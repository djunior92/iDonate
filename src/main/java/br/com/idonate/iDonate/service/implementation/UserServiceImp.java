package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.ApplicationContextLoad;
import br.com.idonate.iDonate.config.property.IDonateProperty;
import br.com.idonate.iDonate.mail.Mailer;
import br.com.idonate.iDonate.model.Enum.StatusUser;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.model.view.ChangePassword;
import br.com.idonate.iDonate.model.view.ValidationUser;
import br.com.idonate.iDonate.repository.UserRepository;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImp implements UserService {

    private static final String subject = "Validação de Usuário";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mailer mailer;

    @Autowired
    private IDonateProperty property;

    @Override
    public User save(User user) throws LoginUnavailableException, InvalidEmailException {
        loginValidation(user);
        emailValidation(user);
        String encrypted = new BCryptPasswordEncoder().encode(user.getPassw());
        user.setPassw(encrypted);
        user.setStatus(StatusUser.AGUARDANDO_VALIDACAO);
        user.setRegistrationDate(LocalDateTime.now());
        user.setCodValidation(generateCodValidation());
        return userRepository.save(user);
    }

    @Override
    public User edit(Long id, User user) throws InvalidEmailException, RegisterNotFoundException {
        User userSaved = userRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Usuário " + id + " não encontrado."));

        userSaved.setEmail(user.getEmail());
        emailValidation(userSaved);

        userSaved.setStatus(StatusUser.AGUARDANDO_VALIDACAO);

        userSaved.setCodValidation(generateCodValidation());

        return userRepository.save(userSaved);
    }


    @Override
    public void linkProfile(User user, Profile profile) {
        user.setProfile(profile);
        userRepository.save(user);
    }

    @Override
    public User validate(ValidationUser validationUser) throws InvalidLoginException,
            LoginAlreadyValidatedException, InvalidCodValidationException {
        Optional<User> userOpt = ApplicationContextLoad.getApplicationContext()
                .getBean(UserRepository.class).findByLogin(validationUser.getLogin());

        if (userOpt.isEmpty()) {
            throw new InvalidLoginException("Login " + validationUser.getLogin() + " não encontrado para validação!");
        }

        if (userOpt.get().getStatus().equals(StatusUser.VALIDADO)) {
            throw new LoginAlreadyValidatedException("Login " + validationUser.getLogin() + " já está com status validado!");
        }

        if (!userOpt.get().getCodValidation().equals(validationUser.getCodValidation())) {
            throw new InvalidCodValidationException("Código " + validationUser.getCodValidation() + " não é valido!");
        }

        User user = userOpt.get();
        user.setStatus(StatusUser.VALIDADO);
        user.setValidationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> searchLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void updateUnsetEmail(User user) {
        user.setStatus(StatusUser.EMAIL_NAO_ENVIADO);
        userRepository.save(user);
    }

    @Async("fileExecutor")
    @Override
    public void triggerEmail(User user) {
        try {

            String message = "Chave de validação: <h1>" + user.getCodValidation() + "</h1>";

            List<String> recipients = new ArrayList<>();
            recipients.add(user.getEmail());

            mailer.sendEmail(property.getMail().getUserName(), recipients, subject, message);
        } catch (Exception e) {

        }
    }

    @Override
    public User changePassword(Long id, ChangePassword changePassword)
            throws RegisterNotFoundException, NewAndOldPasswordAlikeException, PasswordOldInvalidException {
        if (changePassword.getPasswordOld().trim().equals(changePassword.getPasswordNew().trim())) {
            throw new NewAndOldPasswordAlikeException("A senha nova precisa ser diferente.");
        }

        User userEditing = userRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Usuário " + id + " não encontrado."));
        String encrypted = new BCryptPasswordEncoder().encode(changePassword.getPasswordOld());
        if (userEditing.getPassw().equals(encrypted)) {
            throw new PasswordOldInvalidException("Senha antiga inválida.");
        }

        userEditing.setPassw(encrypted);
        return userRepository.save(userEditing);
    }

    private void loginValidation(User user) throws LoginUnavailableException {
        Optional<User> optional = userRepository.findByLogin(user.getLogin());

        if (optional.isPresent()) {
            throw new LoginUnavailableException("Login '" + user.getUsername().toUpperCase() + "' não está disponível!");
        }
    }

    private void emailValidation(User user) throws InvalidEmailException {
        Optional<User> optional = userRepository.findByEmail(user.getEmail());

        if (optional.isPresent()) {
            throw new InvalidEmailException("Email '" + user.getEmail().toLowerCase() + "' já existe!");
        }

        if (!isValidEmailAddressRegex(user.getEmail())) {
            throw new InvalidEmailException(user.getEmail().toLowerCase() + " é um email inválido!");
        }
    }

    private static Boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    private String generateCodValidation() {
        Random random = new Random();
        int resultRandom = random.nextInt(999999) + 1;
        return StringUtils.leftPad(Integer.toString(resultRandom), 6, "0");
    }



}

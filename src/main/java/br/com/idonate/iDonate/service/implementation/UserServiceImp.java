package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.ApplicationContextLoad;
import br.com.idonate.iDonate.model.Enum.StatusUser;
import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.repository.UserRepository;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.InvalidEmailException;
import br.com.idonate.iDonate.service.exception.LoginUnavailableException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) throws LoginUnavailableException, InvalidEmailException {
        loginValidation(user);
        emailValidation(user);
        String encrypted = new BCryptPasswordEncoder().encode(user.getPassw());
        user.setPassw(encrypted);
        user.setStatus(StatusUser.AGUARDANDO_VALIDACAO);
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User edit(Long id, User user) throws InvalidEmailException {
        Boolean changed = false;
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        User userSaved = optionalUser.get();

        if (!userSaved.getEmail().equals(user.getEmail())) {
            userSaved.setEmail(user.getEmail());
            emailValidation(userSaved);
            changed = true;
        }

        if (!userSaved.getStatus().equals(StatusUser.AGUARDANDO_VALIDACAO)) {
            userSaved.setStatus(StatusUser.AGUARDANDO_VALIDACAO);
            changed = true;

        }

        return (changed ? userRepository.save(userSaved) : userSaved);
    }

    @Override
    public User validate(String login) {
        Optional<User> userOpt = ApplicationContextLoad.getApplicationContext()
                .getBean(UserRepository.class).findByLogin(login);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setStatus(StatusUser.VALIDADO);
            user.setValidationDate(LocalDateTime.now());
            return userRepository.save(user);
        }
        return null;
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
        Boolean isEmailIdValid = false;
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

}

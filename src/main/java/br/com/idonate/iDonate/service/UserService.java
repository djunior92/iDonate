package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.model.view.ValidationUser;
import br.com.idonate.iDonate.service.exception.*;

import java.util.Optional;

public interface UserService {

    User save(User user) throws LoginUnavailableException, InvalidEmailException;
    User edit(Long id, User user) throws InvalidEmailException;
    void linkProfile(User user, Profile profile);
    User validate(ValidationUser validationUser) throws InvalidLoginException, LoginAlreadyValidatedException, InvalidCodValidationException;
    Optional<User> searcheLogin(String login);
    void updateUnsetEmail(User user);
    void triggerEmail(User user);

}

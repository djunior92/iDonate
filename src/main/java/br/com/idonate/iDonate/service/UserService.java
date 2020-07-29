package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.service.exception.InvalidEmailException;
import br.com.idonate.iDonate.service.exception.LoginUnavailableException;

public interface UserService {

    User save(User user) throws LoginUnavailableException, InvalidEmailException;
    User edit(Long id, User user) throws InvalidEmailException;
    User validate(String login);

}

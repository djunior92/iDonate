package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.InvalidEmailException;
import br.com.idonate.iDonate.service.exception.LoginUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/newuser")
public class NewUserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user)
            throws LoginUnavailableException, InvalidEmailException {
        final User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}

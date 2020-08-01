package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.InvalidEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PutMapping("/validation/{login}")
    public ResponseEntity<User> validate(@PathVariable String login) {
        User updatedUser = userService.validate(login);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> searchByLogin(@PathVariable String login) {
        Optional<User> user = userService.searcheLogin(login);
        return (user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build());
    }

    @PutMapping("/resendemail/{id}")
    public ResponseEntity<User> resendEmail(@PathVariable Long id, @Valid @RequestBody User user)
            throws InvalidEmailException {
        final User savedUser = userService.edit(id, user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

}

package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

}

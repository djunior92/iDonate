package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByNameContainingIgnoreCase(String name);
}

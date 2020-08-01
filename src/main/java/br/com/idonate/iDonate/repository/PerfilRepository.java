package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    List<Perfil> findByNameContaining(String name);

}

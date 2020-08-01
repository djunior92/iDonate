package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Enum.PeopleType;
import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.repository.PerfilRepository;
import br.com.idonate.iDonate.service.PerfilService;
import br.com.idonate.iDonate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    UserService userService;

    @Override
    public Perfil save(Perfil perfil) {
        perfil.setRegistrationDate(LocalDateTime.now());
        perfil.setDateBirth(LocalDateTime.now());
        perfil.setMyPoints(0);
        perfil.setPointsReceived(0);
        perfil.setPeopleType(PeopleType.F);
        Perfil perfil1Saved = perfilRepository.save(perfil);
        userService.linkPerfil(perfil1Saved);
        return perfil1Saved;
    }

    @Override
    public Perfil edit(Long id, Perfil perfil) {
        Optional<Perfil> optionalPerfil = perfilRepository.findById(id);

        if (!optionalPerfil.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        Perfil pefilSaved = optionalPerfil.get();

        return perfilRepository.save(pefilSaved);
    }

    @Override
    public Optional<Perfil> searchById(Long id) {
        return perfilRepository.findById(id);
    }

    @Override
    public List<Perfil> searchByName(String name) {
        return perfilRepository.findByNameContaining(name);
    }
}

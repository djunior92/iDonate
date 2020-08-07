package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.ApplicationContextLoad;
import br.com.idonate.iDonate.model.Enum.PeopleType;
import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.repository.PerfilRepository;
import br.com.idonate.iDonate.repository.UserRepository;
import br.com.idonate.iDonate.service.PerfilService;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.PerfilNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional(rollbackOn = PerfilNotRegisteredException.class)
    public Perfil save(String login, Perfil perfil) throws PerfilNotRegisteredException {
        try {
            Optional<User> userOpt = ApplicationContextLoad.getApplicationContext()
                    .getBean(UserRepository.class).findByLogin(login);

            if (!userOpt.isPresent()) {
                throw new Exception();
            }

            perfil.setId(userOpt.get().getId());
            perfil.setRegistrationDate(LocalDateTime.now());
            perfil.setDateBirth(LocalDateTime.now());
            perfil.setMyPoints(0);
            perfil.setPointsReceived(0);
            perfil.setPeopleType(PeopleType.F);
            Perfil perfil1Saved = perfilRepository.save(perfil);
            userService.linkPerfil(userOpt.get(), perfil1Saved);
            return perfil1Saved;
        } catch (Exception e) {
            throw new PerfilNotRegisteredException("Erro ao gravar perfil do usuário.");
        }
    }

    @Override
    public Perfil edit(Long id, Perfil perfil) throws EmptyResultDataAccessException {
        if (!perfilExist(id)) {
            throw new EmptyResultDataAccessException(1);
        }
        perfil.setId(id);

        return perfilRepository.save(perfil);
    }

    @Override
    public Optional<Perfil> searchById(Long id) {
        return perfilRepository.findById(id);
    }

    @Override
    public List<Perfil> searchByName(String name) {
        return perfilRepository.findByNameContaining(name);
    }

    private Boolean perfilExist(Long id) {
        Optional<Perfil> optionalPerfil = perfilRepository.findById(id);
        if (!optionalPerfil.isPresent()) {
            return false;
        }
        return true;
    }
}

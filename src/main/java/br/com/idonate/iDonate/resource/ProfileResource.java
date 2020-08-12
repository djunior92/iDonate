package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/profile")
public class ProfileResource {

    @Autowired
    ProfileService profileService;

    @PutMapping("/{id}")
    public ResponseEntity<Profile> edit(@PathVariable Long id, @Valid @RequestBody Profile profile) throws EmptyResultDataAccessException {
        Profile updateProfile = profileService.edit(id, profile);
        return new ResponseEntity<>(updateProfile, HttpStatus.OK);
    }

    @PostMapping("/{login}")
    public ResponseEntity<Profile> save(@PathVariable String login, @Valid @RequestBody Profile profile) throws ProfileNotRegisteredException {
        Profile savedProfile = profileService.save(login, profile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> searchById(@PathVariable Long id) {
        Optional<Profile> profile = profileService.searchById(id);
        return (profile.isPresent() ? ResponseEntity.ok(profile.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Profile>> searchByName(@PathVariable String name) {
        List<Profile> profiles = profileService.searchByName(name);
        return ResponseEntity.ok(profiles);
    }

}
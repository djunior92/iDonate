package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/perfil")
public class PerfilResource {

    @Autowired
    PerfilService perfilService;

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> edit(@PathVariable Long id, @Valid @RequestBody Perfil perfil) {
        Perfil updatePerfil = perfilService.edit(id, perfil);
        return new ResponseEntity<>(updatePerfil, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Perfil> save(@Valid @RequestBody Perfil perfil) {
        Perfil savedPerfil = perfilService.save(perfil);
        return new ResponseEntity<>(savedPerfil, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> searchById(@PathVariable Long id) {
        Optional<Perfil> perfil = perfilService.searchById(id);
        return (perfil.isPresent() ? ResponseEntity.ok(perfil.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Perfil>> searchByName(@PathVariable String name) {
        List<Perfil> perfils = perfilService.searchByName(name);
        return ResponseEntity.ok(perfils);
    }

}

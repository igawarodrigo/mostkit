package com.moskit.contatosApp.controller;

import com.moskit.contatosApp.model.Contato;
import com.moskit.contatosApp.repository.ContatoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ContatosController {

    private ContatoRepository contatoRepository;

    @Autowired
    ContatosController(ContatoRepository contatoRepository){
        this.contatoRepository = contatoRepository;
    }

    @GetMapping({"/", "/list"})
    public Page<Contato> getContatos(Pageable pageable) {
        return contatoRepository.findAll(pageable);
    }

    @GetMapping({"/{id}"})
    public Optional<Contato> getContato(@PathVariable("id") Long id) {
        return contatoRepository.findById(id);
    }

    @PostMapping("/favoritar/{id}")
    public ResponseEntity<?> favoritarContato(@PathVariable("id") Long id) {
        return contatoRepository
                .findById(id)
                .map(contato -> {
                    contato.setFavorito(true);
                    contatoRepository.save(contato);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Contato contato){
        try {
            contatoRepository.save(contato);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Contato contato) {
        return contatoRepository
                .findById(id)
                .map( old -> {
                    contato.setId(old.getId());
                    try {
                        contatoRepository.save(contato);
                    } catch (Exception ex) {
                        return ResponseEntity.badRequest().build();
                    }
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws NotFoundException {
        return contatoRepository
                .findById(id)
                .map(c -> {
                    contatoRepository.delete(c);
                    return ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

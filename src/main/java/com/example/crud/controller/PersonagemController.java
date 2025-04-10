package com.example.crud.controller;

import com.example.crud.model.ItemMagico;
import com.example.crud.model.Personagem;
import com.example.crud.service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Personagem> criarPersonagem(@RequestBody Personagem personagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personagemService.criarPersonagem(personagem));
    }
    @PostMapping("/{id}/adicionar-item")
    public ResponseEntity<Personagem> adicionarItem(@PathVariable Long id, @RequestBody ItemMagico item) {
        return ResponseEntity.ok(personagemService.adicionarItemAoPersonagem(id, item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscarPersonagem(@PathVariable Long id) {
        return personagemService.buscarPersonagem(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagico> buscarAmuleto(@PathVariable Long id) {
        Personagem p = personagemService.buscarPersonagem(id).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        return p.getItensMagicos().stream()
                .filter(item -> item.getTipo().equals("Amuleto"))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Personagem> listarPersonagens() {
        return personagemService.listarPersonagens();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> atualizarPersonagem(@PathVariable Long id, @RequestBody Personagem personagem) {
        return personagemService.buscarPersonagem(id)
                .map(p -> {
                    p.setNome(personagem.getNome());
                    return ResponseEntity.ok(personagemService.criarPersonagem(p));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPersonagem(@PathVariable Long id) {
        personagemService.removerPersonagem(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/remover-item/{itemId}")
    public ResponseEntity<Personagem> removerItem(@PathVariable Long id, @PathVariable Long itemId) {
        return ResponseEntity.ok(personagemService.removerItemDoPersonagem(id, itemId));
    }

}

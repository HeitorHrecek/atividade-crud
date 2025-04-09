package com.example.crud.controller;

import com.example.crud.model.ItemMagico;
import com.example.crud.service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itensmagicos")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping
    public ResponseEntity<ItemMagico> criarItemMagico(@RequestBody ItemMagico itemMagico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemMagicoService.criarItemMagico(itemMagico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> buscarItemMagico(@PathVariable Long id) {
        return itemMagicoService.buscarItemMagico(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public List<ItemMagico> listarItensMagicos() {
        return itemMagicoService.listarItensMagicos();
    }
}

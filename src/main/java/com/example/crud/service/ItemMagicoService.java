package com.example.crud.service;

import com.example.crud.model.ItemMagico;
import com.example.crud.repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public ItemMagico criarItemMagico(ItemMagico itemMagico) {
        return itemMagicoRepository.save(itemMagico);
    }

    public Optional<ItemMagico> buscarItemMagico(Long id) {
        return itemMagicoRepository.findById(id);
    }

    public List<ItemMagico> listarItensMagicos() {
        return itemMagicoRepository.findAll();
    }
}

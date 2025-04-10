package com.example.crud.service;

import com.example.crud.model.ItemMagico;
import com.example.crud.model.Personagem;
import com.example.crud.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public Personagem criarPersonagem(Personagem personagem) {
        int totalPontos = personagem.getForca() + personagem.getDefesa();
        if (totalPontos != 10) {
            throw new RuntimeException("A soma de força e defesa deve ser exatamente 10.");
        }
        return personagemRepository.save(personagem);
    }

    public Optional<Personagem> buscarPersonagem(Long id) {
        return personagemRepository.findById(id);
    }

    public List<Personagem> listarPersonagens() {
        return personagemRepository.findAll();
    }

    public void removerPersonagem(Long id) {
        personagemRepository.deleteById(id);
    }

    public Personagem adicionarItemAoPersonagem(Long id, ItemMagico itemMagico) {
        Personagem personagem = personagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        personagem.adicionarItemMagico(itemMagico);
        return personagemRepository.save(personagem);
    }
    public Personagem removerItemDoPersonagem(Long id, Long itemId) {
        Personagem personagem = personagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        personagem.getItensMagicos().removeIf(item -> item.getId().equals(itemId));
        return personagemRepository.save(personagem);
    }

}
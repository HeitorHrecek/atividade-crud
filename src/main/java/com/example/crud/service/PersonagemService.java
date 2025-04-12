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

    private int calcularPontosBase(Personagem personagem) {
        return personagem.getForca() + personagem.getDefesa();
    }

    public Personagem criarPersonagem(Personagem personagem) {
        int pontosBases = personagem.getForca() + personagem.getDefesa();
        if (pontosBases > 10) {
            throw new RuntimeException("A soma dos pontos base de força e defesa deve ser menor ou igual a 10.");
        }
        return personagemRepository.save(personagem);
    }

    public Personagem atualizarPersonagem(Long id, Personagem personagemAtualizado) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        int pontosBases = personagemAtualizado.getForca() + personagemAtualizado.getDefesa();
        if (pontosBases > 10) {
            throw new RuntimeException("A soma dos pontos base de força e defesa deve ser menor ou igual a 10.");
        }

        personagem.setNome(personagemAtualizado.getNome());
        personagem.setNomeAventureiro(personagemAtualizado.getNomeAventureiro());
        personagem.setClasse(personagem.getClasse()); // Mantém a classe original
        personagem.setLevel(personagemAtualizado.getLevel());
        personagem.setForca(personagemAtualizado.getForca());
        personagem.setDefesa(personagemAtualizado.getDefesa());

        Personagem personagemSalvo = personagemRepository.save(personagem);
        personagemSalvo.setForca(personagemSalvo.getForcaTotal());
        personagemSalvo.setDefesa(personagemSalvo.getDefesaTotal());
        
        return personagemSalvo;
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

    private int calcularPontosComItens(Personagem personagem) {
        int pontosItens = personagem.getItensMagicos().stream()
                .mapToInt(item -> item.getForca() + item.getDefesa())
                .sum();
        return calcularPontosBase(personagem) + pontosItens;
    }

    public Personagem adicionarItemAoPersonagem(Long id, ItemMagico itemMagico) {
        Personagem personagem = personagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        
        // Verifica os pontos base
        int pontosBase = personagem.getForca() + personagem.getDefesa();
        if (pontosBase > 10) {
            throw new RuntimeException("A soma dos pontos base de força e defesa deve ser menor ou igual a 10.");
        }
        
        // Calcula os pontos totais considerando o novo item antes de adicioná-lo
        int pontosAtuais = personagem.getForcaTotal() + personagem.getDefesaTotal();
        int pontosDoNovoItem = itemMagico.getForca() + itemMagico.getDefesa();
        int pontosTotal = pontosAtuais + pontosDoNovoItem;
        
        if (pontosTotal > 10) {
            throw new RuntimeException("A soma total dos pontos (base + itens) não pode exceder 10 pontos.");
        }
        
        personagem.adicionarItemMagico(itemMagico);
        return personagemRepository.save(personagem);
    }
    public Personagem removerItemDoPersonagem(Long id, Long itemId) {
        Personagem personagem = personagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
        personagem.getItensMagicos().removeIf(item -> item.getId().equals(itemId));
        return personagemRepository.save(personagem);
    }

}
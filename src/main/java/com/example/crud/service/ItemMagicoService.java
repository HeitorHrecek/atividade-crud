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
        if (itemMagico.getForca() < 0 || itemMagico.getDefesa() < 0 ||
                itemMagico.getForca() > 10 || itemMagico.getDefesa() > 10) {
            throw new RuntimeException("Força e Defesa devem estar entre 0 e 10.");
        }

        switch (itemMagico.getTipo()) {
            case "Arma":
                if (itemMagico.getDefesa() != 0)
                    throw new RuntimeException("Itens do tipo Arma devem ter defesa 0.");
                break;
            case "Armadura":
                if (itemMagico.getForca() != 0)
                    throw new RuntimeException("Itens do tipo Armadura devem ter força 0.");
                break;
            case "Amuleto":
                if (itemMagico.getForca() == 0 && itemMagico.getDefesa() == 0)
                    throw new RuntimeException("Amuleto deve ter força ou defesa maior que 0.");
                break;
            default:
                throw new RuntimeException("Tipo de item inválido. Use: Arma, Armadura ou Amuleto.");
        }

        return itemMagicoRepository.save(itemMagico);
    }


    public Optional<ItemMagico> buscarItemMagico(Long id) {
        return itemMagicoRepository.findById(id);
    }

    public List<ItemMagico> listarItensMagicos() {
        return itemMagicoRepository.findAll();
    }
}

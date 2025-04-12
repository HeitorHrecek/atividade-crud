package com.example.crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ItemMagico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Tipo do item é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @Min(value = 0, message = "Força não pode ser negativa")
    @Max(value = 10, message = "Força não pode ser maior que 10")
    private int forca;

    @Min(value = 0, message = "Defesa não pode ser negativa")
    @Max(value = 10, message = "Defesa não pode ser maior que 10")
    private int defesa;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    @JsonBackReference
    private Personagem personagem;

    public enum TipoItem {
        ARMA, ARMADURA, AMULETO
    }

    @PrePersist
    @PreUpdate
    private void validarItem() {
        if (forca == 0 && defesa == 0) {
            throw new IllegalStateException("Item deve ter pelo menos força ou defesa maior que zero");
        }

        if (tipo == TipoItem.ARMA && defesa > 0) {
            throw new IllegalStateException("Armas não podem ter defesa");
        }

        if (tipo == TipoItem.ARMADURA && forca > 0) {
            throw new IllegalStateException("Armaduras não podem ter força");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}
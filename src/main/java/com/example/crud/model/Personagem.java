package com.example.crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Nome do aventureiro é obrigatório")
    private String nomeAventureiro;

    @NotNull(message = "Classe é obrigatória")
    @Enumerated(EnumType.STRING)
    private ClassePersonagem classe;

    @Min(value = 1, message = "Level deve ser maior que 0")
    private int level;

    @Min(value = 0, message = "Força não pode ser negativa")
    @Max(value = 10, message = "Força não pode ser maior que 10")
    private int forca;

    @Min(value = 0, message = "Defesa não pode ser negativa")
    @Max(value = 10, message = "Defesa não pode ser maior que 10")
    private int defesa;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    public enum ClassePersonagem {
        GUERREIRO, MAGO, ARQUEIRO, LADINO, BARDO
    }

    public Personagem() {
        
    }

    public Personagem(String nome, String nomeAventureiro, ClassePersonagem classe, int level, int forca, int defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public void adicionarItemMagico(ItemMagico item) {
        if (item.getTipo() == ItemMagico.TipoItem.AMULETO && possuiAmuleto()) {
            throw new IllegalStateException("Personagem já possui um amuleto");
        }
        itensMagicos.add(item);
        item.setPersonagem(this);
    }

    public void removerItemMagico(ItemMagico item) {
        itensMagicos.remove(item);
        item.setPersonagem(null);
    }

    public boolean possuiAmuleto() {
        return itensMagicos.stream()
                .anyMatch(item -> item.getTipo() == ItemMagico.TipoItem.AMULETO);
    }

    public int getForcaTotal() {
        return forca + itensMagicos.stream()
                .mapToInt(ItemMagico::getForca)
                .sum();
    }

    public int getDefesaTotal() {
        return defesa + itensMagicos.stream()
                .mapToInt(ItemMagico::getDefesa)
                .sum();
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

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public ClassePersonagem getClasse() {
        return classe;
    }

    public void setClasse(ClassePersonagem classe) {
        this.classe = classe;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        if (forca < 0 || forca > 10) {
            throw new IllegalArgumentException("Força deve estar entre 0 e 10");
        }
        if (forca + this.defesa > 10) {
            throw new IllegalArgumentException("A soma de força e defesa não pode exceder 10 pontos");
        }
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        if (defesa < 0 || defesa > 10) {
            throw new IllegalArgumentException("Defesa deve estar entre 0 e 10");
        }
        if (this.forca + defesa > 10) {
            throw new IllegalArgumentException("A soma de força e defesa não pode exceder 10 pontos");
        }
        this.defesa = defesa;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

}
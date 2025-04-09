package com.example.crud.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeAventureiro;
    private String classe;
    private int level;
    private int forca;
    private int defesa;

    @ManyToMany
    private List<ItemMagico> itensMagicos;

    public Personagem(String nome, String nomeAventureiro, String classe, int level, int forca, int defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public void adicionarItemMagico(ItemMagico item) {
        if (item.getTipo().equals("Amuleto") && temAmuleto()) {
            throw new RuntimeException("Já possui um amuleto!");
        }
        this.itensMagicos.add(item);
        this.forca += item.getForca();
        this.defesa += item.getDefesa();
    }

    private boolean temAmuleto() {
        return this.itensMagicos.stream().anyMatch(item -> item.getTipo().equals("Amuleto"));
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
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
        this.forca = forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }

}
package com.example.crud.model;

import jakarta.persistence.*;

@Entity
public class Personagem {
    //Identificador;
    //Nome;
    //Nome Aventureiro;
    //Classe (Guerreiro, Mago, Arqueiro, Ladino ou Bardo)
    //Level;
    //Lista de Itens Mágicos;
    //Força;
    //Defesa;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private int hp;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    private int level, forca, defesa;



    public Long getId() {
        return id;
    }

    public int getHp() {
        return hp;
    }

    public String getNome() {
        return nome;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getLevel() {
        return level;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public int produzDano(int dano){
        return this.forca + dano;
    }

    public void receberDano(int dano){
        this.hp -= dano;
    }
}

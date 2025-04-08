package com.example.crud.model;

public class ItemMagico {

    private String nomeItem;
    private int tipo;
    private int def, atk;

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    private void verificarTipo() {
        if(tipo == 1) {
            def = 0;
            atk = 3;
        }else if (tipo == 2) {
            def = 3;
            atk = 0;
        } else if (tipo == 3) {
            def = 5;
            atk = 3;
        }


    }









}

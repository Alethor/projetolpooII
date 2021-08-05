/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Pizza {
    private int id;
    private Sabor sabor1;
    private Sabor sabor2;
    private Double area;
    private String nomeForma;
    private double valor;
    
    public Pizza(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sabor getSabor1() {
        return sabor1;
    }

    public void setSabor1(Sabor sabor1) {
        this.sabor1 = sabor1;
    }

    public Sabor getSabor2() {
        return sabor2;
    }

    public void setSabor2(Sabor sabor2) {
        this.sabor2 = sabor2;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getNomeForma() {
        return nomeForma;
    }

    public void setNomeForma(String nomeForma) {
        this.nomeForma = nomeForma;
    }
    
    
    
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alethor
 */
public class Triangulo implements Forma{
    
    private Double lado;
    private Double area;
    
    
    public Triangulo() {
    }

    public Double getLado() {
        return lado;
    }

    public void setLado(Double lado) {
        this.lado = lado;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public Double calculaArea() {
        this.setArea((this.getLado() * this.getLado())/2);
        return this.getArea();
    }

    @Override
    public Double calculaLadoOuRaio() {
        this.setLado(Math.sqrt(2*this.getArea()));
        return this.getLado();    
    }
}

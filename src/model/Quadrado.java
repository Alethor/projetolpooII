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
public class Quadrado implements Forma{
    
     private double lado;
     private double area;
   
    

    public Quadrado() {
    }

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }


    @Override
    public Double calculaArea() {
        this.setArea(this.getLado() * this.getLado());
        return this.getArea();
    }

    @Override
    public Double calculaLadoOuRaio() {
        this.setLado(Math.sqrt(this.getArea()));
        return this.getLado();
    }
}

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
public class Circulo extends Forma{
    
    private Double r;
    
    public Circulo(){}

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }
    
    
    public void calculaAreaCirculo(double r){
        this.setR(r);
        this.setArea((3.14 * (Math.pow(r, 2))));
        this.setTipo("Círculo");
    }
    
    public void calculaRaioCirculo(double area){
         this.setArea(area);
         this.setR(Math.sqrt(area/3.14));
         this.setTipo("Círculo");
    }
}

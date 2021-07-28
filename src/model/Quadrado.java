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
public class Quadrado extends Forma{
    
     private double base;
     private double h;
   
    

    public Quadrado() {
    }


    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }
   
    
    
    public void calculaAreaRetangulo(double base, double h){
        this.setBase(base);
        this.setH(h);
        this.setArea(base * h);
        this.setTipo("Quadrado");
    
    }
    
    public void calculaBaseAltura(double area){
        this.setArea(area);
        this.setBase(Math.sqrt(area));
        this.setH(Math.sqrt(area));
        this.setTipo("Quadrado");
       
    }
}

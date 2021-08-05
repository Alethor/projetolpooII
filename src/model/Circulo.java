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
public class Circulo implements Forma{
    
    private Double r;
    private Double area;
    
    public Circulo(){}

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
  
    @Override
    public Double calculaArea() {
        this.setArea((3.14 * (Math.pow(this.getR(), 2))));
        return this.getArea();
    }

    @Override
    public Double calculaLadoOuRaio() {
        this.setR(Math.sqrt(this.getArea()/3.14));
        return this.getR();
    }
}

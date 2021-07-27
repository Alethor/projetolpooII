/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PedidoDao;
import dao.PizzaDao;
import java.util.ArrayList;
import java.util.List;
import model.Circulo;
import model.Forma;
import model.Pedido;
import model.Pizza;
import model.Quadrado;
import model.Sabor;
import model.Triangulo;

/**
 *
 * @author Alethor
 */
public class PedidoController {
    
    public void insertPedido(int idCliente){
        Pedido p = new Pedido();
        PedidoDao pDao = new PedidoDao();
        
        p.setIdCliente(idCliente);
        p.setTotalPedido(0);
        p.setIdStatus(1);
        Boolean retorno = pDao.insertPedido(p);
    }
    
    public Pedido findPedido(int idCliente){
        Pedido p = new Pedido();
        PedidoDao pDao = new PedidoDao();
        PizzaDao pzDao = new PizzaDao();
        List<Pizza> pizzas = new ArrayList<Pizza>();
        
        p = pDao.findPedido(idCliente);
        pizzas = pzDao.findPizzasPedido(p.getIdPedido());
        for(Pizza pz : pizzas){
            pz.setSabor1(pzDao.findSaborPizza(pz.getSabor1().getId()));
            pz.setSabor2(pzDao.findSaborPizza(pz.getSabor2().getId()));
        }
        p.setPizzas(pizzas);
        
        return p;
    }
    
    public List<Pizza> findPizzaPedido(int idPedido){
        List<Pizza> pizzas = new ArrayList<>();
        PizzaDao pzDao = new PizzaDao();
        
        pizzas = pzDao.findPizzasPedido(idPedido);
        return pizzas;
        
    }
    
    public void insertPizzaPedido(double x, double y, double area, String forma, Sabor sabor1, Sabor sabor2, int idPedido){
        Pizza pz = new Pizza();
        PizzaDao pzDao = new PizzaDao();
        switch(forma){
            case "Círculo":
                Circulo c = new Circulo();
                c.setTipo("Círculo");
                if(area == 0){
                   c.calculaAreaCirculo(x);
                   
                }else{
                   c.calculaRaioCirculo(area);
                }
                pz.setForma(c);
                break;
            case "Triângulo":
                Triangulo t = new Triangulo();
                t.setTipo("Triângulo");
                if(area == 0){
                    t.calculaAreaTriangulo(x, y);
                }else{
                    t.calculaBaseAlturaTriangulo(area);
                }
                pz.setForma(t);
                break;
            case "Quadrado":
                Quadrado q = new Quadrado();
                q.setTipo("Quadrado");
                if(area == 0){
                    q.calculaAreaRetangulo(x, y);
                }else{
                    q.calculaBaseAltura(area);
                }
                pz.setForma(q);
                
                break;
        }
       
        
        sabor1.setValor(pzDao.findValorSabor(sabor1.getIdCategoria()));
        sabor2.setValor(pzDao.findValorSabor(sabor2.getIdCategoria()));
        
        pz.setSabor1(sabor1);
        pz.setSabor2(sabor2);
      
        pz.setValor(pz.getForma().getArea() * ((sabor1.getValor() + sabor2.getValor())/2));
        
        System.out.println("pz area: " + pz.getForma().getArea());
        System.out.println("pz valor: " + pz.getValor());
        System.out.println("média valors: " + ((sabor1.getValor() + sabor2.getValor())/2));        
    }
}


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
import model.Cliente;
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
    
    public void insertPedido(Cliente cliente){
        Pedido p = new Pedido();
        PedidoDao pDao = new PedidoDao();
        
        p.setCliente(cliente);
        p.setTotalPedido(0);
        p.setIdStatus(1);
        Boolean retorno = pDao.insertPedido(p);
    }
    
    public Pedido findPedido(Cliente cliente){
        Pedido p = new Pedido();
        PedidoDao pDao = new PedidoDao();
        PizzaDao pzDao = new PizzaDao();
        List<Pizza> pizzas = new ArrayList<Pizza>();
     
        p = pDao.findPedido(cliente);
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
        for(Pizza p : pizzas){
            p.setSabor1(pzDao.findSaborPizza(p.getSabor1().getId()));
            p.setSabor2(pzDao.findSaborPizza(p.getSabor2().getId()));
        }
        return pizzas;
        
    }
    
    public void atualizaTotalPedido(Pedido p){
        PedidoDao pDao = new PedidoDao();
        
        p.setPizzas(this.findPizzaPedido(p.getIdPedido()));
        p.atualizaTotalPedido();
        pDao.atualizaTotalPedido(p);
        
        
    }
    
    public List<Double> insertPizzaPedido(double x, double y, double area, String forma, Sabor sabor1, Sabor sabor2, Pedido p, int operacao, int idPizza){
        Pizza pz = new Pizza();
        PizzaDao pzDao = new PizzaDao();
        List<Double> retorno = new ArrayList<>();
       
        
        sabor1.setValor(pzDao.findValorSabor(sabor1.getIdCategoria()));
        sabor2.setValor(pzDao.findValorSabor(sabor2.getIdCategoria()));
        
        pz.setSabor1(sabor1);
        pz.setSabor2(sabor2);
        
        switch(forma){
            case "Círculo":
                Circulo c = new Circulo();
                c.setTipo("Círculo");
                if(area == 0){
                   c.calculaAreaCirculo(x);
                   pz.setForma(c);
                   retorno.add(c.getArea());
                }else{
                   c.calculaRaioCirculo(area);
                   pz.setForma(c);
                   retorno.add(c.getR());
                }
                break;
            case "Triângulo":
                Triangulo t = new Triangulo();
                t.setTipo("Triângulo");
                if(area == 0){
                    t.calculaAreaTriangulo(x, y);
                    pz.setForma(t);
                    retorno.add(t.getArea());
                }else{
                    t.calculaBaseAlturaTriangulo(area);
                    pz.setForma(t);
                    retorno.add(t.getBase());
                    retorno.add(t.getH());
                }
                break;
            case "Quadrado":
                Quadrado q = new Quadrado();
                q.setTipo("Quadrado");
                if(area == 0){
                    q.calculaAreaRetangulo(x, y);
                    pz.setForma(q);
                   
                    retorno.add(q.getArea());
                   
                }else{
                    q.calculaBaseAltura(area);
                    pz.setForma(q);
                    retorno.add(q.getBase());
                    retorno.add(q.getH());
                    
                }
            break;
        }
        pz.setValor(pz.getForma().getArea() * ((sabor1.getValor() + sabor2.getValor())/2));
        
        switch(operacao){
            case 1:
                 pzDao.insertPizzaPedido(p.getIdPedido(), pz);
                break;
            case 2:
                pz.setId(idPizza);
                pzDao.updatePizzaPedido(pz);
                break;
        }
       
        this.atualizaTotalPedido(p);
        return retorno;
    }
}


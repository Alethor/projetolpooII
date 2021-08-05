/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDao;
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
import model.Status;
import model.Triangulo;

/**
 *
 * @author Alethor
 */
public class PedidoController {
    
    public void insertPedido(Cliente cliente){
        Pedido p = new Pedido();
        PedidoDao pDao = new PedidoDao();
        Status s = new Status();
        s.setIdStatus(1);
        p.setCliente(cliente);
        p.setTotalPedido(0);
        p.setStatus(s);
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
    
    public Double insertPizzaPedido(double x, double area, String forma, Sabor sabor1, Sabor sabor2, Pedido p, int operacao, int idPizza){
        Pizza pz = new Pizza();
        PizzaDao pzDao = new PizzaDao();
        Double retorno = 0.0;
       
        
        sabor1.setValor(pzDao.findValorSabor(sabor1.getCategoria().getId()));
        sabor2.setValor(pzDao.findValorSabor(sabor2.getCategoria().getId()));
        
        pz.setSabor1(sabor1);
        pz.setSabor2(sabor2);
        
        switch(forma){
            case "Círculo":
                Circulo c = new Circulo();
                pz.setNomeForma("Círculo");
                if(area == 0){
                   c.setR(x);
                   pz.setArea(c.calculaArea());
                   retorno = pz.getArea();
                }else{
                   c.setArea(area);
                   pz.setArea(area);
                   c.calculaLadoOuRaio();
                  retorno = c.getR();
                }
                break;
            case "Triângulo":
                Triangulo t = new Triangulo();
                pz.setNomeForma("Triângulo");
                if(area == 0){
                    t.setLado(x);
                    pz.setArea(t.calculaArea());
                    retorno = t.getArea();
                }else{
                    t.setArea(area);
                    pz.setArea(area);
                    t.calculaLadoOuRaio();
                    retorno = t.getLado();
                }
                break;
            case "Quadrado":
                Quadrado q = new Quadrado();
                pz.setNomeForma("Quadrado");
                if(area == 0){
                    q.setLado(x);
                    pz.setArea(q.calculaArea());
                    retorno = q.getArea();          
                }else{
                    q.setArea(area);
                    pz.setArea(area);
                    q.calculaLadoOuRaio();
                    retorno = q.getLado();

                }
            break;
        }
        pz.setValor(pz.getArea() * ((sabor1.getValor() + sabor2.getValor())/2));
        
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
    
    public List<Pedido> findAllPedidos(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        PedidoDao pDao = new PedidoDao();
        ClienteDao cDao = new ClienteDao();
        pedidos = pDao.findAllPedido();
        
        for(Pedido p : pedidos){
            p.setStatus(pDao.findStatusPedido(p.getStatus().getIdStatus()));
            p.setCliente(cDao.findClienteById(p.getCliente().getId()));
        }
        return pedidos;
        
    }
    
    public List<Status> findAllStatus(){
        List<Status> status = new ArrayList<Status>();
        PedidoDao pDao = new PedidoDao();
        status = pDao.findAllStatus();
        return status;
        
    }
    
    public void updateStatusPedido(int idPedido, Status s){
        PedidoDao pDao = new PedidoDao();
        pDao.updateStatus(idPedido, s);
        
    }
}


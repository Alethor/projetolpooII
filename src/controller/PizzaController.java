/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PizzaDao;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Sabor;

/**
 *
 * @author Alethor
 */
public class PizzaController {
    
    public List<String> findFormas(){
        
        PizzaDao pDao = new PizzaDao();
        List<String> formas = new ArrayList<String>();
        
        formas = pDao.findFormas();
        return formas;

    }
    
    public List<Sabor> findSabores(){
        PizzaDao pDao = new PizzaDao();
         List<Sabor> sabores = new ArrayList<Sabor>();
         
         sabores = pDao.findSabores();
         for(Sabor s : sabores){
             s.setCategoria(pDao.findCategoriaSabor(s.getCategoria().getId()));
             
         }
         
         return sabores;
         
    }
    
    public void excluirPizzaPedido(int idPizza){
        PizzaDao pDao = new PizzaDao();
        
        pDao.deletePizzaPedido(idPizza);
    }
    
    public List<Categoria> findAllCategorias(){
       List<Categoria> categorias = new ArrayList<Categoria>();
       PizzaDao pDao = new PizzaDao();
       categorias = pDao.findAllCategoria();

       return categorias;
       
    }
    
    public void updateValorCategoria(int idCategoria, double valor){
        PizzaDao pDao = new PizzaDao();
        pDao.updateValorCategoria(idCategoria, valor);
    }
    
    public void insertSabor(Sabor s){
        PizzaDao pDao = new PizzaDao();
        pDao.insertSabor(s);
    }
    
   public void updateSabor(int idSabor, Categoria c){
       PizzaDao pDao = new PizzaDao();
       Sabor sa = new Sabor();
       
       sa = pDao.findSaborPizza(idSabor);
       sa.setCategoria(c);
       
       pDao.updateSabor(sa);
      
   }
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import db.DB;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import model.Pizza;
import model.Sabor;

/**
 *
 * @author Alethor
 */
public class PizzaDao {
    
    public List<String> findFormas(){
        List<String> formas = new ArrayList<String>();
        
        String sql = "SELECT * FROM col_formas";
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                formas.add(rs.getString("DESCRICAO"));
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return formas;
        
        
    }
    
    public List<Sabor> findSabores(){
        List<Sabor> sabores = new ArrayList<Sabor>();
        
        String sql = "SELECT * FROM col_sabores";
       
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Sabor sabor = new Sabor();
                sabor.setId(rs.getInt("ID"));
                sabor.setDescricao(rs.getString("DESCRICAO"));
                sabor.setIdCategoria(rs.getInt("IDCATEGORIA"));
                
                sabores.add(sabor);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sabores;
    }
    
    public double findValorSabor(int idCategoria){
       
        String sql = "SELECT * FROM col_categoria where ID = ?";
        Double valor = 0.0 ;
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                
                valor = rs.getDouble("PRECO");
             
            }
          
        }catch(SQLException e){
            e.printStackTrace();
        }
        return valor;
                
    }
    
    public List<Pizza> findPizzasPedido(int idPedido){
        List<Pizza> pizzas = new ArrayList<Pizza>();
        
        String sql = "SELECT * FROM col_pizza_pedido WHERE IDPEDIDO = ?";
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Pizza p = new Pizza();
                p.getForma().setTipo(rs.getString("FORMA"));
                p.getForma().setArea(rs.getDouble("AREA"));
                p.setValor(rs.getDouble("VALOR"));
                p.getSabor1().setId(rs.getInt("SABOR1"));
                p.getSabor2().setId(rs.getInt("SABOR2"));
                pizzas.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pizzas;
    }
    
    public Sabor findSaborPizza(int idSabor){
        Sabor s = new Sabor();
        
        String sql = "SELECT * FROM col_sabores WHERE ID = ?";
       
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idSabor);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                s.setId(rs.getInt("ID"));
                s.setDescricao(rs.getString("DESCRICAO"));
                s.setIdCategoria(rs.getInt("IDCATEGORIA"));
               
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return s;
        
        
    }
}

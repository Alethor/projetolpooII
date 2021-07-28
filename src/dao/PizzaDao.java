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
import model.Forma;
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
    public void insertPizzaPedido(int idPedido, Pizza p){
        String sql = "INSERT INTO col_pizza_pedido(IDPEDIDO, AREA, VALOR, FORMA, SABOR1, SABOR2) VALUES (?,?,?,?,?,?)";
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ps.setDouble(2, p.getForma().getArea());
            ps.setDouble(3, p.getValor());
            ps.setString(4, p.getForma().getTipo());
            ps.setInt(5, p.getSabor1().getId());
            ps.setInt(6, p.getSabor2().getId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updatePizzaPedido(Pizza p){
        String sql = "UPDATE col_pizza_pedido SET AREA = ?, VALOR = ?, FORMA = ?, SABOR1 = ?, SABOR2 = ? WHERE ID = ?";
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setDouble(1, p.getForma().getArea());
            ps.setDouble(2, p.getValor());
            ps.setString(3, p.getForma().getTipo());
            ps.setInt(4, p.getSabor1().getId());
            ps.setInt(5, p.getSabor2().getId());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
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
                Forma f = new Forma();
                Sabor s1 = new Sabor();
                Sabor s2 = new Sabor();
                p.setId(rs.getInt("ID"));
                f.setTipo(rs.getString("FORMA"));
                f.setArea(rs.getDouble("AREA"));
                p.setValor(rs.getDouble("VALOR"));
                s1.setId(rs.getInt("SABOR1"));
                s2.setId(rs.getInt("SABOR2"));
                p.setForma(f);
                p.setSabor1(s1);
                p.setSabor2(s2);
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
    
    public void deleteAllPizzaPedido(int idPedido){
        String sql = "DELETE FROM col_pizza_pedido WHERE IDPEDIDO = ?";
        
         try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ps.executeUpdate();
         }catch(SQLException e){
             e.printStackTrace();
         }
        
    }
    
    public void deletePizzaPedido(int idPizza){
        String sql = "DELETE FROM col_pizza_pedido WHERE ID = ?";
        
         try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idPizza);
            ps.executeUpdate();
         }catch(SQLException e){
             e.printStackTrace();
         }
        
    }
}

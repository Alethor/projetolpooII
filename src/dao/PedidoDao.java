/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Pedido;
import java.sql.Connection;
import db.DB;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import model.Cliente;
import model.Status;

/**
 *
 * @author Alethor
 */
public class PedidoDao {
    
    public Boolean insertPedido(Pedido p){
        String sql = "INSERT INTO col_pedido(IDCLIENTE, IDSTATUS, TOTAL) VALUES (?,?,?)";
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, p.getCliente().getId());
            ps.setInt(2, p.getStatus().getIdStatus());
            ps.setDouble(3, p.getTotalPedido());
            ps.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Pedido findPedido(Cliente c){
        Pedido p = new Pedido();
       
        String sql = "SELECT * FROM col_pedido WHERE IDCLIENTE = ?";
        
          try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Status s = new Status();
                p.setIdPedido(rs.getInt("ID"));
                s.setIdStatus(rs.getInt("IDSTATUS"));
                p.setTotalPedido(rs.getDouble("TOTAL"));
                p.setStatus(s);
            }
           p.setCliente(c);
        }catch(SQLException e){
            e.printStackTrace();
            
        }
         
        return p;
    }
    
    public void atualizaTotalPedido(Pedido p){
        String sql = "UPDATE col_pedido SET TOTAL = ? WHERE ID = ?";
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setDouble(1, p.getTotalPedido());
            ps.setInt(2, p.getIdPedido());
            ps.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deletePedido(int idCliente){
        String sql = "DELETE FROM col_pedido WHERE IDCLIENTE = ?";
        
         try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
         }catch(SQLException e){
             e.printStackTrace();
         }
        
    }
    
    public List<Pedido> findAllPedido(){
        String sql = "SELECT * FROM col_pedido";
        List<Pedido> pedidos = new ArrayList<Pedido>();
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Status s = new Status();
                Pedido p = new Pedido();
                Cliente c = new Cliente();
                p.setIdPedido(rs.getInt("ID"));
                s.setIdStatus(rs.getInt("IDSTATUS"));
                c.setId(rs.getInt("IDCLIENTE"));
                p.setTotalPedido(rs.getDouble("TOTAL"));
                p.setCliente(c);
                p.setStatus(s);
                pedidos.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return pedidos;
    }
    
    public Status findStatusPedido(int idStatus){
        String sql = "SELECT * FROM col_status WHERE ID = ?";
        Status s = new Status();
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, idStatus);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                s.setIdStatus(rs.getInt("ID"));
                s.setDescricao(rs.getString("DESCRICAO"));
                    
            }
        }catch(SQLException e){
            e.printStackTrace();
        
    }
        return s;
}
    
    public List<Status> findAllStatus(){
        String sql = "SELECT * FROM col_status";
        List<Status> status = new ArrayList<Status>();
        
        try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Status s = new Status();
                
                s.setIdStatus(rs.getInt("ID"));
                s.setDescricao(rs.getString("DESCRICAO"));
                
                status.add(s);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return status;
           
    }
    
    public void updateStatus(int idPedido, Status s){
        String sql = "UPDATE col_pedido SET IDSTATUS = ? WHERE ID = ?";
        
         try{
            Connection conn = DB.getConnection();
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1, s.getIdStatus());
            ps.setInt(2, idPedido);
            ps.executeUpdate();
            
          
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import db.DB;
import model.Cliente;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author Alethor
 */
public class ClienteDao {
    
    
    public boolean insertCliente(Cliente c){
       String sql = "INSERT INTO col_cliente (NOME, SOBRENOME, TELEFONE) VALUES (?, ?, ?)";
       
       try{
         Connection conn = DB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setString(1, c.getNome());
         ps.setString(2, c.getSobrenome());
         ps.setLong(3, c.getTelefone());
         ps.executeUpdate();
       }catch(SQLException e){
           e.printStackTrace();
           return false;
       }
       return true;
    }
    
    public List<Cliente> findAllCliente(){
        List<Cliente> lc = new ArrayList<Cliente>();
        String sql = "SELECT * FROM col_cliente";
        try{
          Connection conn = DB.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              Cliente c = new Cliente();
              c.setId(rs.getInt("ID"));
              c.setNome(rs.getString("NOME"));
              c.setSobrenome(rs.getString("SOBRENOME"));
              c.setTelefone(rs.getLong("TELEFONE"));
              lc.add(c);
          }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lc;
    }
    
    public void deleteCliente(int id){
       String sql = "DELETE FROM col_cliente WHERE ID = ? ";
       
       try{
          Connection conn = DB.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, id);
          ps.executeUpdate();
          
       }catch(SQLException e){
           e.printStackTrace();
       }
       
    }
    
    public void updateCliente(Cliente c){
        String sql = "UPDATE col_cliente set NOME = ?, SOBRENOME = ?, TELEFONE = ? WHERE ID = ?";
        
        try{
          Connection conn = DB.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, c.getNome());
          ps.setString(2, c.getSobrenome());
          ps.setLong(3, c.getTelefone());
          ps.setInt(4, c.getId());
          ps.executeUpdate();
          
       }catch(SQLException e){
           e.printStackTrace();
       }
    }
    
    public List<Cliente> buscaCliente(String busca){
        List<Cliente> lc = new ArrayList<Cliente>();
        
        String sql = "select * from col_cliente where SOBRENOME like ? OR TELEFONE like ?";
        try{
          Connection conn = DB.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, "%"+busca+"%");
          ps.setString(2, "%"+busca+"%");
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              Cliente c = new Cliente();
              c.setId(rs.getInt("ID"));
              c.setNome(rs.getString("NOME"));
              c.setSobrenome(rs.getString("SOBRENOME"));
              c.setTelefone(rs.getLong("TELEFONE"));
              lc.add(c);
          }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lc;
    }
    
    public Cliente findCliente(String busca){
        Cliente c = new Cliente();
        String sql = "SELECT * FROM col_cliente WHERE TELEFONE = ?";
        
         try{
          Connection conn = DB.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, busca);
          ResultSet rs = ps.executeQuery();
          
          while(rs.next()){
              c.setId(rs.getInt("ID"));
              c.setNome(rs.getString("NOME"));
              c.setSobrenome(rs.getString("SOBRENOME"));
              c.setTelefone(rs.getLong("TELEFONE"));
          }
        }catch(SQLException e){
            e.printStackTrace();
        }
         return c;
    }
}

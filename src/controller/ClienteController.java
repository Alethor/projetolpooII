/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import dao.ClienteDao;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Alethor
 */
public class ClienteController {
    
    
    public Boolean insertCliente(String nome, String sobrenome, Long telefone){
        Cliente c = new Cliente();
        ClienteDao cDao = new ClienteDao();
        c.setNome(nome);
        c.setSobrenome(sobrenome);
        c.setTelefone(telefone);
        
       Boolean valor = cDao.insertCliente(c);
       return valor;
        
    }
    
    public List<Cliente> findAllCliente(){
        ClienteDao cDao = new ClienteDao();
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        clientes = cDao.findAllCliente();
        return clientes;
    }
    
    public void deleteCliente(int id){
        ClienteDao cDao = new ClienteDao();
        cDao.deleteCliente(id);
    }
    
    public void alterarCliente(int id, String nome, String sobrenome, Long telefone){
        Cliente c = new Cliente();
        ClienteDao cDao = new ClienteDao();
        
        c.setId(id);
        c.setNome(nome);
        c.setSobrenome(sobrenome);
        c.setTelefone(telefone);
        
        cDao.updateCliente(c);
        
    }
    
    public List<Cliente> buscaClientes(String busca){
        List<Cliente> lc = new ArrayList<Cliente>();
        ClienteDao cDao = new ClienteDao();
        
        lc = cDao.buscaCliente(busca);
        
        return lc;
    }
    
    public Cliente buscaCliente(String busca){
        Cliente c = new Cliente();
        ClienteDao cDao = new ClienteDao();
        
        c = cDao.findCliente(busca);
        return c;
    }
}

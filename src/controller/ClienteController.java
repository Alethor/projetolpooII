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
    
    
    public void insertCliente(String nome, String sobrenome, Long telefone){
        Cliente c = new Cliente();
        ClienteDao cDao = new ClienteDao();
        c.setNome(nome);
        c.setSobrenome(sobrenome);
        c.setTelefone(telefone);
        
        cDao.insertCliente(c);
        
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
}

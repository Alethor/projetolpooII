/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
/**
 *
 * @author Alethor
 */
public class ClienteTableModel extends AbstractTableModel{
    private List<Cliente> clientes = new ArrayList<Cliente>();
    private String[] colunas = {"Id","Nome","Sobrenome","Telefone"};
   
    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
       return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
                
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return clientes.get(linha).getId();
            case 1:
                return clientes.get(linha).getNome();
            case 2:
                return clientes.get(linha).getSobrenome();
            case 3:
                return clientes.get(linha).getTelefone();
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object valor, int linha, int coluna){
        switch(coluna){
            case 0:
                clientes.get(linha).setNome((String) valor);
            case 1:
               clientes.get(linha).setSobrenome((String) valor);
               break;
            case 2:
               clientes.get(linha).setTelefone(Long.parseLong((String) valor));
               break;
              
       }
        this.fireTableRowsUpdated(linha, linha);
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alethor
 */
public class PedidosTableModel extends AbstractTableModel{

     private List<Pedido> pedidos = new ArrayList<Pedido>();

     private String[] colunas = {"Id", "Nome Cliente","Status", "Total"};
   
    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
       switch(coluna){
           case 0:
               return pedidos.get(linha).getIdPedido();
           case 1:
               return pedidos.get(linha).getCliente().getNome();
           case 2:
               return pedidos.get(linha).getStatus().getDescricao();
           case 3:
               return pedidos.get(linha).getTotalPedido();
           default:
               return null;
       }
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    
    
}

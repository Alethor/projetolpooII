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
public class PizzasTableModel extends AbstractTableModel {
    private List<Pizza> pizzas = new ArrayList<Pizza>();
    private String[] colunas = {"ID" ,"Sabor 1"," Sabor 2 ","Forma","Àrea","Preço"};
    
    
    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    @Override
    public int getRowCount() {
        return pizzas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return pizzas.get(linha).getId();
            case 1:
                return pizzas.get(linha).getSabor1().getDescricao();
            case 2:
                return pizzas.get(linha).getSabor2().getDescricao();
            case 3:
                return pizzas.get(linha).getNomeForma();
            case 4:
                return pizzas.get(linha).getArea();
            case 5:
                return pizzas.get(linha).getValor();
            default:
                return null;
                  
        }
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    
   
}

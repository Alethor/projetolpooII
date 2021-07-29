/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alethor
 */
public class SaboresTableModel extends AbstractTableModel{

    private List<Sabor> sabores = new ArrayList<Sabor>();
    private String[] colunas = {"ID","Nome Sabor","Categoria"};
    
    
   @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return sabores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return sabores.get(linha).getId();
            case 1:
                return sabores.get(linha).getDescricao();
            case 2:
                return sabores.get(linha).getCategoria().getDescricao();
            default:
                return null;
        }
    }

    public List<Sabor> getSabores() {
        return sabores;
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class CategoriasTableModel extends AbstractTableModel{
    private List<Categoria> categorias = new ArrayList<>();
    private String[] colunas = {"ID","Descrição","Preço"};

    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    
    @Override
    public int getRowCount() {
        return categorias.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return categorias.get(linha).getId();
            case 1:
                return categorias.get(linha).getDescricao();
            case 2:
                return categorias.get(linha).getPreco();
            default:
                return null;
        }
        
     
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}

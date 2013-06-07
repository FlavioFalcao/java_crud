package views;

import javax.swing.*;

public class Table {
    public static void main(String aargs[])
    {
        String[] colunas = new String[]
        {"Estado", "Cidade"};
        String [][] dados = new String[][]
        {
            {"SP", "São Paulo"},
            {"CE", "Fortaleza"},
            {"RJ", "Rio de Janeiro"},
            {"PR", "Parana"}
        };
        
        JTable jtable = new JTable(dados, colunas);
    }
    
}

package br.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.factory.ConnectionFactory;
import br.com.model.Curso;

/**
 * Esta é minha classe que vai conter meu select referente ao Curso
 * @author Victor Magalhães Pacheco
 */
public class CursoDAO 
{
    public List<Curso> getCursos()
    {
        String sql = "SELECT * FROM cursos";
        List<Curso> cursos = new ArrayList<Curso>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            //enquanto existir dados no banco de dados faça;
            while(rset.next()) {
                Curso curso = new Curso();
                
                //Recupera o id do banco e atribui ele ao objeto
                curso.setId(rset.getInt("id"));
                curso.setNome(rset.getString("nome"));
             
                cursos.add(curso);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            //fecha as conexões
            try {
                if(pstm != null) {
                    pstm.close();
                }
                
                if(conn != null) {
                    conn.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        return cursos;
    }
}

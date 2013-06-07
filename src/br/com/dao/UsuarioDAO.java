package br.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.factory.ConnectionFactory;
import br.com.model.Usuario;

/**
 * Esta é minha classe que vai conter meu CRUD referente ao usuario
 * @author Victor Magalhães Pacheco
 */
public class UsuarioDAO 
{
    public void insert(Usuario usuario)
    {
         String sql = "INSERT INTO usuario (perfil, nome, login, senha, id_curso) VALUES (?, ? ,?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //cria uma conexão com o banco
            conn = ConnectionFactory.createConnection();
            
            //cria um PreparedStatement, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, usuario.getPerfil());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getLogin());
            pstm.setString(4, usuario.getSenha());
            pstm.setInt(5, usuario.getCurso().getId());
            
            pstm.execute();
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
    }
    public void deleteById(int id)
    {
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, id);
            pstm.execute();
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
    }
    
    public void update(Usuario usuario)
    {
        String sql = "UPDATE usuario SET perfil = ?, nome = ?, login = ?, senha = ?, id_curso = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1, usuario.getPerfil());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getLogin());
            pstm.setString(4, usuario.getSenha());
            pstm.setInt(5, usuario.getCurso().getId());
            pstm.setInt(6, usuario.getId());
            
            pstm.execute();
            
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
    }
    
    public List<Usuario> getUsuarios(String where)
    {
		String sql = "";
		if(where.equals("")){
			sql = "SELECT * FROM usuario";
		}else{
			sql = "SELECT * FROM usuario WHERE login LIKE '%"+where+"%'";
		}
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //clase que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            //enquanto existir dados no banco de dados faça;;;
            while(rset.next()) {
                Usuario usuario = new Usuario();
                
                //Recupera o id do banco e atribui ele ao objeto
                usuario.setId(rset.getInt("id"));
                usuario.setNome(rset.getString("nome"));
                usuario.setLogin(rset.getString("login"));
				usuario.setPerfil(rset.getInt("perfil"));
             
                usuarios.add(usuario);
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
        
        return usuarios;
    }
	
	public boolean auth(String login, String senha)
	{
		String sql = "SELECT id, perfil FROM usuario WHERE login = '" +login+"' AND senha = '" + senha + "' LIMIT 1" ;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //clase que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            //enquanto existir dados no banco de dados faça;;;
			int i = 0;
            while(rset.next()) {
                Usuario usuario = new Usuario();
                
                //Recupera o id do banco e atribui ele ao objeto
                usuario.setId(rset.getInt("id"));
				usuario.tipoLogado = rset.getInt("perfil");
				i++;
            }
			
			if(i == 0){
				return false;
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
        
        return true;
	}
}

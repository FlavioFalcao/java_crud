package  br.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.factory.ConnectionFactory;
import br.com.model.Item;
import javax.swing.JOptionPane;

/**
 * Esta é minha classe que vai conter meu CRUD referente ao item
 * @author Victor Magalhães Pacheco
 */
public class ItemDAO 
{
    public void insert(Item item)
    {
         String sql = "INSERT INTO item (tipoItem, tituloObra, autor, editora, anoPublicacao) VALUES (?, ? ,?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //cria uma conexão com o banco
            conn = ConnectionFactory.createConnection();
            
            //cria um PreparedStatement, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, item.getTipoItem());
            pstm.setString(2, item.getTituloObra());
            pstm.setString(3, item.getAutor());
            pstm.setString(4, item.getEditora());
            pstm.setInt(5, item.getAnoPublicacao());
            
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
        String sql = "DELETE FROM item WHERE id = ?";
        
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
    
    public void update(Item item)
    {
        String sql = "UPDATE item SET tipoItem = ?, tituloObra = ?, autor = ?, editora = ?, anoPublicacao = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, item.getTipoItem());
            pstm.setString(2, item.getTituloObra());
            pstm.setString(3, item.getAutor());
            pstm.setString(4, item.getEditora());
            pstm.setInt(5, item.getAnoPublicacao());
            pstm.setInt(6, item.getId());
            
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
    
    public List<Item> getItems(String titutoObra, String tipo)
    {
		String sql = "";
		if(titutoObra.equals("") && tipo.equals("")){
			sql = "SELECT * FROM item";
		}else{
			sql = "SELECT * FROM item WHERE tituloObra LIKE '%"+titutoObra+"%' && tipoItem = '"+tipo+"'";
		}
        List<Item> items = new ArrayList<Item>();
        
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
                Item item = new Item();
                
                //Recupera o id do banco e atribui ele ao objeto
                item.setId(rset.getInt("id"));
				item.setTipoItem(rset.getString("tipoItem"));
				item.setAnoPublicacao(rset.getInt("anoPublicacao"));
				item.setTituloObra(rset.getString("tituloObra"));
				item.setAutor(rset.getString("autor"));
				item.setEditora(rset.getString("editora"));
             
                items.add(item);
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
        
        return items;
    }
	
	public Item returnItem(int id)
	{
		String sql = "SELECT * FROM item WHERE id = " +id+" LIMIT 1" ;
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        //clase que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        Item item = new Item();
		
        try {
            conn = ConnectionFactory.createConnection();
            pstm = conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            	
			
            //enquanto existir dados no banco de dados faça;;;
			int i = 0;
            while(rset.next()) {
                             
                //Recupera o id do banco e atribui ele ao objeto
                item.setId(rset.getInt("id"));
				item.setTipoItem(rset.getString("tipoItem"));
				item.setTituloObra(rset.getString("tituloObra"));
				item.setAutor(rset.getString("autor"));
				item.setEditora(rset.getString("editora"));
				item.setAnoPublicacao(rset.getInt("anoPublicacao"));
				i++;
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
        
        return item;
	}
	
}

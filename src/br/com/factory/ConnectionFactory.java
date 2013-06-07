package br.com.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de conexão com o banco de dados
 * @author Victor Magalhães Pacheco
 */
public class ConnectionFactory 
{
    //nome do usuario do mysql
    private static final String USERNAME = "root";
    //senha do mysql
    private static final String PASSWORD = "87233949";
    
    //dados de caminho, porta e nome da base de dados que irá ser feita a conexão
    private static final String DATABASE_URL = "jdbc:mysql://localhost/poo2";
    
    /**
     * cria a conexão com o banco de dados mysql ultilizando o nome de usuario e senha fornecidos 
     * @return Connection
     */
    public static Connection createConnection() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        
        //cria a conexão com o banco de dados 
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }
	
	public static void main(String[] args) throws Exception{
 
      //Recupera uma conexão com o banco de dados
      Connection con = createConnection();
 
      //Testa se a conexão é nula
      if(con != null){
         System.out.println("Conexão obtida com sucesso!" + con);
         con.close();
      }
 
   }
}

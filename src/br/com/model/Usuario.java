
package br.com.model;

/**
* @author Victor Magalhães Pacheco :)
*/
public class Usuario
{
    private int id;
	/**
	* Int 1 = Administrador, 2 = Aluno 
	*/	
    private int perfil;
    private String nome;
    private String login;
    private String senha;
    private Curso curso;
	
	public static int tipoLogado; 
	public static int idAction;
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public String getNome()
    {
        return this.nome;
    }
    
    public void setLogin(String login)
    {
        this.login = login;
    }
    
    public String getLogin()
    {
        return this.login;
    }
    
    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    
    public String getSenha()
    {
        return this.senha;
    }
    
    public void setCurso(Curso curso)
    {
        this.curso = curso;
    }
    
    public Curso getCurso()
    {
        return this.curso;
    }
    
    public void setPerfil(int perfil)
    {
        this.perfil = perfil;
    }
    
    public int getPerfil()
    {
        return this.perfil;
    }
}

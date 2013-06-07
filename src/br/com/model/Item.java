package br.com.model;

/**
* @author Victor Magalhães Pacheco :)
*/
public class Item 
{
	private int id;
    private String tipoItem;
    private int anoPublicacao;
    private String tituloObra;
    private String autor;
    private String editora;
	
	public static int idAction;
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
        return this.id;
    }
	
	public void setTipoItem(String tipoItem)
    {
        this.tipoItem = tipoItem;
    }
    
    public String getTipoItem()
    {
        return this.tipoItem;
    }
    
    public void setAnoPublicacao(int anoPublicacao)
    {
        this.anoPublicacao = anoPublicacao;
    }
    
    public int getAnoPublicacao()
    {
        return this.anoPublicacao;
    }
    
    public void setTituloObra(String tituloObra)
    {
        this.tituloObra = tituloObra;
    }
    
    public String getTituloObra()
    {
        return this.tituloObra;
    }
    
    public void setAutor(String autor)
    {
        this.autor = autor;
    }
    
    public String getAutor()
    {
        return this.autor;
    }
    
    public void setEditora(String editora)
    {
        this.editora = editora;
    }
    
    public String getEditora()
    {
        return this.editora;
    }
}

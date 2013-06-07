/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import br.com.dao.ItemDAO;
import br.com.model.Item;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author victor
 */
public class CadastroItem extends JFrame
{
    JLabel lb_titulo, lb_tipo_item, lb_ano_publi, lb_titulo_obra, lb_autor, lb_editora;
    JButton bt_cadastro, bt_voltar;
    TextField tf_ano_publi, tf_titulo_obra, tf_autor, tf_editora;
    JComboBox jcb_tipo_item;

    public CadastroItem()
    {
        setTitle("Cadastro de Item no acervo");
		
        setSize(400, 300); 
        setLocation(470, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);
        getContentPane().setBackground(Color.LIGHT_GRAY); 
       
        getContentPane().setLayout(null); 

        lb_titulo = new JLabel("Preencha os campos");
        lb_tipo_item = new JLabel("Tipo do item:");
        lb_ano_publi = new JLabel("Ano publicação:");
        lb_titulo_obra = new JLabel("Título do item:");
        lb_autor = new JLabel("Autor:");
        lb_editora = new JLabel("Editora:");

        tf_ano_publi = new TextField();
        tf_titulo_obra = new TextField();
        tf_autor = new TextField();
        tf_editora = new TextField();

        jcb_tipo_item = new JComboBox();
        jcb_tipo_item.addItem("-- selecione uma opção -- ");
        jcb_tipo_item.addItem("LIVRO");
        jcb_tipo_item.addItem("PERIODICO");
        jcb_tipo_item.addItem("REVISTA");

        bt_cadastro = new JButton("Cadastrar"); 
		bt_voltar = new JButton("Voltar");

        //posicionando os labels componentes na tela
        lb_titulo.setBounds(120, 5, 200, 20);
        lb_tipo_item.setBounds(20, 50, 100, 15);
        lb_ano_publi.setBounds(20, 80, 115, 15);
        lb_titulo_obra.setBounds(20, 110, 110, 15);
        lb_autor.setBounds(20, 140, 70, 15);
        lb_editora.setBounds(20, 170, 70, 15);

        //posicionando o combobox na tela
        jcb_tipo_item.setBounds(140, 45, 230, 25);		

        //posicionando os textfields componentes na tela
        tf_ano_publi.setBounds(140, 75, 230, 25);
        tf_titulo_obra.setBounds(140, 105, 230, 25);
        tf_autor.setBounds(140, 135, 230, 25);
        tf_editora.setBounds(140, 165, 230, 25);		

        bt_cadastro.setBounds(200, 200, 140, 25);
		bt_voltar.setBounds(50, 200, 140, 25);

        //inserindo os labels na tela
        getContentPane().add(lb_titulo); 
        getContentPane().add(lb_tipo_item); 
        getContentPane().add(lb_ano_publi); 
        getContentPane().add(lb_titulo_obra); 
        getContentPane().add(lb_autor); 
        getContentPane().add(lb_editora);

        getContentPane().add(jcb_tipo_item); 
        getContentPane().add(tf_ano_publi); 
        getContentPane().add(tf_titulo_obra); 
        getContentPane().add(tf_autor); 
        getContentPane().add(tf_editora);

        getContentPane().add(bt_cadastro);
		getContentPane().add(bt_voltar);
		
		setVisible(true);
		
		this.actions();
    }

    private void actions()
    {
		//evento quando seleciona no jcombo, desabilita a editora caso for periodico
		ActionListener acJ = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if("eventDisabledFieldEditora".equals(e.getActionCommand())){
					if(jcb_tipo_item.getSelectedItem().equals("PERIODICO")){
						lb_editora.setVisible(false);
						tf_editora.setVisible(false);
					}else{
						lb_editora.setVisible(true);
						tf_editora.setVisible(true);
					}
                }
            }
        };
        jcb_tipo_item.setActionCommand("eventDisabledFieldEditora");
        jcb_tipo_item.addActionListener(acJ);
		
		//evento de cadastro
		ActionListener ac = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if("eventoCadastro".equals(e.getActionCommand())){
                    					
					if(jcb_tipo_item.getSelectedItem().equals("-- selecione uma opção -- ")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir o tipo de item", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else if(tf_titulo_obra.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir o titulo", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else if(tf_ano_publi.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir o ano da publicação", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else if(tf_autor.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir o autor", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else{
						
						boolean cadastra = true;
						if(jcb_tipo_item.getSelectedItem().equals("LIVRO") || jcb_tipo_item.getSelectedItem().equals("REVISTA")){
							if(tf_editora.getText().equals("")){
								JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir a editora", "Aviso", JOptionPane.INFORMATION_MESSAGE);
								cadastra = false;
							}
						}
								
						if(cadastra){
							Item item = new Item();
							item.setTipoItem(jcb_tipo_item.getSelectedItem().toString());
							item.setAnoPublicacao(Integer.parseInt(tf_ano_publi.getText()));
							item.setTituloObra(tf_titulo_obra.getText());
							item.setAutor(tf_autor.getText());
							item.setEditora(tf_editora.getText());

							ItemDAO itemDao = new ItemDAO();
							itemDao.insert(item);

							tf_ano_publi.setText("");
							tf_titulo_obra.setText("");
							tf_autor.setText("");
							tf_editora.setText("");
							jcb_tipo_item.setSelectedItem("-- selecione uma opção -- ");
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Sucess", JOptionPane.INFORMATION_MESSAGE);
						}
						
					}
                }
            }
        };
        
        bt_cadastro.setActionCommand("eventoCadastro");
        bt_cadastro.addActionListener(ac);
		
		//evento de voltar
		ActionListener actionVoltar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if("eventoVoltar".equals(e.getActionCommand())){
					dispose();
					new ConsultaItem();							
                }
            }
        };
        
        bt_voltar.setActionCommand("eventoVoltar");
        bt_voltar.addActionListener(actionVoltar);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import br.com.dao.CursoDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import br.com.factory.ConnectionFactory;
import br.com.model.Usuario;
import br.com.dao.UsuarioDAO;
import br.com.model.Curso;

/**
 *
 * @author victor
 */
public class CadastroUser extends JFrame
{
    JLabel lb_titulo, lb_nome, lb_curso, lb_login, lb_senha;
    JButton bt_cadastro, bt_voltar;
    TextField tf_nome, tf_login;
    JPasswordField pf_senha;
    JComboBox jcb_curso;

    public CadastroUser()
    {

        setTitle("Cadastro de usuário"); //setando o titulo
        setSize(400, 250); //setando o tamanho da tela em pixel largura, altura
        setLocation(470, 250);	//setando para a tela ficar no meio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setando para quando clicar no fechar da janela, para a aplicação 
        setResizable(false); //aqui estou dizendo que não pode redimensionar a tela
        getContentPane().setBackground(Color.LIGHT_GRAY); 
		
		getContentPane().setLayout(null);

        lb_titulo = new JLabel("Preencha os campos"); //criando um label
        lb_nome = new JLabel("Nome: ");
        lb_curso = new JLabel("Curso: ");
        lb_login = new JLabel("Login: ");
        lb_senha = new JLabel("Senha: ");

        tf_nome = new TextField(); //criando um input
        tf_login = new TextField(); //criando um input
		
        pf_senha = new JPasswordField(); //criando um password

				
        jcb_curso = new JComboBox(); //select do curso
        jcb_curso.addItem("-- selecione uma opção -- ");
		
		
		CursoDAO cursoDAO = new CursoDAO();
		for(Curso c : cursoDAO.getCursos()){
			int id = c.getId();
			String nome = c.getNome();
			jcb_curso.addItem(id + "-" + nome);
		}

        bt_cadastro = new JButton("Cadastrar"); //criando um botão
		bt_voltar = new JButton("Voltar");

        //posicionando os labels componentes na tela
        lb_titulo.setBounds(120, 5, 200, 20);
        lb_nome.setBounds(50, 50, 50, 15);
        lb_login.setBounds(50, 80, 50, 15);
        lb_senha.setBounds(50, 110, 60, 15);
        lb_curso.setBounds(50, 140, 50, 15);

        //posicionando os textfields componentes na tela
        tf_nome.setBounds(110, 45, 230, 25);
        tf_login.setBounds(110, 75, 230, 25);
        pf_senha.setBounds(110, 105, 230, 25);
        jcb_curso.setBounds(110, 135, 230, 25);

        bt_cadastro.setBounds(200, 180, 140, 25);
		bt_voltar.setBounds(50, 180, 140, 25);

        getContentPane().add(lb_titulo); 
        getContentPane().add(lb_nome); 	
        getContentPane().add(lb_curso); 
        getContentPane().add(lb_login);
        getContentPane().add(lb_senha);

        getContentPane().add(tf_nome); 	
        getContentPane().add(tf_login);
        getContentPane().add(pf_senha);
        getContentPane().add(jcb_curso); 

        getContentPane().add(bt_cadastro);
		getContentPane().add(bt_voltar);
        		
		setVisible(true);
		
		this.actions();
    }
	
	private void actions()
	{
		ActionListener ac = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if("eventoCadastro".equals(e.getActionCommand())){
                    					
					if(tf_nome.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir o nome", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else if(tf_login.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir o login", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else if(pf_senha.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir a senha", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else if(jcb_curso.getSelectedItem().equals("-- selecione uma opção -- ")){
						JOptionPane.showMessageDialog(null, "Por favor, é necessario inserir o curso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}else{
										
						Curso curso = new Curso();
						String items = (String) jcb_curso.getSelectedItem().toString().substring(0,1);
						Integer item = Integer.parseInt(items);
						curso.setId(item);

						Usuario usuario = new Usuario();
						usuario.setPerfil(2);
						usuario.setNome(tf_nome.getText());
						usuario.setLogin(tf_login.getText());
						usuario.setSenha(pf_senha.getText());
						usuario.setCurso(curso);

						UsuarioDAO usuDao = new UsuarioDAO();
						usuDao.insert(usuario);
						
						tf_nome.setText("");
						tf_login.setText("");
						pf_senha.setText("");
						jcb_curso.setSelectedItem("-- selecione uma opção -- ");
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso", "Sucess", JOptionPane.INFORMATION_MESSAGE);
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
					new ConsultaUser();							
                }
            }
        };
        
        bt_voltar.setActionCommand("eventoVoltar");
        bt_voltar.addActionListener(actionVoltar);
	}
}

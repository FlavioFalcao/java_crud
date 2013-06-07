/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import br.com.dao.UsuarioDAO;
import br.com.model.Curso;
import br.com.model.Usuario;
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

/**
 * PARTE DE LOGIN :D
 * @author Victor Magalhães Pacheco :)
 */

public class Login extends JFrame
{
    JLabel lb_titulo, lb_login, lb_senha, lb_logotipo;
    JButton bt_logar;
    TextField tf_login;
    JPasswordField pf_senha;
    ImageIcon imagem_logo;

    public Login()
    {	
        setTitle("Login Biblioteca"); 
        setSize(400, 300); 
        setLocation(470, 250);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); 
        getContentPane().setBackground(Color.LIGHT_GRAY); 
       
        getContentPane().setLayout(null); 

        lb_titulo = new JLabel("Entre com seus dados"); //criando um label
        lb_login = new JLabel("Login: ");
        lb_senha = new JLabel("Senha: "); 

        tf_login = new TextField(); //criando um input
        pf_senha = new JPasswordField(); //criando um password

        bt_logar = new JButton("Logar"); //criando um botão

        //posicionando os componentes na tela
        lb_titulo.setBounds(120, 5, 200, 20);
        lb_login.setBounds(70, 50, 50, 15);
        tf_login.setBounds(130, 45, 200, 25);
        lb_senha.setBounds(70, 80, 60, 15);
        pf_senha.setBounds(130, 75, 200, 25);
        bt_logar.setBounds(150, 110, 120, 25);

        getContentPane().add(lb_titulo); 
        getContentPane().add(lb_login); 	
        getContentPane().add(tf_login); 
        getContentPane().add(lb_senha);
        getContentPane().add(pf_senha);
        getContentPane().add(bt_logar);
				
		setVisible(true);
		
		this.actions();
    }
	
	private void actions()
	{
		ActionListener ac = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if("eventoLogin".equals(e.getActionCommand())){
                    
                    UsuarioDAO usuDao = new UsuarioDAO();
                    boolean retorno = usuDao.auth(tf_login.getText(), pf_senha.getText());                  
                    
					if(retorno){
						dispose();
						new ConsultaItem();						
						
					}else{
						JOptionPane.showMessageDialog(null, "Dados inválidos", "Desculpe", JOptionPane.INFORMATION_MESSAGE);
						tf_login.setText("");
						pf_senha.setText("");
					}
				}
            }
        };
        
        bt_logar.setActionCommand("eventoLogin");
        bt_logar.addActionListener(ac);
	}
}

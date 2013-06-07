/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author victor
 */
public class ConsultaUser extends JFrame
{	
    JLabel  lb_pesquisar, lb_titulo;
    JButton bt_novo, bt_consultar, bt_delete, bt_voltar;
    TextField tf_pesquisar;
	JScrollPane scrollPane;
	JTable tabela;
	DefaultTableModel modeloTable; 

    public ConsultaUser()
    {
        setTitle("Cadastro de usuário");
        setSize(600, 400); 
        setLocation(370, 220);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false); 
        getContentPane().setBackground(Color.LIGHT_GRAY); 
       
        getContentPane().setLayout(null); 

        lb_titulo = new JLabel("Consulta de usuários"); 
        lb_pesquisar = new JLabel("Login: ");

        tf_pesquisar = new TextField(); 


        bt_consultar = new JButton("Consultar");
        bt_novo = new JButton("Novo"); 
		bt_delete = new JButton("Deletar"); 
		bt_voltar = new JButton("Voltar");

		modeloTable = new DefaultTableModel();  
		/* Set os nomes das colunas que eu quero na tabela */  
		modeloTable.setColumnIdentifiers(new String[] { "Id", "Nome", "Login", "Perfil" });  
		/* Cria a tabela passando como parametro o modelo da tabela criado acima. */  
		tabela = new JTable(modeloTable);
        

        //posicionando os labels componentes na tela
        lb_titulo.setBounds(230, 5, 200, 20);
        lb_pesquisar.setBounds(70, 50, 80, 15);

        //posicionando os textfields componentes na tela
        tf_pesquisar.setBounds(160, 45, 230, 25);

        bt_consultar.setBounds(395, 45, 140, 25);
        bt_novo.setBounds(350, 330, 140, 25);
		bt_delete.setBounds(250, 330, 90, 25);
		bt_voltar.setBounds(100, 330, 140, 25);
		bt_delete.setVisible(false);
		
		tabela.setBounds(40, 100, 510, 200);
		
		int posicao = 0;
		UsuarioDAO usuarioDao = new UsuarioDAO();
		for(Usuario u : usuarioDao.getUsuarios("")){
			String perfil = u.getPerfil() == 1 ? "Administrador" : "Aluno";
			modeloTable.insertRow(posicao, new Object[] {u.getId(), u.getNome(), u.getLogin(), perfil });
			posicao++;
		}
		
		scrollPane = new JScrollPane(tabela);

		scrollPane.setBounds(40, 100, 500, 200);
		
        getContentPane().add(lb_titulo); 
        getContentPane().add(lb_pesquisar);

        getContentPane().add(tf_pesquisar);

		getContentPane().add(bt_voltar);
        getContentPane().add(bt_consultar);
		getContentPane().add(bt_delete);
		bt_delete.setBackground(Color.RED);
		bt_delete.setForeground(Color.BLACK);
		
		//aqui verifico se o usuario que está logado é administrador, se for 1 é administrador e pode inserir um novo usuario
		if(Usuario.tipoLogado == 1){
			getContentPane().add(bt_novo);
		}
        
		getContentPane().add(scrollPane);
		
		setVisible(true);
		
		this.actions();
    }
	
	private void actions()
	{
		//ação de  selecionar a linha
		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting()) {
					if(Usuario.tipoLogado  == 1) {
						bt_delete.setVisible(true);
						TableModel mode = tabela.getModel();
						Integer id = (Integer) mode.getValueAt(tabela.getSelectedRow(), 0);
						Usuario.idAction = id;
					}
				}
			}
		});
		
		//trecho de acão de chamar o form de novo usuario
		ActionListener ac = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if("eventCallRegisterUser".equals(e.getActionCommand())){
					dispose();
					new CadastroUser();
				}
            }
        };
        bt_novo.setActionCommand("eventCallRegisterUser");
        bt_novo.addActionListener(ac);
		
		//trecho de acão de chamar o form de novo usuario
		ActionListener acPesquisa = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if("eventCallSearch".equals(e.getActionCommand())){
					modeloTable.setNumRows(0);
					
					int posicao = 0;
					UsuarioDAO usuarioDao = new UsuarioDAO();
					for(Usuario u : usuarioDao.getUsuarios(tf_pesquisar.getText())){
						String perfil = u.getPerfil() == 1 ? "Administrador" : "Aluno";
						modeloTable.insertRow(posicao, new Object[] {u.getId(), u.getNome(), u.getLogin(), perfil });
						posicao++;
					}
					
				}
            }
        };
        bt_consultar.setActionCommand("eventCallSearch");
        bt_consultar.addActionListener(acPesquisa);
		
		//action de excluir item
		ActionListener acDelete = new ActionListener() {
			private int dialogButton;

			public void actionPerformed(ActionEvent e) {
				if ("eventCallDelete".equals(e.getActionCommand())) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o item de id: " + Usuario.idAction + "?", "Atenção", dialogButton);
					if (dialogResult == JOptionPane.YES_OPTION) {
						UsuarioDAO usuarioDao = new UsuarioDAO();
						usuarioDao.deleteById(Usuario.idAction);

						modeloTable.setNumRows(0);

						int posicao = 0;
						for (Usuario u : usuarioDao.getUsuarios("")) {
							String perfil = u.getPerfil() == 1 ? "Administrador" : "Aluno";
							modeloTable.insertRow(posicao, new Object[] {u.getId(), u.getNome(), u.getLogin(), perfil });
							posicao++;
						}
					}
				}
			}
		};
		bt_delete.setActionCommand("eventCallDelete");
		bt_delete.addActionListener(acDelete);
		
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

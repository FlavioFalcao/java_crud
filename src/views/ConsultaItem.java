/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import br.com.dao.ItemDAO;
import br.com.model.Item;
import br.com.model.Usuario;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class ConsultaItem extends JFrame {

	JLabel lb_tipoItem, lb_titulo, lb_tituloPesquisa;
	JButton bt_listaUser, bt_novo, bt_consultar, bt_delete, bt_update;
	TextField tf_tituloPesquisa;
	JScrollPane scrollPane;
	JTable tabela;
	DefaultTableModel modeloTable;
	JComboBox jcb_tipoItem;

	public ConsultaItem() {
		setTitle("Consulta de items");
		
		setSize(600, 400);
		setLocation(370, 220);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		getContentPane().setLayout(null); 

		lb_titulo = new JLabel("Consulta de Items"); 
		lb_tipoItem = new JLabel("Tipo Item: ");
		lb_tituloPesquisa = new JLabel("Titulo: ");

		tf_tituloPesquisa = new TextField();

		jcb_tipoItem = new JComboBox(); 
		jcb_tipoItem.addItem("-- TODOS --");
		jcb_tipoItem.addItem("LIVRO");
		jcb_tipoItem.addItem("PERIODICO");
		jcb_tipoItem.addItem("REVISTA");


		bt_consultar = new JButton("Consultar"); 
		bt_listaUser = new JButton("Listar Usuário"); 
		bt_novo = new JButton("Novo Item");
		bt_delete = new JButton("Deletar"); 
		bt_update = new JButton("Editar");

		modeloTable = new DefaultTableModel();
		
		/**
		 * @author Victor Magalhães Pacheco :)
		 * Set os nomes das colunas que eu quero na tabela 
		 */
		modeloTable.setColumnIdentifiers(new String[]{"ID", "Titulo", "Tipo"});
		
		tabela = new JTable(modeloTable);


		//posicionando os labels componentes na tela
		lb_titulo.setBounds(230, 5, 200, 20);
		lb_tipoItem.setBounds(20, 50, 80, 15);
		lb_tituloPesquisa.setBounds(300, 52, 60, 15);

		tf_tituloPesquisa.setBounds(360, 50, 160, 23);

		jcb_tipoItem.setBounds(100, 50, 190, 20);


		bt_consultar.setBounds(395, 85, 140, 25);

		bt_listaUser.setBounds(300, 330, 140, 25);

		bt_novo.setBounds(450, 330, 140, 25);

		bt_delete.setBounds(150, 330, 90, 25);
		bt_delete.setVisible(false);
		
		bt_update.setBounds(50, 330, 90, 25);
		bt_update.setVisible(false);

		tabela.setBounds(20, 120, 550, 200);


		int posicao = 0;
		ItemDAO itemDao = new ItemDAO();
		for (Item i : itemDao.getItems("", "")) {
			modeloTable.insertRow(posicao, new Object[]{i.getId(), i.getTituloObra(), i.getTipoItem()});
			posicao++;
		}

		scrollPane = new JScrollPane(tabela);


		scrollPane.setBounds(20, 120, 560, 200);

		getContentPane().add(lb_titulo);
		getContentPane().add(lb_tipoItem);
		getContentPane().add(lb_tituloPesquisa);

		getContentPane().add(tf_tituloPesquisa);

		getContentPane().add(jcb_tipoItem);

		getContentPane().add(bt_consultar);
		getContentPane().add(bt_listaUser);
		
		if(Usuario.tipoLogado == 1) {
			getContentPane().add(bt_novo);
		}
		
		getContentPane().add(bt_delete);
		bt_delete.setBackground(Color.RED);
		bt_delete.setForeground(Color.BLACK);
		
		getContentPane().add(bt_update);
		bt_update.setBackground(Color.YELLOW);
		bt_update.setForeground(Color.BLACK);

		getContentPane().add(scrollPane);
		
		setVisible(true);
		
		this.actions();
	}

	/**
	* @author Victor Magalhães Pacheco :)
	* Minhas ações
	*/
	private void actions()
	{
		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting()) {
					if(Usuario.tipoLogado == 1){
						bt_delete.setVisible(true);
						bt_update.setVisible(true);
						TableModel mode = tabela.getModel();
						Integer id = (Integer) mode.getValueAt(tabela.getSelectedRow(), 0);
						Item.idAction = id;
					}
				}
			}
		});
		
		//action de chamar a lista de usuario
		ActionListener acListUser = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("eventCallListUser".equals(e.getActionCommand())) {
					dispose();
					new ConsultaUser();
				}
			}
		};
		bt_listaUser.setActionCommand("eventCallListUser");
		bt_listaUser.addActionListener(acListUser);

		//trecho de acão de chamar o form de novo usuario
		ActionListener ac = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("eventCallRegisterItem".equals(e.getActionCommand())) {
					dispose();
					new CadastroItem();
				}
			}
		};
		bt_novo.setActionCommand("eventCallRegisterItem");
		bt_novo.addActionListener(ac);

		//action de procurar na listagem
		ActionListener acPesquisa = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("eventCallSearch".equals(e.getActionCommand())) {
					modeloTable.setNumRows(0);

					int posicao = 0;
					ItemDAO itemDao = new ItemDAO();
					
					String tituloPesquisa = tf_tituloPesquisa.getText().equals("") ? "" : tf_tituloPesquisa.getText();
					String itemPesquisa = jcb_tipoItem.getSelectedItem().toString().equals("-- TODOS --") ? "" : jcb_tipoItem.getSelectedItem().toString();
					
					for (Item i : itemDao.getItems(tituloPesquisa, itemPesquisa)) {
						modeloTable.insertRow(posicao, new Object[]{i.getId(), i.getTituloObra(), i.getTipoItem()});
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
					int dialogResult = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o item de id: " + Item.idAction + "?", "Atenção", dialogButton);
					if (dialogResult == JOptionPane.YES_OPTION) {
						ItemDAO itemDao = new ItemDAO();
						itemDao.deleteById(Item.idAction);

						modeloTable.setNumRows(0);

						int posicao = 0;
						for (Item i : itemDao.getItems("", "")) {
							modeloTable.insertRow(posicao, new Object[]{i.getId(), i.getTituloObra(), i.getTipoItem()});
							posicao++;
						}
					}
				}
			}
		};
		bt_delete.setActionCommand("eventCallDelete");
		bt_delete.addActionListener(acDelete);
		
		//action de chamar o form de item para editar
		ActionListener acCallFormUpdate = new ActionListener() {
			private int dialogButton;

			public void actionPerformed(ActionEvent e) {
				if ("acCallFormUpdate".equals(e.getActionCommand())) {
					dispose();
					new UpdateItem();
				}
			}
		};
		bt_update.setActionCommand("acCallFormUpdate");
		bt_update.addActionListener(acCallFormUpdate);
	}
}

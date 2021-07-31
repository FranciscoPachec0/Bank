import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Deposit implements ActionListener {

	private GUI parent;
	private double depositar;
	private JFormattedTextField deposito;
	private Account atual;
	
	public Deposit(GUI parent) {
		
		this.parent = parent;
		atual = parent.getAccount();
		
		JFrame deposit = new JFrame();
		
		
		JLabel valor = new JLabel("Insira o montante que deseja depositar");
		deposito = new JFormattedTextField();
		
		JButton dep = new JButton("Depositar");
		dep.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(valor);
		panel.add(deposito);
		panel.add(dep);
		
		
		//frame.setVisible(false);
		deposit.add(panel, BorderLayout.CENTER);
		deposit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    deposit.addWindowListener(new WindowAdapter() {
	    	
	    	@Override
	        public void windowClosing(WindowEvent arg0) {
	    		parent.getButtonD().setEnabled(true);
				parent.getButtonW().setEnabled(true);
				parent.getButtonVT().setEnabled(true);
				parent.getButtonCI().setEnabled(true);
				
			    try{
			    	Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teste","root","Anajorge31");
			      
			        PreparedStatement preparedStatement = con.prepareCall("update users set Money = ? where username = ?;");
			        preparedStatement.setDouble(1, atual.getClient_money());
			        preparedStatement.setString(2, atual.getUsername().trim());
			        preparedStatement.executeUpdate();
			        
			        con.close();
			    } catch(Exception e){
			        System.out.println(e);			    
			    }
	    	}

	    });
		deposit.setTitle("Depositar");
		deposit.pack();
		deposit.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = deposito.getText();
		try {
			depositar = Double.parseDouble(str);
			//System.out.println("Depositar = " + depositar);
			//System.out.println("Dinheiro atual = " + atual.getClient_money());
			atual.setClient_money(atual.getClient_money() + depositar);
			parent.getMotantLabel().setText("Balance = " + new DecimalFormat("#,##0.00").format(atual.getClient_money()));
			Transicao.transicao(atual, 0, depositar);
		}
		catch(Exception a) {
			JOptionPane.showMessageDialog(null, "Montante Inválido");
		}		
	}
	
}

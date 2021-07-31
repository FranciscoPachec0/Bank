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

public class Withdraw implements ActionListener {
	
	private GUI parent;
	private Account atual;
	private JFormattedTextField levantamento;
	private double levantar;
	
	public Withdraw(GUI parent) {
		
		this.parent = parent;
		atual = parent.getAccount();
		
		JFrame frame = new JFrame();		
		
		JLabel valor = new JLabel("Insira o montante que deseja levantar");
		levantamento = new JFormattedTextField();
		
		JButton levant = new JButton("Levantar");
		levant.addActionListener(this);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(valor);
		panel.add(levantamento);
		panel.add(levant);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.addWindowListener(new WindowAdapter() {
	    	
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
		frame.setTitle("Levantamento");
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = levantamento.getText();
		try {
			levantar = Double.parseDouble(str);
			//System.out.println("Levantar = " + levantar);
		}
		catch(Exception a) {
			JOptionPane.showMessageDialog(null, "Montante Inválido");
		}
		
		if (atual.getClient_money() < levantar) {
			JOptionPane.showMessageDialog(null, "Não possui esse montante");
		} else {
			atual.setClient_money(atual.getClient_money() - levantar);
			System.out.println("Dinheiro atual = " + atual.getClient_money());
			parent.getMotantLabel().setText("Balance = " + new DecimalFormat("#,##0.00").format(atual.getClient_money()));
			Transicao.transicao(atual, 1, levantar);
		}
	}
	
}


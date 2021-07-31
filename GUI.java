import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener{
	
	private JLabel label;
	private JFrame frame;
	private Account atual;
	private JButton buttonD;
	private JButton buttonW;
	private JButton buttonVT;
	private JButton buttonCI;
	
	public JButton getButtonD() {
		return buttonD;
	}

	public void setButtonD(JButton buttonD) {
		this.buttonD = buttonD;
	}

	public JButton getButtonW() {
		return buttonW;
	}

	public void setButtonW(JButton buttonW) {
		this.buttonW = buttonW;
	}

	public JButton getButtonVT() {
		return buttonVT;
	}

	public void setButtonVT(JButton buttonVT) {
		this.buttonVT = buttonVT;
	}

	public JButton getButtonCI() {
		return buttonCI;
	}

	public void setButtonCI(JButton buttonCI) {
		this.buttonCI = buttonCI;
	}
	
	
	
	public GUI(Account atual) {
		this.atual = atual;
		
		frame = new JFrame();
		
		buttonW = new JButton("Withdraw");
		buttonW.addActionListener(this);
		buttonD = new JButton("Deposit");
		buttonD.addActionListener(this);
		buttonVT = new JButton("View Transactions");
		buttonVT.addActionListener(this);
		buttonCI = new JButton("Calculate Interest");
		buttonCI.addActionListener(this);
		JLabel bemvindo = new JLabel("Bem Vindo " + atual.getClient_name()); 
		label = new JLabel("Balance = " + atual.getClient_money());
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(bemvindo);
		panel.add(label);
		panel.add(buttonW);
		panel.add(buttonD);
		panel.add(buttonVT);
		panel.add(buttonCI);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Banco");
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Withdraw")) {
			buttonD.setEnabled(false);
			buttonW.setEnabled(false);
			buttonVT.setEnabled(false);
			buttonCI.setEnabled(false);
			new Withdraw(this);
		} else if(e.getActionCommand().equals("Deposit")) {
			buttonD.setEnabled(false);
			buttonW.setEnabled(false);
			buttonVT.setEnabled(false);
			buttonCI.setEnabled(false);
			new Deposit(this);
		} else if(e.getActionCommand().equals("View Transactions")) {
			buttonD.setEnabled(false);
			buttonW.setEnabled(false);
			buttonVT.setEnabled(false);
			buttonCI.setEnabled(false);
			new ViewTran(this);
		} else if(e.getActionCommand().equals("Calculate Interest")) {
			buttonD.setEnabled(false);
			buttonW.setEnabled(false);
			buttonVT.setEnabled(false);
			buttonCI.setEnabled(false);
			new CalcInterest(this);
		}
	}
	
	public Account getAccount() {
		return atual;
	}
	
	public JLabel getMotantLabel() {
		return label;
	}

	public JFrame getFrame() {
		return frame;
	}
	
}

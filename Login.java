import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener{
	
	private JTextField login;
	private JPasswordField password;
	private JFrame frame;
	private String username;
	private String pass;
	private boolean login_valido;
	private Account utilizador;

	public Login() {
		frame = new JFrame();
		
		JLabel labelogin = new JLabel("Username: ");
		JLabel labelpass = new JLabel("Password: ");
		login = new JTextField();
		password = new JPasswordField();
		labelogin.setLabelFor(login);
		labelpass.setLabelFor(password);
		
		//System.out.println("text = " + login.getText());
		
		JButton Buttonlog = new JButton("Login");
		Buttonlog.addActionListener(this);
		JButton ButtonCanc = new JButton("Cancel");
		ButtonCanc.addActionListener(this);
		JButton Buttonadmin = new JButton("Admin");
		Buttonadmin.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 30, 100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(labelogin);
		panel.add(login);
		panel.add(labelpass);
		panel.add(password);
		panel.add(Buttonlog);
		panel.add(ButtonCanc);
		panel.add(Buttonadmin);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		frame.pack();
		frame.setVisible(true);
	}
	
	public String getPass() {
		return pass;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setLogin_valido(boolean login_valido) {
		this.login_valido = login_valido;
	}
	
	public Account getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Account utilizador) {
		this.utilizador = utilizador;
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Login")) {
			username = login.getText();
			pass = password.getText();
			new MysqlCon(this);
			if(login_valido) {
				frame.setVisible(false);
				//System.out.println("Login -> Nome cliente: " + utilizador.getClient_name() + ", Dinheiro cliente: " + utilizador.getClient_money() + ", Username = " + utilizador.getUsername());
				new GUI(utilizador);
			} else {
				JOptionPane.showMessageDialog(frame, "Login Inválido");
			}
		} else if (e.getActionCommand().equals("Cancel")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Admin")) {
			username = "francisco";
			pass = "1234";
			new MysqlCon(this);
			frame.setVisible(false);
			new GUI(utilizador);
		}
	}
	
	
}

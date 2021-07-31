import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CalcInterest implements ActionListener{

	private int anos;
	private Account atual;
	
	public CalcInterest(GUI parent) {
		atual = parent.getAccount();
		
		JFrame frame = new JFrame();
		
		JLabel valor = new JLabel("Insira o número de anos");
		SpinnerModel value =  new SpinnerNumberModel(0, //initial value  
	                								 0, //minimum value  
	                								 100, //maximum value  
	                								 1); //step  
	    JSpinner spinner = new JSpinner(value);   
        spinner.setBounds(100,100,50,30);  
        
        JButton calcular = new JButton("Calcular");
        calcular.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(valor);
		panel.add(spinner); 
		panel.add(calcular);

		spinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				//System.out.println("Value : " + ((JSpinner)e.getSource()).getValue()); 
				anos = (Integer) spinner.getValue();
			}
			
		});

		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.addWindowListener(new WindowAdapter() {
	    	
	    	@Override
	        public void windowClosing(WindowEvent arg0) {
	    		parent.getButtonD().setEnabled(true);
				parent.getButtonW().setEnabled(true);
				parent.getButtonVT().setEnabled(true);
				parent.getButtonCI().setEnabled(true);
	        }



	    });
		frame.setTitle("Depositar");
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Daqui a " + anos + " anos terá " + new DecimalFormat("#,##0.00").format(calcularjuros(anos,atual)) + "€");
	}
	
	private static double calcularjuros(int anos, Account atual) {
		double juros = (atual.getClient_money() * 0.185 * anos);
		double total = juros + atual.getClient_money();
		//System.out.println("No fim dos " + anos + "anos teria " + total + "euros");
		System.out.println("Juros = " + juros);
		return total;
	}

}

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ViewTran {

	private int i=1;
	private Account atual;
	private JTextArea transacoes;
	
	public ViewTran(GUI parent) {
		atual = parent.getAccount(); 
		
		JFrame frame = new JFrame();		
		
		transacoes = new JTextArea();
		Iterator<Extrato> iterator = atual.extrato.iterator();
		while(iterator.hasNext()){
		  Extrato element = iterator.next();
		  transacoes.append(i + " transicao: " + element+"\n");
		  i++;
		}
		
		JButton pdf = new JButton("Extrair para PDF");
		//falta por o ActionListener
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(transacoes);
		frame.add(pdf);
		
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
		frame.setTitle("Extrato");
		frame.pack();
		frame.setVisible(true);
		
		
		
	}

}

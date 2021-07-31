import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;

public class Transicao {

	private static String acao;
	
	public static void transicao (Account atual, int operacao, double montante) {
    	if (operacao == 0) acao = "deposito";
    	else acao = "levantamento";
    		
		Extrato temp = new Extrato();
    	temp.setMoney(montante);
    	temp.setUsername(atual.getUsername());
    	temp.setAcao(acao);
    	long millis=System.currentTimeMillis();
        Date date = new Date(millis);
    	//java.sql.Time date = new java.sql.Time(Calendar.getInstance().getTime().getTime());
    	//java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    	//java.sql.Date date = new java.sql.Date(utilDate.getTime());
        temp.setData(date);
        //System.out.println("Date = " + date);
    	atual.extrato.add(temp);
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teste","root","Anajorge31");
	        PreparedStatement preparedStatement = con.prepareCall("INSERT INTO Transactions (Username, Transaction, Money) VALUES (?, ?, ?)");
	        preparedStatement.setString(1, atual.getUsername().trim());
	        preparedStatement.setString(2, acao.trim());
	        preparedStatement.setDouble(3, montante);
	        preparedStatement.execute();
	        con.close();
	    } catch(Exception e){
	        System.out.println(e);
	    }
		
	}
}

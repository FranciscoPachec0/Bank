import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlCon {
	
	//private Login parent;
	private String login_user;
	private String login_pass;
	private Account temp;
	//private String FirstName;
	//private String LastName;
	
	public MysqlCon(Login parent) {
		//this.parent = parent;
		login_user = parent.getUsername();
		login_pass = parent.getPass();
		//System.out.println("Login_user = " + login_user + ", Login_pass = " + login_pass);
		
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/teste","root","Anajorge31");
	        //esta Query é para selecionar o utilizador correspondente da tabela Users
	        PreparedStatement preparedStatement = con.prepareCall("SELECT * FROM Users WHERE Username = ?");
	        preparedStatement.setString(1, login_user.trim());
	        ResultSet rs = preparedStatement.executeQuery();
	        while(rs.next()) { //neste caso este while nao interessa pois no maximo existe 1 row no sql correspondete ao login_user(username) passado pelo utilizador
		      	String password = rs.getString(7);
	        	//System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getInt(3)+"  "+rs.getInt(4)+"  "+rs.getInt(5)+"  "+rs.getString(6)+"  "+rs.getString(7));
		        if (login_pass.equals(password)) { 
		        	parent.setLogin_valido(true);
		        	temp = new Account();
		        	//FirstName = rs.getString(1);
		        	//LastName = rs.getString(2);
		        	temp.setClient_name(rs.getString(1)+" "+rs.getString(2));
		        	temp.setClient_money(rs.getDouble(5));
		        	temp.setUsername(rs.getString(6));
		        	//System.out.println("Nome cliente: " + rs.getString(1)+" "+rs.getString(2) + ", Dinheiro cliente: " + rs.getInt(5) + ", Username = " + rs.getString(6));
		        	parent.setUtilizador(temp);
		        }
	        }
	        //esta Query é para selecionar todas as transacoes do utilizador
	        preparedStatement = con.prepareCall("SELECT * FROM Transactions WHERE Username = ? ORDER BY Data asc");
	        preparedStatement.setString(1, login_user.trim());
	        rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	Extrato temp = new Extrato();
	        	temp.setMoney(rs.getDouble(4));
	        	temp.setUsername(login_user);
	        	temp.setAcao(rs.getString(3));
	        	temp.setData(rs.getDate(5));
	        	parent.getUtilizador().extrato.add(temp);
	        	//System.out.println(temp);
	        }
	        con.close();
	        
	    } catch(Exception e){
	        System.out.println(e);
	    }
	}
}

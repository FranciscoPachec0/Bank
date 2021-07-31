import java.util.Date;

public class Extrato {
	private String username;
	private String acao;
	private Double money;
	private Date data;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		//return "O Sr " + username + " realizou um " + acao + " no valor de " + money + "€ no dia " + data;
		//return " Username = " + username + " Acao = " + acao + " Money = " + money + " Data = " + data;
		return username+" "+ acao+" "+money+" "+data;
	}

}

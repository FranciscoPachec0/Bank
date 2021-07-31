import java.util.*;


//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;

public class Account {
	private String client_name;
	private double client_money;
	private String username;
	Queue<Extrato> extrato = new LinkedList<>();


	Account() {
		
	}
	
	Account(String name, String username) {
		client_name = name;
		this.username = username;
		client_money = 0;
	}
	
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public double getClient_money() {
		return client_money;
	}
	public void setClient_money(double client_money) {
		this.client_money = client_money;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username= username;
	}
}

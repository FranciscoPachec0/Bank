
public class Pair<T1, T2> {
	private T1 acao;
	private T2 valor;

	
	Pair(){
		
	}
	
	Pair(T1 acao, T2 valor) {
		this.acao = acao;
		this.valor = valor;
	}

	public T1 getAcao() {
		return acao;
	}

	public void setAcao(T1 acao) {
		this.acao = acao;
	}

	public T2 getValor() {
		return valor;
	}

	public void setValor(T2 valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "acao = " + acao + ", valor = " + valor;
	}
	
	
}

package negocio.exercicio01prof;

public class Produto {
	private String nome;
	private String descricao;
	private String unidade;
	private double preco;
	
	public Produto() {
		super();
	}

	public Produto(String nome, String descricao, String unidade, double preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.unidade = unidade;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}

package negocio.exercicio01prof;

public class ItemDeVenda {
	private Produto produto;
	private int quantidade;
	
	public ItemDeVenda() {
		super();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void adicionaQuantidade() {
		quantidade++;
	}
	
	public double getSubTotal() {
		return produto.getPreco() * quantidade;
	}
}

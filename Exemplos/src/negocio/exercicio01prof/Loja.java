package negocio.exercicio01prof;

import java.util.ArrayList;
import java.util.List;

public class Loja {
	private List<Produto> catalogo;
	
	public Loja() {
		super();
		catalogo = new ArrayList<Produto>();
		catalogo.add(new Produto("Coca-cola","Refigerante Coca-cola","lata",3.5));
		catalogo.add(new Produto("Feij�o","Feij�o da Casa","pacote 1Kg",4.8));
		catalogo.add(new Produto("Arroz","Arroz da Casa","pacote 1Kg",5.7));
		catalogo.add(new Produto("F�sforo","F�sforo Olho","caixa",2.9));
		catalogo.add(new Produto("Cereal","Cereal Matinal","caixa 500g",8.5));
		catalogo.add(new Produto("Pasta de Dente","Colgate","bisnaga",5.2));
	}
	
	public List<Produto> getProdutos() {
		return catalogo;
	}
}

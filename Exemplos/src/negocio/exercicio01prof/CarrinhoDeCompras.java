package negocio.exercicio01prof;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
	private List<ItemDeVenda> items;
	
	public CarrinhoDeCompras() {
		items = new ArrayList<ItemDeVenda>();
	}

	public List<ItemDeVenda> getItems() {
		return items;
	}

	public void setItems(List<ItemDeVenda> items) {
		this.items = items;
	}
	
	public void addItem(ItemDeVenda item) {
		items.add(item);
	}
	
	public double getTotal() {
		double valor = 0;
		for (ItemDeVenda item : items) {
			valor += item.getSubTotal();
		}
		return valor;
	}
	
	public boolean isEmpty() {
		return items.isEmpty();
	}
}

package servlet.exercicio01prof;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.exercicio01prof.CarrinhoDeCompras;
import negocio.exercicio01prof.ItemDeVenda;
import negocio.exercicio01prof.Loja;
import negocio.exercicio01prof.Produto;

@WebServlet("/store")
public class FrenteLoja extends HttpServlet {
	private static final long serialVersionUID = 7437426681517837723L;

	private Loja loja;
	
	@Override
	public void init() {
		loja = new Loja();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Objetivo inicial - exibir a lista de produtos da "loja virtual"
		List<Produto> lista = loja.getProdutos();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>Loja Virtual</title></head>");
		out.println("<body><h1>Loja Virtual</h1>");
		
		// Exibe possíveis mensagens de erro
		String erro = req.getParameter("erro");
		if (erro != null && !erro.isEmpty()) {
			out.println("<h3><font color=\"red\">"+erro+"</font></h3>");
		}
		
		// Exibe o catálogo de produtos
		out.println("<h3>Catálogo de Produtos</h3>");
		out.println("<table border=\"1\"><tr><th>Produto</th><th>Descricao</th>"
					+ "<th>Unidade</th><th>Preço</th><th>Ação</th></tr>");
		for(Produto p : lista) {
			out.println("<tr><td>"+p.getNome()+"</td><td>"+p.getDescricao()+"</td>");
			out.println("<td>"+p.getUnidade()+"</td><td>"+p.getPreco()+"</td>");
			out.println("<td><form action=\"/Exemplos/store\" method=\"POST\">"
					+ "<input type=\"hidden\" name=\"produto\" value=\""+p.getNome()+"\">"
					+ "<input type=\"submit\" value=\"Adicionar ao Carrinho\"></form>");
			out.println("</td></tr>");
		}
		out.println("</table>");
		out.println("<a href=\"/Exemplos/carrinho\">Acesse o carrinho de compras</a>");
		out.println("</body></html>");
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Objetivo adicionar o produto desejado no carrinho de compras
		String produto = req.getParameter("produto");
		if (produto == null || produto.isEmpty()) {
			resp.sendRedirect("/Exemplos/store");
		}
		
		// Recuperar a sessão e armazenar o produto desejado
		HttpSession session = req.getSession();
		CarrinhoDeCompras carrinho = (CarrinhoDeCompras) session.getAttribute("carrinho");
		if (carrinho == null) {
			carrinho = new CarrinhoDeCompras();
		}
		
		// Procurar se o produto já está no carrinho - adicionando a quantidade do mesmo
		boolean novo = true;
		for (ItemDeVenda item: carrinho.getItems()) {
			if (item.getProduto().getNome().equals(produto)) {
				item.adicionaQuantidade();
				novo = false;
				break;
			}
		}
		
		// Adicionar no carrinho
		if (novo) {
			Produto p = loja.getProdutoByNome(produto);
			ItemDeVenda iv = new ItemDeVenda();
			iv.setProduto(p);
			iv.setQuantidade(1);
			carrinho.addItem(iv);
		}
		
		// Guardar carrinho na sessão
		session.setAttribute("carrinho", carrinho);
		
		//Imprimir a resposta HTML
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html><head><title>Loja Virtual</title></head>");
		out.println("<body>");
		if (novo) {
			out.println("<h2>Produto \""+produto+"\" adicionado com sucesso</h2>");
		} else {
			out.println("<h2>Quantida do produto desejado foi incrementada de uma unidade</h2>");
		}
		out.print("<a href=\"/Exemplos/store\">Voltar ao catálogo de produtos</a> ou ");
		out.println("<a href=\"/Exemplos/carrinho\">acesse o carrinho de compras</a>");
		out.println("</body></html>");
		out.close();
	}
	
}

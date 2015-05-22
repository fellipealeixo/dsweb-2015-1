package servlet.exercicio01prof;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.exercicio01prof.Loja;
import negocio.exercicio01prof.Produto;

@WebServlet("/store")
public class FrenteLoja extends HttpServlet {
	private static final long serialVersionUID = 7437426681517837723L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Objetivo inicial - exibir a lista de produtos da "loja virtual"
		Loja loja = new Loja();
		List<Produto> lista = loja.getProdutos();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>Loja Virtual</title></head>");
		out.println("<body><h1>Loja Virtual</h1>");
		out.println("<h3>Catálogo de Produtos</h3>");
		out.println("<table border=\"1\"><tr><th>Produto</th><th>Descricao</th><th>Unidade</th><th>Preço</th><th>Ação</th></tr>");
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
}

package servlet.exercicio01prof;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		@SuppressWarnings("unchecked")
		List<String> carrinho = (List<String>) session.getAttribute("carrinho");
		if (carrinho == null) {
			carrinho = new ArrayList<String>();
		}
		
		// Procurar se o produto já está no carrinho
		boolean novo = true;
		for (String p: carrinho) {
			if (p.equals(produto)) {
				novo = false;
				break;
			}
		}
		
		// Adicionar no carrinho
		if (novo) {
			carrinho.add(produto);
		}
		
		// Guardar carrinho na sessão
		session.setAttribute("carrinho", carrinho);
		session.setMaxInactiveInterval(10);
		
		//Imprimir a resposta HTML
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html><head><title>Loja Virtual</title></head>");
		out.println("<body>");
		if (novo) {
			out.println("<h2>Produto \""+produto+"\" adicionado com sucesso</h2>");
		} else {
			out.println("<h2>Produto já está no carrinho</h2>");
		}
		out.print("<a href=\"/Exemplos/store\">Voltar ao catálogo de produtos</a> ou ");
		out.println("<a href=\"/Exemplos/carrinho\">acesse o carrinho de compras</a>");
		out.println("</body></html>");
		out.close();
	}
	
}

package servlet.exercicio01prof;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.exercicio01prof.CarrinhoDeCompras;
import negocio.exercicio01prof.ItemDeVenda;

@WebServlet("/finalizaCompra")
public class FinalizaCompra extends HttpServlet {
	private static final long serialVersionUID = 1904274514457496635L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Solicitar as informações do comprador
		HttpSession session = req.getSession();
		CarrinhoDeCompras carrinho = (CarrinhoDeCompras) session.getAttribute("carrinho");
		if (carrinho == null || carrinho.isEmpty()) {
			req.getRequestDispatcher("/store?erro=Carrinho de compras está vazio!").forward(req, resp);
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html><head><title>Finalização de Compra</title></head>");
		out.println("<body><h2>Finalização de Compra</h2><h4>Forneça as informações do comprador...</h4>");
		
		// Exibe possíveis mensagens de erro
		String erro = req.getParameter("erro");
		if (erro != null && !erro.isEmpty()) {
			out.println("<h3><font color=\"red\">"+erro+"</font></h3>");
		}
		
		out.println("<form method=\"POST\" action=\"/Exemplos/finalizaCompra\">");
		out.println("Nome: <input type=\"text\" name=\"nome\"><br/>");
		out.println("CPF: <input type=\"text\" name=\"cpf\"><br/>");
		out.println("Número do Cartão: <input type=\"text\" name=\"numCartao\"><br/>");
		out.println("Código de Segurança: <input type=\"text\" name=\"codSeguranca\"><br/>");
		out.println("Endereço de Entrega: <input type=\"text\" name=\"endereco\"><br/>");
		out.println("<input type=\"submit\" value=\"Registrar a Compra\"><br/>");
		out.println("</form></body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		CarrinhoDeCompras carrinho = (CarrinhoDeCompras) session.getAttribute("carrinho");
		if (carrinho == null || carrinho.isEmpty()) {
			req.getRequestDispatcher("store?erro=Carrinho de compras está vazio!").forward(req, resp);
		}
		String nome = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String cartao = req.getParameter("numCartao");
		String codSeguranca = req.getParameter("codSeguranca");
		String endereco = req.getParameter("endereco");
		if (nome == null || cpf == null || cartao == null || codSeguranca == null || endereco == null ||
			nome.isEmpty() || cpf.isEmpty() || cartao.isEmpty() || codSeguranca.isEmpty() || endereco.isEmpty()) {
			resp.sendRedirect("/Exemplos/finalizaCompra?erro=Preencha todos os dados solicitados...");
			return;
		}
		
		// Escrever todos os dados
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html><head><title>Finalização de Compra</title></head>");
		out.println("<body><h2>Finalização de Compra</h2>");
		out.println("<h4>Cliente: "+nome+", com o CPF: "+cpf+"<br/>");
		out.println("Número do Cartão: "+cartao+"<br/>");
		out.println("Endereço de Entrega: "+endereco+"</h4>");
		out.println("<h4>Lista de itens comprados:<ul></h4>");
		for (ItemDeVenda item: carrinho.getItems()) {
			out.println("<li>"+item.getProduto().getNome()+" - quantidade = "+item.getQuantidade()+
					" - Preço unitário = R$ "+item.getProduto().getPreco()+" - Subtotal = R$ "+item.getSubTotal()+"</li>");
		}
		out.println("</ul><p><b>VALOR TOTAL DA VENDA = R$ "+carrinho.getTotal()+"</b></p>");
		out.print("<a href=\"/Exemplos/store\">Voltar ao catálogo de produtos para realizar novas compras</a>");
		out.println("</body></html>");
		session.invalidate();
		out.close();
	}

}

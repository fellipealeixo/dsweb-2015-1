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

@WebServlet("/finalizaCompra")
public class FinalizaCompra extends HttpServlet {
	private static final long serialVersionUID = 1904274514457496635L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Solicitar as informações do comprador
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<String> carrinho = (List<String>) session.getAttribute("carrinho");
		if (carrinho == null || carrinho.isEmpty()) {
			req.getRequestDispatcher("/Exemplos/store").forward(req, resp);
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<html><head><title>Finalização de Compra</title></head>");
		out.println("<body><h2>Finalização de Compra</h2><h4>Forneça as informações do comprador...</h4>");
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
		
	}

}

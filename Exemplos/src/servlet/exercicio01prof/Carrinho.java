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

@WebServlet(name = "carrinho", urlPatterns = { "/carrinho" })
public class Carrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Carrinho() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Exibir o carrinho de compras
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<String> carrinho = (List<String>) session.getAttribute("carrinho");
		if (carrinho == null) {
			carrinho = new ArrayList<String>();
			session.setAttribute("carrinho", carrinho);
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>Carrinho de Compras</title></head>");
		out.println("<body><h2>Carrinho de Compras - Produtos Selecionados</h2>");
		if (carrinho.isEmpty()) {
			out.println("<p>Nenhum produto foi adicionado ao carrinho.</p>");
		} else {
			out.println("<ol>");
			for (String p: carrinho) {
				out.println("<li>"+p+"</li>");
			}
			out.println("</ol>");
			out.println("<a href=\"/Exemplos/finalizaCompra\">Finaliza a compra</a><br>");
		}
		out.println("<a href=\"/Exemplos/store\">Continuar comprando...</a><br>");
		out.println("</body></html>");
		out.close();
	}

}

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
		CarrinhoDeCompras carrinho = (CarrinhoDeCompras) session.getAttribute("carrinho");
		if (carrinho == null) {
			carrinho = new CarrinhoDeCompras();
			session.setAttribute("carrinho", carrinho);
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>Carrinho de Compras</title></head>");
		out.println("<body><h2>Carrinho de Compras - Produtos Selecionados</h2>");
		if (carrinho.isEmpty()) {
			out.println("<p>Nenhum produto foi adicionado ao carrinho.</p>");
		} else {
			out.println("<ul>");
			for (ItemDeVenda item: carrinho.getItems()) {
				out.println("<li>"+item.getProduto().getNome()+" - quantidade = "+item.getQuantidade()+
						" - Preço unitário = R$ "+item.getProduto().getPreco()+" - Subtotal = R$ "+item.getSubTotal()+"</li>");
			}
			out.println("</ul>");
			out.println("<p><b>Valor total = R$ "+carrinho.getTotal()+"</b></p>");
			out.println("<a href=\"/Exemplos/finalizaCompra\">Finaliza a compra</a><br>");
		}
		out.println("<a href=\"/Exemplos/store\">Continuar comprando...</a><br>");
		out.println("</body></html>");
		out.close();
	}

}

package servlet.exemplo02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exemplo02")
public class MeuServlet extends HttpServlet {

	private static final long serialVersionUID = 2929531044003754703L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Enumeration<String> cabecalho = req.getHeaderNames();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body><h1>Cabe�alho HTTP</h1><ul>");
		while (cabecalho.hasMoreElements()) {
			String header = cabecalho.nextElement();
			out.println("<li>"+header+" = "+req.getHeader(header)+"</li>");
		}
		out.println("</ul>");
		out.println("<h2>"+req.getRemoteAddr()+"</h2>");
		out.println("</body></html>");
	}
	
	

}

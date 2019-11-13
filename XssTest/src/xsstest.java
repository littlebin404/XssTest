import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;

@WebServlet("/xsstest")
public class xsstest extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			String xss = request.getParameter("xss");
			// xss = htmlEncode(xss);
			// xss = ESAPI.encoder().encodeForURL(xss);
			// System.out.println(xss);
			xss = ESAPI.encoder().encodeForHTML(xss);
			// xss = ESAPI.encoder().encodeForHTMLAttribute(xss);
			// xss = ESAPI.encoder().encodeForJavaScript(xss);
			// xss = ESAPI.encoder().encodeForCSS(xss);
			// xss = ESAPI.encoder().encodeForURL(xss);
			request.getSession().setAttribute("xss", xss);
			request.setAttribute("xss", xss);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 只能解决<script>、css标签,无法解决href=javascript:alert(1)
	public static String htmlEncode(String source) {
		if (source == null) {
			return "";
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			switch (c) {
			case '<':
				buffer.append("&lt;");
				break;
			case '>':
				buffer.append("&gt;");
				break;
			case '&':
				buffer.append("&amp;");
				break;
			case '"':
				buffer.append("&quot;");
				break;
			case '`':
				buffer.append("&#x60");
			case ' ':
				buffer.append("&#x2F");
			default:
				buffer.append(c);
			}
		}
		html = buffer.toString();
		return html;
	}
}
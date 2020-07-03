package servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 認証
	 * ログインの学籍番号、パスワードが合っていたらTop.javaへ
	 * 一致しなければ戻る
	 */

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String message = (String)session.getAttribute("message");
		if(message != null) {
			session.removeAttribute("message");
			request.setAttribute("message", message);
		}
		String	path = "/WEB-INF/jsp/login.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}

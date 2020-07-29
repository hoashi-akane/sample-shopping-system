package servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dto.UserDto;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginId =request.getParameter("login_Id");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		UserDto userDto = userDao.login(loginId, password, false);

		String path = "";
		HttpSession session = request.getSession();
		if(userDto==null) {
			session.setAttribute("message", "ログインに失敗しました");
			response.sendRedirect("/SampleShopping/login");
		}else {
			session.setAttribute("userDto",userDto );
			response.sendRedirect("/SampleShopping/menu");
		}
	}

}

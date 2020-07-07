package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDto;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/dispuserinfo")
public class DispAdminUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispAdminUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("adminDto");
		request.setAttribute("userDto", userDto);
		request.getRequestDispatcher("WEB-INF/jsp/accountadmin.jsp").forward(request, response);
	}
}

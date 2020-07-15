package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dto.UserDto;

/**
 * Servlet implementation class InsertAbsenceResistServlet
 */
@WebServlet("/authadmin")
public class AuthAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	try {

		HttpSession session = request.getSession();

		String userid =request.getParameter("userid");
		String password =request.getParameter("paasword");

		AdminDao adminDao = new AdminDao();

		UserDto userDto = adminDao.login(userid,password);

		String path ="";
		if(userDto != null) {
			session.setAttribute("userDto", userDto);
			path = "/SampleShopping/menu";
			response.sendRedirect(path);
		} else {
			path = "/SampleShopping/loginadmin";
			response.sendRedirect(path);
	}
		}catch(Exception e){

		}
	}
}

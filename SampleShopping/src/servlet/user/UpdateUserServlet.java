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
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/updateuser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		UserDao userDao = new UserDao();

		if(userDao.updateUser(userDto)) {
			response.sendRedirect("/SampleShopping/menu");
		}else{
			response.sendRedirect("/SampleShopping/dispuserinfo");
		}
	}
}

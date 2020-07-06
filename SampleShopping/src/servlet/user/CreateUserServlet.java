package servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.UserDto;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/createuser")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/createuser.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto userDto = new UserDto();
		userDto.setLoginId(request.getParameter("loginid"));
		userDto.setPassword(request.getParameter("userpassword"));
		userDto.setUserName(request.getParameter("username"));
		userDto.setAddress(request.getParameter("address"));
		userDto.setAddressSub(request.getParameter("addresssub"));
		userDto.setTel(request.getParameter("tel"));
		userDto.setGender(Byte.parseByte(request.getParameter("gender")));

		UserDao userDao = new UserDao();
		if(userDao.insertUser(userDto)) {
			response.sendRedirect("/SampleShopping/menu");
		}else{
			request.getRequestDispatcher("WEB-INF/jsp/createuser.jsp").forward(request, response);
		}
	}
}

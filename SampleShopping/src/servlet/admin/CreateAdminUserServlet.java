package servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dto.UserDto;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/createadmin")
public class CreateAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAdminUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/createadmin.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto userDto = new UserDto();
		userDto.setLoginId(request.getParameter("loginid"));
		userDto.setPassword(request.getParameter("userpassword"));
		userDto.setUserName(request.getParameter("username"));
		userDto.setAddress(request.getParameter("address"));
		userDto.setAddressSub(request.getParameter("addresssub"));
		userDto.setZipCode(request.getParameter("zipcode"));
		userDto.setZipCodeSub(request.getParameter("zipcodesub"));
		userDto.setTel(request.getParameter("tel"));
		userDto.setGender(Byte.parseByte(request.getParameter("gender")));
		userDto.setMailAddress(request.getParameter("mailaddress"));

		AdminDao adminDao = new AdminDao();
		if(adminDao.insertAdminUser(userDto)) {
			response.sendRedirect("/SampleShopping/menuadmin");
		}else{
			request.getRequestDispatcher("WEB-INF/jsp/createadmin.jsp").forward(request, response);
		}
	}
}

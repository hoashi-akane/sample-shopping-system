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
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/admin/drawadminuser")
public class DrawAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawAdminUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	UserDto userDto = (UserDto)session.getAttribute("userDto");
		int id = userDto.getId();

		AdminDao adminDao = new AdminDao();
		if(adminDao.deleteUser(id)) {
			session.invalidate();
			response.sendRedirect("/SampleShopping/loginadmin");
		}else{
			response.sendRedirect("/SampleShopping/admin/dispadminuserinfo");
		}
	}
}

package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.UserDao;
import dto.UserDto;

/**
 * Servlet implementation class CheckAdminPasswordServlet
 */
@WebServlet("/checkadminpassword")
public class CheckAdminPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAdminPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inputPassword = request.getParameter("input_password");
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		AdminDao adminDao = new AdminDao();
		
		if(adminDao.exists(userDto.getId(), inputPassword)) {
			session.setAttribute("checkPasswordFlag", true);
			response.sendRedirect("/SampleShopping/dispadminuserinfo");
		}else {
			session.setAttribute("message", "パスワードの認証に失敗しました");
			response.sendRedirect("/SampleShopping/menuadmin");
		}
	}

}

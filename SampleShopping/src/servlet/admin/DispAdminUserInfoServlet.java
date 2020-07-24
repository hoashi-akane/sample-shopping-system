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
@WebServlet("/dispadminuserinfo")
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
//		checkPasswordFlagが発行されていない限り入れない
    	if(!(boolean)session.getAttribute("checkPasswordFlag")) {
    		response.sendRedirect("/SampleShopping/menuadmin");
    		return;
    	}
    	
    	UserDto userDto = (UserDto)session.getAttribute("userDto");
		AdminDao adminDao = new AdminDao();
		userDto = adminDao.findAdminUser(userDto.getId());
		
		if(userDto==null) {
    		request.getRequestDispatcher("/WEB-INF/jsp/topadmin.jsp").forward(request,response);
    	}else{
    		session.setAttribute("userDto", userDto);
    		request.getRequestDispatcher("WEB-INF/jsp/accountadmin.jsp").forward(request, response);
    	}
	}
}

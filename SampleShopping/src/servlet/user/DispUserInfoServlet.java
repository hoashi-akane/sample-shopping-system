package servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dto.UserDto;

/**
 * Servlet implementation class EntryServlet
 */
public class DispUserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();

    	int userId = ((UserDto)session.getAttribute("userDto")).getId();
    	UserDao userDao = new UserDao();
    	UserDto userDto = userDao.findUser(userId);

    	if(userDto==null) {
    		getServletContext().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").
    		forward(request,response);

    	}else {
    		session.setAttribute("userDto", userDto);
    		getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp").
    		forward(request,response);
    	}

    }

}
package servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import dto.CartDto;
import dto.UserDto;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/updatecart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CartDao cartDao = new CartDao();
		HttpSession session = request.getSession();
		int userId = ((UserDto)session.getAttribute("userDto")).getId();
		String path = "dispcart";
		try {
//			値の受取りとセット
			CartDto cartDto = new CartDto();
			cartDto.setId(Integer.parseInt(request.getParameter("cart_id")));
			cartDto.setVolume(Integer.parseInt(request.getParameter("volume")));
			cartDto.setUserId(userId);
			
			boolean isSuccess = cartDao.updateCart(cartDto);
			if(isSuccess) {
				session.setAttribute("message", "更新しました。");
			}else {
				session.setAttribute("message", "更新に失敗しました。<br>やり直してください");
			}
//		NumberFormatException等
		}catch(Exception e) {
			session.setAttribute("message", "エラーが発生しました。<br>やり直してください。");
		}finally {
			response.sendRedirect(path);
		}
	}
	
}

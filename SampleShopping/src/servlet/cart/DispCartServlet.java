package servlet.cart;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class DispCartServlet
 */
@WebServlet("/dispcart")
public class DispCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int userId = ((UserDto)session.getAttribute("userDto")).getId();
		CartDao cartDao = new CartDao();
		//		成功、失敗、エラーメッセージ確認
		String message = (String)session.getAttribute("message");
		if(message != null) {
			session.removeAttribute("message");
			request.setAttribute("message", message);
		}
		List<CartDto> cartDtoList = cartDao.getCart(userId);
		if(cartDtoList != null) {
			request.setAttribute("cartDtoList", cartDtoList);
		}else {
			request.setAttribute("message", "カートに商品は追加されていません。");
		}
		request.getRequestDispatcher("WEB-INF/jsp/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

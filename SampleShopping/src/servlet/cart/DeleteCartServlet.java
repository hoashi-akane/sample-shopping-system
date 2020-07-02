package servlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import dto.UserDto;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
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
			int cartId = Integer.parseInt(request.getParameter("cart_id"));
			boolean isSuccess = cartDao.deleteCart(cartId, userId);
			if(isSuccess) {
				session.setAttribute("message", "削除しました。");
			}else {
				session.setAttribute("message", "削除に失敗しました。<br>やり直してください");
			}
		}catch(Exception e) {
			session.setAttribute("message", "エラーが発生しました。<br>やり直してください。");
		}finally {
			response.sendRedirect(path);
		}
	}
}

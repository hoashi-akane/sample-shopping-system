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
 * Servlet implementation class InsertCartServlet
 */
@WebServlet("/insertcart")
public class InsertCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCartServlet() {
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
		HttpSession session = request.getSession();
		int userId = ((UserDto)session.getAttribute("userDto")).getId();
		CartDto cartDto = new CartDto();
		CartDao cartDao = new CartDao();
		String path = "dispcart";
		try{
			int goodsId = Integer.parseInt(request.getParameter("goods_id"));
			int volume = Integer.parseInt(request.getParameter("volume"));
			cartDto.setGoodsId(goodsId);
			cartDto.setVolume(volume);
			cartDto.setUserId(userId);
			boolean isSuccess = cartDao.insertCart(cartDto);
			if(isSuccess) {
				session.setAttribute("message", "追加しました。");
			}else {
				session.setAttribute("message", "追加に失敗しました。<br>やり直してください");
			}
//		numberFormat等
		}catch(Exception e) {
			session.setAttribute("message", "エラーが発生しました。<br>やり直してください。");
		}finally {
			response.sendRedirect(path);
		}
	}
}

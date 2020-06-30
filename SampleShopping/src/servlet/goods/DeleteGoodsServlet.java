package servlet.goods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;

/**
 * Servlet implementation class InsertAbsenceResistServlet
 */
@WebServlet("/deletegoods")
public class DeleteGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String num =request.getParameter("id");
		int id  = Integer.parseInt(num);

		GoodsDao goodsDao = new GoodsDao();
		boolean isSuccess = goodsDao.deleteGoods(id);


		String path ="";
		if(isSuccess) {
			path = "menu";
			response.sendRedirect(path);
		} else {
			path = "dispgoodslist";
			response.sendRedirect(path);
		}
	}
}

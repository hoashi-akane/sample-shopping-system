package servlet.goods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;


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

	try {

		String num =request.getParameter("id");
		int id  = Integer.parseInt(num);


		GoodsDao goodsDao = new GoodsDao();
		boolean isSuccess = goodsDao.deleteGoods(id);


		String path ="";
		if(isSuccess) {
			path = "menuadmin";
			response.sendRedirect(path);
		} else {
			path = "dispgoodslistadmin";
			response.sendRedirect(path);
		}

	} catch(Exception e) {

		}
	}
}

package servlet.goods;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import dto.GoodsDto;

/**
 * Servlet implementation class DispGoodsDetailServlet
 */
@WebServlet("/dispgoodsdetail")
public class DispGoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispGoodsDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = (Integer)request.getAttribute("id");
		GoodsDao goodsDao  = new GoodsDao();
		GoodsDto goodsDto = goodsDao.getGoods(id);
		if(goodsDto != null) {
			request.setAttribute("goodsDto", goodsDto);
		}else {
			request.setAttribute("message", "IDから商品が見つかりませんでした。");
		}
		request.getRequestDispatcher("WEB-INF/jsp/dispgoodsdetail.jsp").forward(request, response);
	}

}

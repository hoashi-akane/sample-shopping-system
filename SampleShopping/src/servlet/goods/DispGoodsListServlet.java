package servlet.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import dto.GoodsDto;

/**
 * Servlet implementation class DispGoodsListServlet
 */
@WebServlet("/dispgoodslist")
public class DispGoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispGoodsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//    商品一覧を取得してくるサーブレット（引数無いからdoGetで作った。)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//　商品一覧表示用サーブレット
		List<GoodsDto> goodsDtoList = null;
	   	GoodsDao goodsDao = new GoodsDao();
		goodsDtoList = goodsDao.getGoodsList();
		if(goodsDtoList != null) {
			request.setAttribute("goodsDtoList", goodsDtoList);
		}else {
			request.setAttribute("message", "商品がありませんでした。");
		}
		request.getRequestDispatcher("WEB-INF/jsp/goodslist.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

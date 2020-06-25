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
 * Servlet implementation class SearchGoodsServlet
 */
@WebServlet("/searchgoods")
public class SearchGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	検索欄から飛んできた文字から商品を検索し一覧表示させるサーブレット
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<GoodsDto> goodsDtoList = null;
    	GoodsDao goodsDao  = new GoodsDao();
    	String input = (String)request.getAttribute("input");
    	goodsDtoList = goodsDao.getGoodsList(input);
    	if(goodsDtoList != null) {
    		request.setAttribute("goodsDtoList", goodsDtoList);
    	}else {
    		request.setAttribute("message", "検索しましたが見つかりませんでした。");
    	}
    	request.getRequestDispatcher("WEB-INF/jsp/goodslist.jsp").forward(request, response);
    }

}

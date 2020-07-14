package servlet.goods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsDao;
import dto.GoodsDto;


@WebServlet("/updategoods")
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	try	{

		HttpSession session = request.getSession();
		GoodsDto goodsDto = new GoodsDto();
	    goodsDto = (GoodsDto)session.getAttribute("goodsDto");

	    String num1 = request.getParameter("goods_id");
		int goods_id  = Integer.parseInt(num1);
		String goods_name = request.getParameter("goods_name");
		String num2 = request.getParameter("price");
		int price  = Integer.parseInt(num2);
		String num3 = request.getParameter("stock");
		int stock  = Integer.parseInt(num3);
		String description = request.getParameter("description");
		String num4 = request.getParameter("category_id");
		int category_id  = Integer.parseInt(num4);
		String num5 = request.getParameter("brand_id");
		int brand_id  = Integer.parseInt(num5);


		goodsDto.setId(goods_id);
		goodsDto.setGoodsName(goods_name);
		goodsDto.setPrice(price);
		goodsDto.setStock(stock);
		goodsDto.setDescription(description);
		goodsDto.setCategoryId(category_id);
		goodsDto.setBrandId(brand_id);

		request.getRequestDispatcher("/WEB-INF/jsp/correctiongoods.jsp").
		forward(request,response);

		}catch(Exception e) {
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	try {

		HttpSession session = request.getSession();
		GoodsDto goodsDto = new GoodsDto();
	    goodsDto = (GoodsDto)session.getAttribute("goodsDto");

	    String num1 = request.getParameter("goods_id");
		int goods_id  = Integer.parseInt(num1);
		String goods_name = request.getParameter("goods_name");
		String num2 = request.getParameter("price");
		int price  = Integer.parseInt(num2);
		String num3 = request.getParameter("stock");
		int stock  = Integer.parseInt(num3);
		String description = request.getParameter("description");
		String num4 = request.getParameter("category_id");
		int category_id  = Integer.parseInt(num4);
		String num5 = request.getParameter("brand_id");
		int brand_id  = Integer.parseInt(num5);


		goodsDto.setId(goods_id);
		goodsDto.setGoodsName(goods_name);
		goodsDto.setPrice(price);
		goodsDto.setStock(stock);
		goodsDto.setDescription(description);
		goodsDto.setCategoryId(category_id);
		goodsDto.setBrandId(brand_id);

		GoodsDao goodsDao = new GoodsDao();
		boolean successGoods = goodsDao.updateGoods(goodsDto);


		String path ="";
		if(successGoods) {
			session.setAttribute("goodsDto", goodsDto);
			path = "menu";
			response.sendRedirect(path);
			session.removeAttribute("goodsDto");
		} else {
			path = "dispgoodslist";
			response.sendRedirect(path);
	}
		}catch(Exception e) {

		}
	}
}
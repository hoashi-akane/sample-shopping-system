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
import service.BrandService;
import service.CategoryService;


@WebServlet("/updategoods")
public class UpdateGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BrandService brandService = new BrandService();
	CategoryService categoryService = new CategoryService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int id =(Integer)session.getAttribute("goodsId");
		session.removeAttribute("goodsId");

		String goodsName = request.getParameter("goods_name");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		String description = request.getParameter("description");
		String categoryId = request.getParameter("category_id");
		String brandId = request.getParameter("brand_id");

		GoodsDto goodsDto = new GoodsDto();
		try {
			goodsDto.setId(id);
			goodsDto.setGoodsName(goodsName);
			goodsDto.setPrice(Integer.parseInt(price));
			goodsDto.setStock(Integer.parseInt(stock));
			goodsDto.setDescription(description);
			goodsDto.setCategoryId(Integer.parseInt(categoryId));
			goodsDto.setBrandId(Integer.parseInt(brandId));
		}catch(NumberFormatException e) {
			request.setAttribute("message", "数値を入力してください");
			request.getRequestDispatcher("WEB-INF/jsp/correctiongoods.jsp").forward(request, response);
		}

		GoodsDao goodsDao = new GoodsDao();
		boolean successGoods = goodsDao.updateGoods(goodsDto);
		
		String path ="";
		if(successGoods) {
			session.setAttribute("goodsDto", goodsDto);
			session.setAttribute("message", "編集に成功しました。");
			path = "dispgoodslistadmin";
			response.sendRedirect(path);
			session.removeAttribute("goodsDto");
		} else {
			session.setAttribute("message", "編集に失敗しました。");
			path = "dispgoodslistadmin";
			response.sendRedirect(path);
		}
	}
}
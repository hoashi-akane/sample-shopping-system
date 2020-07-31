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


@WebServlet("/updateloadgoods")
public class UpdateLoadGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BrandService brandService = new BrandService();
	CategoryService categoryService = new CategoryService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLoadGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("goods_id"));
		}catch(NumberFormatException e){
			e.printStackTrace();
		}

		if(id == 0) {
			request.setAttribute("message", "数値を入力してください。");
		}else {

			GoodsDao goodsDao = new GoodsDao();
			GoodsDto goodsDto = goodsDao.getGoods(id);
			request.setAttribute("brandDtoList",brandService.brandListService());
			request.setAttribute("categoryDtoList", categoryService.categoryListService());
			request.setAttribute("goodsDto", goodsDto);
			session.setAttribute("goodsId", goodsDto.getId());
		}
		request.getRequestDispatcher("/WEB-INF/jsp/correctiongoods.jsp").
		forward(request,response);
	}
}
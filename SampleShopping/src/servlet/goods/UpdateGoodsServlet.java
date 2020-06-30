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

/**
 * Servlet implementation class InsertAbsenceResistServlet
 */
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
		request.getRequestDispatcher("/WEB-INF/jsp/updategoods.jsp").
		forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		GoodsDto goodsDto = new GoodsDto();

	    goodsDto = (GoodsDto)session.getAttribute("goodsDto");

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
	}


}
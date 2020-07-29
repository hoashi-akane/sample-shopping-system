package servlet.goods;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsDao;
import dto.GoodsDto;
import other.SystemPath;
import service.GoodsService;

/**
 * Servlet implementation class DispGoodsDetailServlet
 */
@WebServlet("/dispgoodsdetail")
public class DispGoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService goodsService = new GoodsService();
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		GoodsDao goodsDao  = new GoodsDao();
		GoodsDto goodsDto = goodsDao.getGoods(id);
		if(goodsDto == null) {
			request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
			return;
		}
		
		File[] fileList = goodsService.getImageFileList(SystemPath.getImageFullPath(goodsDto.getImageDir()));
		request.setAttribute("fileList", fileList);
		
//		商品がない。
		if(goodsDto != null) {
			request.setAttribute("goodsDto", goodsDto);
		} else {
			request.setAttribute("message", "IDから商品が見つかりませんでした。");
		}
		request.getRequestDispatcher("WEB-INF/jsp/dispgoodsdetail.jsp").forward(request, response);
	}
}

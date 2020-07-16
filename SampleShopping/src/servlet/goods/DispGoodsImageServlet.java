package servlet.goods;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import other.SystemPath;
import service.GoodsService;

/**
 * Servlet implementation class DispGoodsImageServlet
 */
@WebServlet("/dispgoodsimage")
public class DispGoodsImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService goodsService = new GoodsService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispGoodsImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		String imagePath = (String)request.getParameter("imagePath");
		goodsService.outputImages(SystemPath.getImageFullPath(imagePath), response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
		String fullPath = SystemPath.getImageFullPath(imagePath);
//		商品一覧の場合は受け取ったimagePathの最後にfirstが入っている→パスを商品の画像に再設定
		if (imagePath.substring(imagePath.lastIndexOf("/")+1).equals("first") &&
				goodsService.getImageFileList(fullPath.substring(0, fullPath.lastIndexOf("/"))).length != 0){
			fullPath = fullPath.substring(0, fullPath.lastIndexOf("/")+1);
			fullPath = goodsService.findImageFile(fullPath).getPath();
		}
		goodsService.outputImages(fullPath, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

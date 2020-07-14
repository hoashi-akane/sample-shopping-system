package servlet.goods;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.GoodsDao;
import dto.GoodsDto;


@WebServlet("/insertgoods")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class InsertGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();
    	String message = (String)session.getAttribute("message");
    	if(message != null) {
    		session.removeAttribute("message");
    		request.setAttribute("message", message);
    	}
    	Path path2 = Paths.get("/WebContent");
		request.getRequestDispatcher("/WEB-INF/jsp/insertgoods.jsp").
		forward(request,response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path ="";
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		GoodsDto goodsDto = new GoodsDto();
		String goodsName = request.getParameter("goods_name");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		String description = request.getParameter("description");
		String categoryId = request.getParameter("category_id");
		String brandId = request.getParameter("brand_id");
	    
		try {
			goodsDto.setGoodsName(goodsName);
			goodsDto.setPrice(Integer.parseInt(price));
			goodsDto.setStock(Integer.parseInt(stock));
			goodsDto.setDescription(description);
			goodsDto.setSalesQuantity(0);
			goodsDto.setSale(false);
			goodsDto.setCategoryId(Integer.parseInt(categoryId));
			goodsDto.setBrandId(Integer.parseInt(brandId));

		}catch(NumberFormatException e) {
			session.setAttribute("message","数値を入力してください。");
			path ="/SampleShopping/insertgoods";
			response.sendRedirect(path);
			return;
		}
		
		GoodsDao goodsDao = new GoodsDao();
		boolean successGoods = goodsDao.insertGoods(goodsDto);
		
		if(!successGoods) {
			path = "/SampleShopping/dispgoodslist";
			response.sendRedirect(path);
			return ;
		}
		
		int goodsId = goodsDao.findGoods(goodsDto);
		String imagePath = System.getProperty("user.home")+"/output_imgfile/"+goodsId;
		File dir = new File(imagePath);
		dir.mkdirs();
		
		if(goodsId == 0 || !goodsDao.insertImagePath(goodsId, imagePath)) {
//			商品が見つからない場合
			path = "/SampleSHopping/dispgoodsilst";
			response.sendRedirect(path);
			return ;
		}
		
//		画像受取り
	    List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
	    int i = 0;
	    for(Part part : fileParts) {
			String fileType = part.getContentType();
//			画像であるか確かめる。
			if(isImage(fileType)) {
				session.setAttribute("message", "画像はpngまたはjpeg(jpg)形式で送信してください。");
				path ="/SampleShopping/insertgoods";
				response.sendRedirect(path);
				return;
			}
			String pass = imagePath +"/"+i+"."+fileType.substring(fileType.lastIndexOf("/")+1);
			part.write(pass);
			i++;
		}
		
		path = "/SampleShopping/menu";
		response.sendRedirect(path);
	}
	
	private boolean isImage(String fileType) {
		return !(fileType.equals("image/png")) && !(fileType.equals("image/jpg")) && !(fileType.equals("image/jpeg"));
	}
}
package servlet.buy_history;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyHistoryDao;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/hiddenbuyhistory")
public class HiddenBuyHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HiddenBuyHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	BuyHistoryDao buyHistoryDao = new BuyHistoryDao();

    	if(BuyHistoryDao.hiddenBuyHistory(id)) {
			response.sendRedirect("/SampleShopping/menu");
		}else{
			response.sendRedirect("/SampleShopping/dispbuyhistory");
		}
    }
}

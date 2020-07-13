package servlet.buy_history;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BuyHistoryDao;
import dto.BuyHistoryDto;
import dto.UserDto;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/dispbuyhistory")
public class DispBuyHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispBuyHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	List<BuyHistoryDto> listBuyHistory = new ArrayList<BuyHistoryDto>();
    	BuyHistoryDao buyHistoryDao = new BuyHistoryDao();
    	HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		int id = userDto.getId();

		listBuyHistory = buyHistoryDao.getBuyHistoryList(id);
		request.setAttribute("listBuyHistory", listBuyHistory);
		request.getRequestDispatcher("WEB-INF/jsp/dispbuyhistory.jsp").forward(request, response);
	}
}

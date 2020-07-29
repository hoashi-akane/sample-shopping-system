package servlet.user;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.UserDao;
import dto.UserDto;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/updateuser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao = new UserDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	HttpSession session = request.getSession();
//    	id取得
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		int id = userDto.getId();
		session.setAttribute("userDto", userDao.findUser(id));
    	request.getRequestDispatcher("WEB-INF/jsp/correctionuser.jsp").forward(request,response);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
//    	id取得
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		int id = userDto.getId();
		
		Optional<String> loginId = Optional.ofNullable(request.getParameter("login_id"));
		Optional<String> userPassword = Optional.ofNullable(request.getParameter("user_password"));
		Optional<String> userName = Optional.ofNullable(request.getParameter("username"));
		Optional<String> zipCode = Optional.ofNullable(request.getParameter("zip_code"));
		Optional<String> zipCodeSub = Optional.ofNullable(request.getParameter("zip_code_sub"));
		Optional<String> address = Optional.ofNullable(request.getParameter("address"));
		Optional<String> addressSub = Optional.ofNullable(request.getParameter("address_sub"));
		Optional<String> tel = Optional.ofNullable(request.getParameter("tel"));
		Optional<String> gender = Optional.ofNullable(request.getParameter("gender"));
		Optional<String> mailaddress = Optional.ofNullable(request.getParameter("mailaddress"));
//		どれか一つでもnullだった場合
		if(!loginId.isPresent() || !userPassword.isPresent() || !userName.isPresent() ||
				!mailaddress.isPresent() || !address.isPresent() || !zipCode.isPresent()) {
			request.setAttribute("message", "必須項目が入力されていません。");
			request.getRequestDispatcher("WEB-INF/jsp/correctionuser.jsp").forward(request, response);
			return;
		}
		
//		適切にチェックされていなければget()でExceptionを発生させる
		userDto.setId(id);
		userDto.setLoginId(loginId.get());
		userDto.setPassword(userPassword.get());
		userDto.setUserName(userPassword.get());
		userDto.setZipCode(zipCode.get());
		userDto.setZipCodeSub(zipCodeSub.orElse("登録なし"));
		userDto.setAddress(address.get());
		userDto.setAddressSub(addressSub.orElse("登録なし"));
		userDto.setTel(tel.orElse("登録なし"));
		userDto.setGender(Byte.parseByte(gender.orElse("0")));
		userDto.setMailAddress(mailaddress.get());

		if(userDao.updateUser(userDto)) {
			response.sendRedirect("/SampleShopping/menu");
		}else{
			request.setAttribute("message", "そのログインIDは既に使用されています。");
			request.getRequestDispatcher("WEB-INF/jsp/correctionuser.jsp").forward(request, response);
		}
	}
}

package servlet.user;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.UserDto;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/createuser")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/createuser.jsp").forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		UserDto userDto = new UserDto();
		
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
			request.getRequestDispatcher("WEB-INF/jsp/createuser.jsp").forward(request, response);
			return;
		}
		
//		適切にチェックされていなければget()でExceptionを発生させる
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

		UserDao userDao = new UserDao();
		if(userDao.insertUser(userDto)) {
			response.sendRedirect("/SampleShopping/login");
		}else{
			request.setAttribute("message", "そのidは既に登録されています。");
			request.getRequestDispatcher("WEB-INF/jsp/createuser.jsp").forward(request, response);
		}
	}
}

package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDto;


/**
 * Servlet Filter implementation class MyFillter
 */
@WebFilter("/*")
public class SessionCheckFilter implements Filter {
	private String encoding = "UTF-8";
    /**
     * Default constructor.
     */
    public SessionCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=" + encoding);


		String name = ((HttpServletRequest)request).getServletPath();
		StringBuffer sb = ((HttpServletRequest)request).getRequestURL();
// ログイン前
		if( sb.toString().contains(".PNG") || sb.toString().contains(".png") || sb.toString().contains(".jpeg") || sb.toString().contains(".jpg") || name.equals("/createuser") || name.equals("/login") || name.equals("/auth") || name.equals("/loginadmin") || name.equals("/admin/authadmin")) {
			chain.doFilter(request, response);
			return;
		}
// 本来ログイン後の画面にいる
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		//セッション自体ない場合と、教員ログインか学生ログインかによって処理分割
		if(session == null) {
			((HttpServletResponse)response).sendRedirect("/SampleShopping/login");
			return;
		}
		if(session.getAttribute("userDto") == null) {
			((HttpServletResponse)response).sendRedirect("/SampleShopping/login");
			return;
		}
		
		if(!checkAccessAuthority((UserDto)session.getAttribute("userDto"), name)) {
			((HttpServletResponse)response).sendRedirect("/SampleShopping/login");
			return;
		}
		chain.doFilter(request, response);
	}
	
	
	private boolean checkAccessAuthority(UserDto userDto, String name) {
//		アクセス許可 true 拒否 false
		boolean authority = true;
		boolean userAuthority = userDto.isAdmin();
		if(!userAuthority) {
			switch(name) {
				case "/dispbuyhistory":
				case "/deletecart":
				case "/dispcart":
				case "/insertcart":
				case "/updatecart":
				case "/dispgoodsdetail":
				case "/dispgoodsimage":
				case "/dispgoodslist":
				case "/searchgoods":
				case "/menu":
				case "/payment":
				case "/paymentcharge":
				case "/checkpassword":
				case "/createuser":
				case "/dispuserinfo":
				case "/drawuser":
				case "/updateuser":
				case "/logout":
					break;
				default:
					authority = false;
			}
		}
		return authority;
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
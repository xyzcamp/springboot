package sample04.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//http://blog.csdn.net/xiaodanjava/article/details/32125687
//http://einverne.github.io/post/2017/08/spring-interceptor-vs-filter.html
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private String goIfDeny = "/";

	public void setGoIfDeny(String goIfDeny) {
		this.goIfDeny = goIfDeny;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle," + this.goIfDeny);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle," + this.goIfDeny);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion," + this.goIfDeny);
	}
}

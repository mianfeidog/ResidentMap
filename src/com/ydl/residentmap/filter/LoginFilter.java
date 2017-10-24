package com.ydl.residentmap.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.ydl.residentmap.model.User;

/**
 * 登录过滤器
 * @author xq
 * 2017-5-20
 */
public class LoginFilter implements Filter{

	/**   
	* 需要排除的页面   
	*/    
	
	private String excludedPages;       
	private String[] excludedPageArray;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsq = (HttpServletRequest)req;
		//System.out.println("过滤器");
		boolean isExclueURL = false;
		String servletPath = hsq.getServletPath();
		if(servletPath.indexOf(".js")>=0 || servletPath.indexOf(".css")>=0)
		{
			isExclueURL = true;
		}

		if(excludedPageArray != null){
			//判断本次请求是否在过滤URL之外
			for(String url : excludedPageArray){
				if(servletPath.equals(url)){
					isExclueURL = true;
					break;
				}     
			}
		}

		if(isExclueURL){
			chain.doFilter(req, resp);
		}else{
			//判断用户是否登录
			User user = (User)hsq.getSession().getAttribute("loginUser");
			if(user==null) {
				String contextPath = hsq.getContextPath();
				PrintWriter pw = ((HttpServletResponse)resp).getWriter();
				//pw.write("needlogin");
				((HttpServletResponse)resp).sendRedirect(contextPath+"/login.html");
				//req.getRequestDispatcher("login.html").forward(req,resp);
				return;
				//chain.doFilter(req, resp);
			}
			else
			{
				chain.doFilter(req, resp);
			}
		}
		
		
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		//获取例外的URL
		excludedPages = fConfig.getInitParameter("excludedPages");     
		if (!StringUtils.isEmpty(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
		}     
		return; 

		
	}

}

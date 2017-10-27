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
		String servletPath = hsq.getServletPath();
		String contextPath = hsq.getContextPath();
		//判断用户是否登录
		User user = (User)hsq.getSession().getAttribute("loginUser");
		//用户已登录，
		if(user!=null )
		{
			//直接在浏览器输入http://localhost:8080/ResidentMap/时，跳转到首页
			if("/login.html".equals(servletPath))
			{
				((HttpServletResponse)resp).sendRedirect(contextPath+"/party_commitee_info.html");
				return;
			}
			else
			{
				chain.doFilter(req, resp);
			}
		}
		//用户未登录
		else
		{
			//System.out.println("过滤器");
			boolean isExclueURL = false;

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
			}
			else
			{
				PrintWriter pw = ((HttpServletResponse)resp).getWriter();
				((HttpServletResponse)resp).sendRedirect(contextPath+"/login.html");
				//req.getRequestDispatcher("login.html").forward(req,resp);
				return;
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

package be.vdab.filters;

import be.vdab.services.LandService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MenuFilter", value = "*")
public class MenuFilter implements Filter {

	private final transient LandService landService = new LandService();

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		HttpSession session = httpReq.getSession();


		if (session.getAttribute("sessionlanden") == null){
			session.setAttribute("sessionlanden", landService.findAll());
		}

		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);

	}

	public void init(FilterConfig config) throws ServletException {

	}

}

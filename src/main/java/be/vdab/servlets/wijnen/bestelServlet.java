package be.vdab.servlets.wijnen;

import be.vdab.entities.WijnenEntity;
import be.vdab.services.LandService;
import be.vdab.services.WijnService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "bestelServlet", urlPatterns = "/wijnen/bestel.htm")
public class bestelServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/wijnen/bestel.jsp";
	private static final String REDIRECT_URL = "%s/index.htm";
	private final transient WijnService wijnService = new WijnService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();


		try{
			int aantal = Integer.parseInt(request.getParameter("aantal"));
			long wijnid = Long.parseLong(request.getParameter("wijnid"));

			if (aantal < 1){
				fouten.put("aantal", "Enkel 1 of meer flessen aub.");
			} else {
				WijnenEntity wijn = wijnService.findById(wijnid);
				HttpSession session = request.getSession();
				//Session basket exist
				if (session.getAttribute("basket") != null) {
					@SuppressWarnings("unchecked")
					Map<WijnenEntity, Integer> basketMap = (HashMap) session.getAttribute("basket");
					basketMap.put(wijn, aantal);
					session.setAttribute("basket", basketMap);
				} else {
					Map<WijnenEntity, Integer> basketMap = new HashMap<>();
					basketMap.put(wijn, aantal);
					session.setAttribute("basket", basketMap);
				}
			}

		} catch (NumberFormatException ex){
			fouten.put("aantal", "Enkel 1 of meer flessen aub.(catch)");
		}

		if (fouten.isEmpty()){
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String wijnid = request.getParameter("id");

		if (wijnid != null && !wijnid.isEmpty()){
			wijnService.read(Long.parseLong(wijnid)).ifPresent(Wijn -> request.setAttribute("wijn", Wijn));
		}

		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}

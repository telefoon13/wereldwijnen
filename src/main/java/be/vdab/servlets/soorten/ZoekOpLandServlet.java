package be.vdab.servlets.soorten;

import be.vdab.entities.LandenEntity;
import be.vdab.entities.SoortenEntity;
import be.vdab.services.LandService;
import be.vdab.services.SoortService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ZoekOpLandServlet", urlPatterns = "/soorten/zoekopland.htm")
public class ZoekOpLandServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/soorten/zoekopland.jsp";
	private final transient LandService landService = new LandService();
	private final transient SoortService soortService = new SoortService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String landid = request.getParameter("id");
		String soortid = request.getParameter("soort");

		if (landid != null && !landid.isEmpty()){
			landService.read(Long.parseLong(landid)).ifPresent(LandenEntity -> request.setAttribute("land", LandenEntity));
		}

		if (soortid != null && !soortid.isEmpty()){
			soortService.read(Long.parseLong(soortid)).ifPresent(SoortenEntity -> request.setAttribute("soort", SoortenEntity));
		}

		request.getRequestDispatcher(VIEW).forward(request, response);

	}
}

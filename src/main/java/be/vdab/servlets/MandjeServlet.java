package be.vdab.servlets;


		import be.vdab.entities.BestelbonnenEntity;
		import be.vdab.entities.WijnenEntity;
		import be.vdab.services.BestelBonService;
		import be.vdab.services.WijnService;
		import be.vdab.valueobjects.BestelbonLijnen;

		import javax.servlet.ServletException;
		import javax.servlet.annotation.WebServlet;
		import javax.servlet.http.HttpServlet;
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;
		import javax.servlet.http.HttpSession;
		import java.io.IOException;
		import java.sql.Date;
		import java.sql.Timestamp;
		import java.util.HashMap;
		import java.util.Map;

@WebServlet(name = "MandjeServlet", urlPatterns = "/mandje.htm")
public class MandjeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private final transient BestelBonService bestelBonService = new BestelBonService();
	private final transient WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		String straat = request.getParameter("straat");
		String huisnummer = request.getParameter("huisnummer");
		String postcode = request.getParameter("postcode");
		String gemeente = request.getParameter("gemeente");
		int afhalenOpsturen = 3;

		if (naam == null || naam.isEmpty()){
			fouten.put("naam","Gelieven dit veld in te vullen");
		}
		if (straat == null || straat.isEmpty()){
			fouten.put("straat","Gelieven dit veld in te vullen");
		}
		if (huisnummer == null || huisnummer.isEmpty()){
			fouten.put("huisnummer","Gelieven dit veld in te vullen");
		}
		if (postcode == null || postcode.isEmpty()){
			fouten.put("postcode","Gelieven dit veld in te vullen");
		}
		if (gemeente == null || gemeente.isEmpty()){
			fouten.put("gemeente","Gelieven dit veld in te vullen");
		}
		try{
			afhalenOpsturen = Integer.parseInt(request.getParameter("afhalenOpsturen"));
			if (afhalenOpsturen < 0 || afhalenOpsturen > 1){
				fouten.put("afhalenOpsturen","Gelieven een optie te selecteren");
			}
		} catch (NumberFormatException ex){
			fouten.put("afhalenOpsturen","Gelieven een optie te selecteren");
		}


		if (fouten.isEmpty()){
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Map<WijnenEntity, Integer> basketMap = (HashMap) session.getAttribute("basket");
			Timestamp nu = new Timestamp(System.currentTimeMillis());
			BestelbonnenEntity bestelbon = new BestelbonnenEntity(nu, naam, straat, huisnummer, postcode, gemeente, afhalenOpsturen);

			for (Map.Entry<WijnenEntity, Integer> entry : basketMap.entrySet()) {
				WijnenEntity wijn = entry.getKey();
				int aantal = entry.getValue();
				BestelbonLijnen bestelbonLijn = new BestelbonLijnen(wijn.getId(), aantal, wijn.getPrijs());
				bestelbon.addBestelbonLijnen(bestelbonLijn);
				wijnService.toevoegenInBestelling(wijn.getId(),aantal);
			}

			try {
				bestelBonService.create(bestelbon);
				request.setAttribute("bestelbonnummer",bestelbon.getId());
				session.removeAttribute("basket");
				request.getRequestDispatcher(VIEW).forward(request, response);
			} catch (RuntimeException ex){
				throw ex;
			}

		} else {
			request.setAttribute("fouten",fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
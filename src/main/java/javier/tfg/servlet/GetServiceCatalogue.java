package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Catalogue;
import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;
import javier.tfg.domain.Waiter;

/**
 * Servlet implementation class GetServiceCatalogue
 */
public class GetServiceCatalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetServiceCatalogue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long serviceId = Long.parseLong(request.getParameter("serviceId"));
		String sessionToken = request.getHeader("sessionToken");
		Customer customer = ofy().load().type(Customer.class).filter("sessionToken", sessionToken).first().now();
		Waiter waiter = ofy().load().type(Waiter.class).filter("sessionToken", sessionToken).first().now();
		if(customer == null && waiter == null){
			response.setStatus(420);
			return;
		}
		List<Catalogue> catalog = ofy().load().type(Catalogue.class).filter("serviceId", serviceId).list();
		if(catalog.size() < 1){
			response.setStatus(402);
			return;
		}
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(catalog));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

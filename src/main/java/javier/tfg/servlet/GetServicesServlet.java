package javier.tfg.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Service;
import javier.tfg.domain.Waiter;
import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;

import static javier.tfg.service.OfyService.ofy;

/**
 * Servlet implementation class GetServicesServlet
 */
public class GetServicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetServicesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sessionToken = request.getHeader("sessionToken");
		String barServiceString = request.getParameter("barService");
		boolean barService;
		Customer customer = ofy().load().type(Customer.class).filter("sessionToken", sessionToken).first().now();
		Waiter waiter = ofy().load().type(Waiter.class).filter("sessionToken", sessionToken).first().now();
		if(customer == null && waiter == null){
			response.setStatus(420);
			return;
		}
		List<Service> services = null;
		if(barServiceString != null){
			barService = Boolean.valueOf(barServiceString);
			services = ofy().load().type(Service.class).filter("barService", barService ).list();
		}else{
			services = ofy().load().type(Service.class).list();
		}
		response.setContentType("application/json");
		Log pepe = new Log(new Gson().toJson(services));
		ofy().save().entity(pepe);
		response.getWriter().print(new Gson().toJson(services));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

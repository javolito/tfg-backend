package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Customer;
import javier.tfg.domain.Service;

/**
 * Servlet implementation class GetServiceObject
 */
public class GetServiceObject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetServiceObject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sessionToken = request.getHeader("sessionToken");
		Long serviceId = Long.parseLong(request.getParameter("serviceId"));
		Customer customer = ofy().load().type(Customer.class).filter("sessionToken", sessionToken).first().now();
		
		if(customer == null){
			response.setStatus(420);
			return;
		}
		Service service = ofy().load().type(Service.class).id(serviceId).now();
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(service));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

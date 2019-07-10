package javier.tfg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Customer;
import javier.tfg.processes.CustomerDTO;

import static javier.tfg.service.OfyService.ofy;

/**
 * Servlet implementation class UpdateOneSignalIdServlet
 */
public class UpdateOneSignalIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOneSignalIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionToken = request.getHeader("sessionToken");
		Customer customer = ofy().load().type(Customer.class).filter("sessionToken", sessionToken).first().now();
		if(customer == null){
			response.setStatus(420);
			return;
		}
		String oneSignalId = request.getParameter("oneSignalId");
		customer.setOneSignalId(oneSignalId);
		ofy().save().entity(customer).now();
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(new CustomerDTO(customer)));
	}

}

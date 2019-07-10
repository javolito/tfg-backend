package javier.tfg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Customer;
import javier.tfg.processes.CustomerDTO;
import javier.tfg.processes.SessionToken;

import static javier.tfg.service.OfyService.ofy;
import static javier.tfg.processes.Crypt.comprueba;

/**
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String oneSignalId = request.getParameter("oneSignalId");
		
		Customer customer = ofy().load().type(Customer.class).filter("email", email).first().now();
		
		if(customer == null){
			response.setStatus(401);
			return;
		}else{
			try {
				if(comprueba(customer.getPassword(), password)){
					String sessionToken = new SessionToken().generateToken(customer.getId().toString());
					customer.setSessionToken(sessionToken);
					customer.setOneSignalId(oneSignalId);
					ofy().save().entity(customer).now();
					response.setContentType("application/json");
					response.getWriter().print(new Gson().toJson(new CustomerDTO(customer)));
				}else{
					response.setStatus(401);
					return;
				}
			} catch (Exception e) {
				response.setStatus(499);
				return;
			}
			
		}
		
	}

}

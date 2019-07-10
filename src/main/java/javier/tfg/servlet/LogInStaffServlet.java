package javier.tfg.servlet;

import static javier.tfg.processes.Crypt.comprueba;
import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Receptionist;
import javier.tfg.domain.Waiter;
import javier.tfg.processes.WaiterDTO;
import javier.tfg.processes.ReceptionistDTO;
import javier.tfg.processes.SessionToken;

/**
 * Servlet implementation class LogInStaffServlet
 */
public class LogInStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInStaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Waiter waiter = ofy().load().type(Waiter.class).filter("email", email).first().now();
		Receptionist receptionist = ofy().load().type(Receptionist.class).filter("email", email).first().now();
		if(waiter == null && receptionist == null){
			response.setStatus(401);
			return;
		}else{
			if(waiter != null){
				try {
					if(comprueba(waiter.getPassword(), password)){
						String sessionToken = new SessionToken().generateToken(waiter.getId().toString());
						waiter.setSessionToken(sessionToken);
						ofy().save().entity(waiter).now();
						response.setContentType("application/json");
						response.getWriter().print(new Gson().toJson(new WaiterDTO(waiter)));
					}else{
						response.setStatus(401);
						return;
					}
				} catch (Exception e) {
					response.setStatus(499);
					return;
				}	
			}else if(receptionist != null){
				try {
					if(comprueba(receptionist.getPassword(), password)){
						String sessionToken = new SessionToken().generateToken(receptionist.getId().toString());
						receptionist.setSessionToken(sessionToken);
						ofy().save().entity(receptionist).now();
						response.setContentType("application/json");
						response.getWriter().print(new Gson().toJson(new ReceptionistDTO(receptionist)));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

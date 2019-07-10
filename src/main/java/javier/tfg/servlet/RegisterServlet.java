package javier.tfg.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;
import javier.tfg.domain.Receptionist;
import javier.tfg.domain.RoomBook;

import static javier.tfg.processes.Crypt.salt;

import static javier.tfg.service.OfyService.ofy;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        
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
		// TODO Auto-generated method stub
		String sessionToken = request.getHeader("sessionToken");
		Receptionist recept = ofy().load().type(Receptionist.class).filter("sessionToken", sessionToken).first().now();
		if(recept == null){
			response.setStatus(420);
			return;
		}
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("passwordRetry");
		int people = Integer.parseInt(request.getParameter("people"));
		password = password.substring(13).split(String.valueOf('"'))[0];
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
		Date startDate = null;
		try {
			startDate = format.parse(request.getParameter("startDate"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Date endDate = null;
		try {
			endDate = format.parse(request.getParameter("endDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			password = salt(password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Customer client = new Customer(name, lastName, email, password, roomNumber);
		
		Long clientId = ofy().save().entity(client).now().getId();
		RoomBook book = new RoomBook(clientId, true, roomNumber, startDate, endDate, people);
		ofy().save().entity(book).now();
	}

}

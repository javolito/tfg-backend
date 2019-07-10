package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;
import javier.tfg.domain.RoomBook;

/**
 * Servlet implementation class GetLinkAvailability
 */
public class GetLinkAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLinkAvailability() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionToken = request.getHeader("sessionToken");
		Customer customer = ofy().load().type(Customer.class).filter("sessionToken", sessionToken).first().now();
		if(customer == null){
			response.setStatus(420);
			return;
		}
		List<RoomBook> books = ofy().load().type(RoomBook.class).filter("roomNumber", customer.getRoomNumber()).list();
		String result = "false";
		for(RoomBook book: books){
			Log pepe = new Log("people: " + book.getPeople() + "; sizeNFCKeys: " + book.getNfcKeys().size());
			ofy().save().entity(pepe);
			if(book.isActive() == true){
				if(book.getPeople() >= book.getNfcKeys().size()){
					result = "true";
				}
			}
		}
		response.setContentType("text/html");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;
import javier.tfg.domain.Receptionist;
import javier.tfg.domain.RoomBook;

/**
 * Servlet implementation class LinkNFCServlet
 */
public class LinkNFCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkNFCServlet() {
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
		String customerIdString = request.getParameter("customerId");
		Long customerId = 0l;
		if(customerIdString != null){
			customerId = Long.parseLong(customerIdString);
		}
		String nfcKey = request.getParameter("nfcTag");
		String sessionToken = request.getHeader("sessionToken");
		String roomNumberString = request.getParameter("roomNumber");
		int roomNumber = 0;
		if(roomNumberString != null){
			roomNumber = Integer.parseInt(roomNumberString);
		}
		Customer customer = ofy().load().type(Customer.class).filter("sessionToken", sessionToken).first().now();
		Receptionist recept = ofy().load().type(Receptionist.class).filter("sessionToken", sessionToken).first().now();
		if(customer == null && recept == null){
			response.setStatus(420);
			return;
		}
		
		RoomBook book = null;
		List<RoomBook> books = ofy().load().type(RoomBook.class).filter("active", true).list();
		if(roomNumberString != null){
			for(RoomBook bookInstance : books){
				if(bookInstance.getRoomNumber() == roomNumber){
					book = bookInstance;
				}else if(bookInstance.getNfcKeys().contains(nfcKey)){
					response.setStatus(427);
					return;
				}
			}
		}else if(customerIdString != null){
			for(RoomBook bookInstance2 : books){
				if(bookInstance2.getCustomerId().equals(customerId)){
					book = bookInstance2;
				}else if(bookInstance2.getNfcKeys().contains(nfcKey)){
					response.setStatus(427);
					return;
				}
			}
		}
		
		if(book == null){
			response.setStatus(425);
			return;
		}else{
			boolean isAdded = book.addNFCKey(nfcKey);
			if(!isAdded){
				response.setStatus(426);
				return;
			}else{
				ofy().save().entity(book).now();
			}
		}
	}

}

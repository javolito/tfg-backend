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
import javier.tfg.domain.Waiter;
import javier.tfg.processes.CustomerDTO;

/**
 * Servlet implementation class GetUserIdWithNFCId
 */
public class GetUserIdWithNFCId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserIdWithNFCId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nfcKey = request.getParameter("nfcTag");
		String sessionToken = request.getHeader("sessionToken");
		
		if(ofy().load().type(Waiter.class).filter("sessionToken =",sessionToken).list().size() <= 0){
			response.setStatus(420);
			return;
		}
		Customer client = null;
		List<RoomBook> books = ofy().load().type(RoomBook.class).filter("active", true).list();
		for(RoomBook book: books){
			if(book.getNfcKeys().contains(nfcKey)){
				client = ofy().load().type(Customer.class).id(book.getCustomerId()).now();
			}
		}

		if(client == null){
			response.setStatus(403);
			return;
		}
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(new CustomerDTO(client)));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

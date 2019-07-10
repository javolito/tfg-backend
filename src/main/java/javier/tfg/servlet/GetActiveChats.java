package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import javier.tfg.domain.Receptionist;
import javier.tfg.domain.RoomBook;
import javier.tfg.processes.CustomerDTO;
import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;

/**
 * Servlet implementation class GetActiveChats
 */
public class GetActiveChats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetActiveChats() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sessionToken = request.getHeader("sessionToken");
		if(ofy().load().type(Receptionist.class).filter("sessionToken =",sessionToken).list().size() <= 0){
			response.setStatus(420);
			return;
		}
		List<RoomBook> activeChatList = ofy().load().type(RoomBook.class).filter("active", true).list();
		List<CustomerDTO> jsonList = new ArrayList<>();
		for(RoomBook book: activeChatList){
			Customer cust = ofy().load().type(Customer.class).id(book.getCustomerId()).now();
			CustomerDTO custDTO = new CustomerDTO(cust);
			jsonList.add(custDTO);
		}
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(jsonList));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

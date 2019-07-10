package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javier.tfg.domain.Receptionist;
import javier.tfg.domain.RoomBook;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		String sessionToken = request.getHeader("sessionToken");
		if(ofy().load().type(Receptionist.class).filter("sessionToken =",sessionToken).list().size() <= 0){
			response.setStatus(420);
			return;
		}
		List<RoomBook> books = ofy().load().type(RoomBook.class).filter("roomNumber", roomNumber).list();
		boolean findActive = false;
		for(RoomBook book: books){
			if(book.isActive()){
				book.setActive(false);
				ofy().save().entity(book).now();
				findActive = true;
			}
		}
		if(!findActive){
			response.setStatus(430);
			return;
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

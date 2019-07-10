package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;
import javier.tfg.domain.Purchase;
import javier.tfg.domain.Waiter;

/**
 * Servlet implementation class RegisterServiceSale
 */
public class RegisterServiceSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServiceSale() {
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
		Long serviceId = Long.parseLong(request.getParameter("serviceId"));
		Long catalogId = Long.parseLong(request.getParameter("catalogId"));
		Long customerId = Long.parseLong(request.getParameter("customerId"));
		String stripeCustomerId = request.getParameter("stripeCustomerId");
		Double amount = Double.parseDouble(request.getParameter("totalAmount"));
		String sessionToken = request.getHeader("sessionToken");
		Date bookDate = null;
		
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss", Locale.ENGLISH);
			bookDate = format.parse(request.getParameter("bookHour"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		
		List<Purchase> saleList = ofy().load().type(Purchase.class).filter("serviceId", serviceId).list();
		int counter = 0;
		for(Purchase sale: saleList){
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(sale.getBookDate());
			cal2.setTime(bookDate);
			boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
			                  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
			Log pepe1 = new Log(cal1.getTime().toString()+ ":" +cal2.getTime().toString() + ";BOOLEAN:" + sameDay);
			ofy().save().entity(pepe1);
			if(sameDay){
				counter = counter+1;
			}
		}
		Log pepe = new Log(counter + ":COUNTER");
		ofy().save().entity(pepe);
		if(counter > 5){
			response.setStatus(431);
			return;
		}
		
		Customer client = ofy().load().type(Customer.class).id(customerId).now();
		if(client == null){
			response.setStatus(401);
			return;
		}
		if(!client.getSessionToken().equals(sessionToken)){
			response.setStatus(420);
			return;
		}
		
		Stripe.apiKey = "sk_test_KkKv1E426JjKY5Y1sXWtitv300j7rPYRWH";

		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("amount", amount.intValue()*100);
		customerParams.put("currency", "eur");
		customerParams.put("customer", stripeCustomerId);
		try {
			Charge.create(customerParams);
		} catch (StripeException e) {
		    e.printStackTrace();
	    }
		
		
		Purchase purchase = new Purchase(serviceId, customerId, catalogId,  new Date(), bookDate, amount);
		ofy().save().entity(purchase).now();
	}

}

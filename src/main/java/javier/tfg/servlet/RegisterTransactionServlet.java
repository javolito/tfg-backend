package javier.tfg.servlet;

import static javier.tfg.service.OfyService.ofy;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;
import javier.tfg.domain.Purchase;
import javier.tfg.domain.Waiter;

public class RegisterTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RegisterTransactionServlet() {
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long customerId = Long.parseLong(request.getParameter("customerId"));
		Long waiterId = Long.parseLong(request.getParameter("waiterId"));
		String stripeCustomerId = request.getParameter("stripeCustomerId");
		Double amount = Double.parseDouble(request.getParameter("totalAmount"));
		String sessionToken = request.getHeader("sessionToken");
		Long serviceId = Long.parseLong(request.getParameter("serviceId"));
		String productsList = request.getParameter("products");
		
		if(ofy().load().type(Waiter.class).filter("sessionToken", sessionToken).list().size() <= 0){
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
		
		Purchase purchase = new Purchase(serviceId, customerId, productsList, waiterId, new Date(), new Date(), amount);
		ofy().save().entity(purchase).now();
	}
}

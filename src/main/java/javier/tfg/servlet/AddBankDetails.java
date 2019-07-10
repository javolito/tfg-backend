package javier.tfg.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stripe.exception.StripeException;
import com.google.gson.Gson;
import com.stripe.Stripe;

import javier.tfg.domain.Customer;
import javier.tfg.processes.CustomerDTO;

import static javier.tfg.service.OfyService.ofy;

public class AddBankDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sessionToken = request.getHeader("sessionToken");
		Customer customer = ofy().load().type(Customer.class).filter("sessionToken", sessionToken).first().now();
		if(customer == null){
			response.setStatus(420);
			return;
		}
		String stripeToken = request.getParameter("stripeToken");
		String email = request.getParameter("email");
   
		Stripe.apiKey = "sk_test_KkKv1E426JjKY5Y1sXWtitv300j7rPYRWH";

	   Map<String, Object> customerParams = new HashMap<String, Object>();
	   customerParams.put("email", email);
	   customerParams.put("source", stripeToken);
	   com.stripe.model.Customer cust = null;
	   try {
		   cust = com.stripe.model.Customer.create(customerParams);
	   } catch (StripeException e) {
		   response.setStatus(e.getStatusCode());
		   return;
	   }
	   
	   
	   
	   customer.setStripeId(cust.getId());
	   ofy().save().entity(customer).now();
	   response.setContentType("application/json");
	   response.getWriter().print(new Gson().toJson(new CustomerDTO(customer)));
	   
	}
}

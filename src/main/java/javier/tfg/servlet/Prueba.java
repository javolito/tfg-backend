package javier.tfg.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javier.tfg.domain.Catalogue;
import javier.tfg.domain.Customer;
import javier.tfg.domain.Log;
import javier.tfg.domain.Product;
import javier.tfg.domain.Receptionist;
import javier.tfg.domain.RoomBook;
import javier.tfg.domain.Service;
import javier.tfg.domain.Waiter;

import static javier.tfg.service.OfyService.ofy;

/**
 * Servlet implementation class Prueba
 */
public class Prueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prueba() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Customer customer = new Customer("Javier", "Tfg", "javier@tfg.com", "-12057-106-5-2-174369-39-6-93-7-82-66105-45", 25);
		//Service poolBar = new Service("Bar Piscina", "Bar piscina situado en piscina principal", true);
		//Service lavanderia = new Service("Lavandería", "Esta es la descripción de la lavandería");
		//Long poolBarId = ofy().save().entity(poolBar).now().getId();
		//Product fanta = new Product("Fanta Naranja", "Refresco de naranja", 1);
		//Product cola = new Product("Coca Cola", "Refresco de cola", 1);
//		Product blanco = new Product("Arehucas Blanco", "Ron blanco", 0);
//		Long fantaId = ofy().save().entity(fanta).now().getId();
//		Long colaId = ofy().save().entity(cola).now().getId();
//		Long blancoId = ofy().save().entity(blanco).now().getId();
		//Catalogue catalog = new Catalogue(poolBarId, fantaId, 3,fanta.getName(), fanta.getDescription(), false, fanta.getType());
		//Catalogue catalog2 = new Catalogue(poolBarId, colaId, 3,cola.getName(), cola.getDescription(), false, cola.getType());
		//Catalogue catalog3 = new Catalogue(poolBarId, blancoId, 5,blanco.getName(), blanco.getDescription(), false, blanco.getType());
		//Waiter waiter = new Waiter("Mateo", "Alga", "mateo@pecerasucia.com", "9478-52829893-9022-70-85-29107-811612478");
		//ofy().save().entities(catalog,catalog2,catalog3).now();
		//Receptionist recept = new Receptionist("Recptionist1", "Recept", "receptionist@gmail.com", "6576-89-106-5037439763-122-22-3-46-6-4598");
		//RoomBook book = new RoomBook(Long.parseLong("5664902681198592"),new ArrayList<>(), true, 205, new Date(), new Date());
		//ofy().save().entity(book);
		/*Customer cust =ofy().load().type(Customer.class).filter("email", "anaguefus@gmail.com").first().now();
		RoomBook book = ofy().load().type(RoomBook.class).filter("customerId", cust.getId()).first().now();
		Log pepe = new Log("cust: " + cust.getId() + "; book: " + book.getId());
		ofy().save().entity(pepe).now();
		book.addNFCKey(cust.getNfcKey());
		book.addNFCKey("4,112,-68,-102,-86,74,-120");
		ofy().save().entity(book).now();*/
		
		String sDate1="31/12/1998";
		Date startDate = null;
		try {
			startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("31/12/1998 09:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date endDate = null;
		try {
			endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("31/12/1998 20:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Service spa = new Service("Experiencias", "Aventuras y demás", false, true , startDate, endDate);
		Long spaId = ofy().save().entity(spa).now().getId();
		Product spaPara2 = new Product("Tirolina", "Duración de 2 horas. Oferta válida para una persona");
		Product spaPara2Cena = new Product("Kayak","Duración de 4 horas. Oferta válida para una persona");
		Product spaPara1 = new Product("Visita cultural", "Incluye excursión por puntos de interés turístico");
		Product bonoSpa = new Product("Senderismo", "Duración de 10 horas. Ruta con guía, válido para una persona");
		Long spa1 = ofy().save().entity(spaPara1).now().getId();
		Long spa2 = ofy().save().entity(spaPara2).now().getId();
		Long spa2C = ofy().save().entity(spaPara2Cena).now().getId();
		Long spaB = ofy().save().entity(bonoSpa).now().getId();
		Catalogue spa1Cat = new Catalogue(spaId, spa1, 30, spaPara1.getName(), spaPara1.getDescription(), "");
		Catalogue spa2Cat = new Catalogue(spaId, spa2, 45, spaPara2.getName(), spaPara2.getDescription(), "");
		Catalogue spa2CCat = new Catalogue(spaId, spa2C, 40, spaPara2Cena.getName(), spaPara2Cena.getDescription(), "");
		Catalogue spaBCat = new Catalogue(spaId, spaB, 50, bonoSpa.getName(), bonoSpa.getDescription(), "");
		ofy().save().entities(spa1Cat, spa2Cat, spa2CCat, spaBCat).now();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

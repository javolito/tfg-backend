package javier.tfg.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import javier.tfg.domain.*;

public class OfyService {

    public OfyService(){}
	static {
		factory().register(Customer.class);
		factory().register(Purchase.class);
        factory().register(Receptionist.class);
        factory().register(Service.class);
        factory().register(Waiter.class);
        factory().register(Product.class);
        factory().register(Catalogue.class);
        factory().register(Log.class);
        factory().register(RoomBook.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}

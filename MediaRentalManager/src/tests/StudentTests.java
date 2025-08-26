package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import mediaRentalManager.MediaRentalManager;

public class StudentTests {

	@Test
	public void processingRequests() {
		MediaRentalManager m = new MediaRentalManager();
		
		m.addCustomer("Arush", "Blaney", "UNLIMITED");
		m.addAlbum("Ye", 4, "Kanye", "Violent Crimes, Ghost Town");
		m.addMovie("Interstellar", 4, "PG");
		m.addMovie("Harry Potter", 4, "PG");
		
		m.addToQueue("Arush", "Ye");
		m.addToQueue("Arush", "Interstellar");
		m.addToQueue("Arush", "Harry Potter");
		
		System.out.println(m.processRequests());
		
		
	}

}

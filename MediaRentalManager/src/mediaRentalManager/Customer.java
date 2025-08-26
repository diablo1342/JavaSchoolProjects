package mediaRentalManager;
import java.lang.Comparable;
import java.util.ArrayList;

public class Customer implements Comparable<Customer> {
	String name;
	String address;
	String plan;
	public static int NUMLIMITED = 2;
	ArrayList<String> queued = new ArrayList<String>();
	ArrayList<String> rented = new ArrayList<String>();
	
	public Customer(String name, String address, String plan) {
		this.name = name;
		this.address = address;
		this.plan = plan;
	}
	
	public int compareTo(Customer other) {
		return this.name.compareTo(other.name);
	}
}

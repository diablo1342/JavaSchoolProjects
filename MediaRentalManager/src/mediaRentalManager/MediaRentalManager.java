package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MediaRentalManager implements MediaRentalManagerInt {
	public ArrayList<Customer> customers = new ArrayList<Customer>();
	public ArrayList<Media> content = new ArrayList<Media>();
	
	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {
		Album a = new Album(title, copiesAvailable, artist, songs);
		content.add(a);
	}
	
	public void addCustomer(String name, String address, String plan) {
		Customer c = new Customer(name, address, plan);
		customers.add(c);
	}
	
	public void addMovie(String title, int copiesAvailable, String rating) {
		Movie m = new Movie(title, copiesAvailable, rating);
		content.add(m);
	}
	
	public void setLimitedPlanLimit(int value) {
		Customer.NUMLIMITED = value;
	}
	
	public String getAllCustomersInfo() {
		Collections.sort(customers);
		String answer = "";
		answer += "***** Customers' Information *****\n";
		for(int i = 0; i<customers.size(); i++) {
			answer = answer + "Name: " + customers.get(i).name + ", Address: " + customers.get(i).address 
					+ ", Plan: " + customers.get(i).plan + "\n";
			
			answer = answer + "Rented: " + customers.get(i).rented + "\n";
			
			answer = answer + "Queue: " + customers.get(i).queued + "\n";
			
		}
		return answer;
	}
	
	public String getAllMediaInfo() {
		
		//Couldn't use comparable because Media interface cannot implement another interface
		for(int i = 0; i < content.size() - 1; i++) {
			for(int j = 0; j<content.size() -1 - i; j++) {
				if(content.get(j).getTitle().compareTo(content.get(j+1).getTitle()) > 0) {
					Media temp = content.get(j);
					content.set(j, content.get(j+1));
					content.set(j+1, temp);
				}
			}
		}
		
		String answer = "";
		answer += "***** Media Information *****\n";
		for(int i = 0; i<content.size(); i++) {
			Media media = content.get(i);
			if(media instanceof Album) {
				Album album = (Album) media;
				answer = answer + "Title: " + album.getTitle() + ", Copies Available: " + album.getNumCopies()
				+ ", Artist: " + album.getArtist() + ", Songs: " + album.getSongs() + "\n";
			}
			
			if(media instanceof Movie) {
				Movie movie = (Movie) media;
				answer = answer + "Title: " + movie.getTitle() + ", Copies Available: " + movie.getNumCopies()
				+ ", Rating: " + movie.getRating() + "\n";
			}
			
		}
		return answer;
		
	}
	
	public boolean addToQueue(String customerName, String mediaTitle) {
		for(int i = 0; i<customers.size(); i++) {
			if(customers.get(i).name.equals(customerName)) {
				int queueLength = customers.get(i).queued.size();
				for(int j = 0; j < queueLength; j++ ) {
					if(mediaTitle.equals(customers.get(i).queued.get(j))) {
						return false;
					}
				}
				
				customers.get(i).queued.add(mediaTitle);
				return true;
					
			}
		}
		return false;
		
		
	}
	
	public boolean removeFromQueue(String customerName, String mediaTitle) {
		for(int i = 0; i<customers.size(); i++) {
			if(customers.get(i).name.equals(customerName)) {
				int queueLength = customers.get(i).queued.size();
				for(int j = 0; j < queueLength; j++ ) {
					if(mediaTitle.equals(customers.get(i).queued.get(j))) {
						customers.get(i).queued.remove(j);
						return true;
					}
				}
				
				return false;
					
			}
		}
		return false;
	}
	
	public String processRequests() {
		Collections.sort(customers);
		String answer = "";
		for(int i = 0; i<customers.size(); i++) {
			for(int j = 0; j<customers.get(i).queued.size(); j++) {
				int mediaIndex = 0;
				for(int k = 0; k<content.size(); k++) {
					if(customers.get(i).queued.get(j).equals(content.get(k).getTitle())) {
						mediaIndex = k;
					}
				}
				if(content.get(mediaIndex).getNumCopies() >0) {
					if(customers.get(i).plan.equals("UNLIMITED")) {
						customers.get(i).rented.add(content.get(mediaIndex).getTitle());
						customers.get(i).queued.remove(content.get(mediaIndex).getTitle());
						j--;
						content.get(mediaIndex).removeCopy();
						answer += "Sending " + content.get(mediaIndex).getTitle() + " to " +
						customers.get(i).name + "\n";
					}
					if(customers.get(i).plan.equals("LIMITED") && customers.get(i).rented.size()<Customer.NUMLIMITED) {
						customers.get(i).rented.add(content.get(mediaIndex).getTitle());
						customers.get(i).queued.remove(content.get(mediaIndex).getTitle());
						j--;
						content.get(mediaIndex).removeCopy();
						answer += "Sending " + content.get(mediaIndex).getTitle() + " to " +
								customers.get(i).name + "\n";
					}
				}
				
			}
		}
		return answer;
	}
	
	public boolean returnMedia(String customerName, String mediaTitle) {
		int customerIndex = -1;
		int mediaIndex = -1;
		
		for(int i = 0; i<customers.size(); i++) {
			if(customers.get(i).name.equals(customerName)) {
				customerIndex = i;
			}
		}
		
		if(customerIndex == -1) {
			return false;
		}
		
		Customer c = customers.get(customerIndex);
		
		c.rented.remove(mediaTitle);
		
		for(int i = 0; i<content.size(); i++) {
			if(content.get(i).getTitle().equals(mediaTitle)) {
				mediaIndex = i;
			}
		}
		
		if(mediaIndex == -1) {
			return false;
		}
		
		content.get(mediaIndex).addCopy();
		
		return true;
		
	}
	
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs){
		//Couldnt use comparable because Media interface cannot implement another interface
		for(int i = 0; i < content.size() - 1; i++) {
			for(int j = 0; j<content.size() -1 - i; j++) {
				if(content.get(j).getTitle().compareTo(content.get(j+1).getTitle()) > 0) {
					Media temp = content.get(j);
					content.set(j, content.get(j+1));
					content.set(j+1, temp);
				}
			}
				}
		
		ArrayList<String> titles = new ArrayList<String>();
		
		for(int i = 0; i<content.size(); i++) {
			Media media = content.get(i);
			if(media instanceof Album) {
				Album album = (Album) media;
				if((title == null || title.equals(album.getTitle())) && (artist == null || 
				artist.equals(album.getArtist())) && (songs == null || album.getSongs().contains(songs))
				&& rating == null) {
					titles.add(album.getTitle());
				}
			}
			
			else if(media instanceof Movie) {
				Movie movie = (Movie) media;
				if((title == null || title.equals(movie.getTitle())) && (rating == null || 
				rating.equals(movie.getRating())) && artist == null && songs == null) {
					titles.add(movie.getTitle());
				}
			}
			
		}
		
		return titles;
		
		
	}
	
}

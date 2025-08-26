package mediaRentalManager;

public class Movie implements Media {
	String title;
	int numCopies;
	String rating;
	
	public Movie(String title, int numCopies, String rating) {
		this.title = title;
		this.numCopies = numCopies;
		this.rating = rating;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getNumCopies() {
		return numCopies;
	}
	
	public void removeCopy() {
		numCopies--;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void addCopy() {
		numCopies++;
	}
}

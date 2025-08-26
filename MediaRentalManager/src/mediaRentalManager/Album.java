package mediaRentalManager;

public class Album implements Media {
	String title;
	int numCopies;
	String artist;
	String songs;
	
	public Album(String title, int numCopies, String artist, String songs) {
		this.title = title;
		this.numCopies = numCopies;
		this.artist = artist;
		this.songs = songs;
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
	
	public String getArtist() {
		return artist;
	}
	
	public String getSongs() {
		return songs;
	}
	
	public void addCopy() {
		numCopies++;
	}
}

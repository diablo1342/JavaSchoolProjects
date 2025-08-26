package mediaRentalManager;

public interface Media  {
	public String getTitle();	
	public int getNumCopies();
	public void removeCopy();
	public void addCopy();
}

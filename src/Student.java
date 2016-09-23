/**
 *  Student.java
 */

public class Student
{
	int bannerID;
	String lastName;
	String firstName;
	String emailAddress;
	
	
	public Student(int bID, String last, String first, String email) 
	{
		setStudent(bID, last, first, email);
	}
	public void setStudent(int bID, String last, String first, String email)
	{
		setBannerID(bID);
		setLastName(last);
		setFirstName(first);
		setEmailAddress(email);
	}
	private void setBannerID(int bID)
	{
		bannerID = bID;
	}
	private void setLastName(String last)
	{
		lastName = last;
	}
	private void setFirstName(String first)
	{
		firstName = first;
	}
	private void setEmailAddress(String email)
	{
		emailAddress = email;
	}
}

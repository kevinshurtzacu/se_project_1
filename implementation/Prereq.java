package implementation;

public class Prereq
{
	// Prereq Information
	String subject; // Course Subject
	String number;	// Course Number
	String grade;   // Course Grade
	
	public Prereq(String sbt, String num, String grd)
	{
		setPrereq(sbt, num, grd);
	}
	
	public void setPrereq(String sbt, String num, String grd)
	{
		setPrereqSubject(sbt);
		setPrereqNumber(num);
		setPrereqGrade(grd);
	}
	
	public void setPrereqSubject(String sbt)
	{
		subject = sbt;
	}
	
	public void setPrereqNumber(String num)
	{
		number = num;
	}
	
	public void setPrereqGrade(String grd)
	{
		grade = grd;
	}
	
	public String getPrereqSubject()
	{
		return subject;
	}
	
	public String getPrereqNumber()
	{
		return number;
	}
	
	public String getPrereqGrade()
	{
		return grade;
	}
	
	public boolean equals(Prereq other)
	{
		if (subject != other.getPrereqSubject())
			return false;
		
		if (number != other.getPrereqNumber())
			return false;
		
		if (grade != other.getPrereqGrade())
			return false;
		
		return true;
	}
	
	public boolean equals(Course other)
	{
		if (subject != other.getCourseSubject())
			return false;
		if (number != other.getCourseNumber())
			return false;
		if (grade != other.getCourseGrade())
			return false;
		
		return true;
	}
}
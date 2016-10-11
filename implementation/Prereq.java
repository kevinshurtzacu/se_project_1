package implementation;

public class Prereq
{
	// Prereq Information
	private String subject; // course subject
	private String number;	// course number
	private String title;   // course title
	private String grade;   // course grade
	
	public Prereq(String sbt, String num, String ttl, String grd)
	{
		setPrereq(sbt, title, num, grd);
	}
	
	public void setPrereq(String sbt, String num, String ttl, String grd)
	{
		setPrereqSubject(sbt);
		setPrereqNumber(num);
        setPrereqTitle(ttl);
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

	public void setPrereqTitle(String ttl)
    {
        title = ttl;
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

	public String getPrereqTitle()
    {
        return title;
    }

	public String getPrereqGrade()
	{
		return grade;
	}

	@Override
	public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (other == this)
            return true;

        if (other instanceof Prereq)
            return equals(other);

        return false;
    }

	private boolean equals(Prereq other)
	{
		if (!getPrereqSubject().equals(other.getPrereqSubject()))
			return false;
		
		if (!getPrereqNumber().equals(other.getPrereqNumber()))
			return false;

        if (!getPrereqTitle().equals(other.getPrereqTitle()))
            return false;

		if (!getPrereqGrade().equals(other.getPrereqGrade()))
			return false;
		
		return true;
	}
	
	public boolean equals(Course other)
	{
		if (!getPrereqSubject().equals(other.getCourseSubject()))
			return false;

		if (!getPrereqNumber().equals(other.getCourseNumber()))
			return false;

        if (!getPrereqTitle().equals(other.getCourseTitle()))
            return false;

        if (!getPrereqGrade().equals(other.getCourseGrade()))
			return false;
		
		return true;
	}
}
package implementation;

import java.util.Objects;

public class Prereq
{
	// Prereq Information
	private String subject; // course subject
	private String number;	// course number
	private String title;   // course title
	private String grade;   // course grade
	
	public Prereq(String sbt, String num, String ttl, String grd)
	{
        // System.out.println("\t\tALL GOOD 1: " + sbt + " " + num + " " + ttl + " " + grd);
		setPrereq(sbt, num, ttl, grd);
	}
	
	public void setPrereq(String sbt, String num, String ttl, String grd)
	{
        // System.out.print("\t\tALL GOOD 2: " + sbt + " " + num + " " + ttl + " " + grd);
		setPrereqSubject(sbt);
		setPrereqNumber(num);
        setPrereqTitle(ttl);
		setPrereqGrade(grd);
	}
	
	public void setPrereqSubject(String sbt)
	{
		// System.out.print(" `");
        subject = sbt;
	}
	
	public void setPrereqNumber(String num)
	{
		// System.out.print(" ~");
        number = num;
	}

	public void setPrereqTitle(String ttl)
    {
        // System.out.print(" *");
        title = ttl;
    }

	public void setPrereqGrade(String grd)
	{
        // System.out.println(" ^");
		grade = grd;
	}
	
	public String getPrereqSubject()
	{
		// System.out.print("[SUB: " + subject + "] ");
		return subject;
	}
	
	public String getPrereqNumber()
	{
        // System.out.print("[NUM: " + number + "] ");
		return number;
	}

	public String getPrereqTitle()
    {
        // System.out.print("[TTL: " + title + "] ");
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
            return equals((Prereq)other);

		if (other instanceof Course)
		    return equals((Course)other);

        return false;
    }

	private boolean equals(Prereq other)
	{
		if (!getPrereqSubject().equals(other.getPrereqSubject()))
			return false;
		
		if (!getPrereqNumber().equals(other.getPrereqNumber()))
			return false;

		if (!getPrereqGrade().equals(other.getPrereqGrade()))
			return false;
		
		return true;
	}
	
	private boolean equals(Course other)
	{
		if (!getPrereqSubject().equals(other.getCourseSubject()))
			return false;

		if (!getPrereqNumber().equals(other.getCourseNumber()))
			return false;

        if (!getPrereqGrade().equals(other.getCourseGrade()))
			return false;
		
		return true;
	}

	@Override
	public int hashCode()
    {
        return Objects.hash(getPrereqSubject(), getPrereqNumber());
    }
}
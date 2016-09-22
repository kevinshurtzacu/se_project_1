/* 
 * Last Modified By: Kevin Shurtz
 * Last Modified Date: 09/21/2016
 *
 * Notes: This will represent a single instance of a student's course.
 *        The Course object should be aggregated by students. Several 
 *        instances of this object will eventually be populated with data 
 *        from a database.
 * 
 * Course.java
 *     Public Object:   Course
 *     Public Enum:     Term
 * 
 * Table of Contents
 * 
 */

public class Course
{
    // Course Term Enum
    public enum Term {
        FALL,       // Fall term
        SPRING,     // Spring term
        JAN_SHORT,  // January short-course
        MAY,        // "Maymester" short-course
    }
    
    // Course characteristics
    int crn;                // Course Registration Number
    int banner;             // Banner ID
    
    String coursetitle;     // Course title (no number)
    int courseNumber;       // Course number (ie. 101, 102, etc.)
    String courseNumberExt; // Course number extension (ie. 1, 2, H2, etc.)
    
    // Time characteristics
    int courseYear;         // Year course is taken
    Term courseTerm;        // Term during which course is taken
    
    // Student data
    double courseGrade;     // Student's grade in course (from 0.0 to 100.0)
    boolean inCourseNow;    // Whether the student is in the course now
    
	// Construct Course
	public Course(int crnNum, int bannerNum, String title, int courseNum, 
	        String ext, int year, Term cTerm, double grade, boolean now)
	{
	    setCourse(crnNum, bannerNum, title, courseNum, ext, year, cTerm, 
	            grade, now);
	}
	
	// Set the course instance variables
	public void setCourse(int crnNum, int bannerNum, String title, int courseNum, 
	        String ext, int year, Term cTerm, double grade, boolean now)
    {
        // Validated assignments
        setCRN(crnNum);             // Assign crn
        setBanner(banner);          // Assign banner
        setTitle(courseTitle);      // Assign title
        setCourseNumber(courseNum); // Assign course number
        setCourseNumberExt(ext);    // Assign course number extension
        setCourseYear(year);        // Assign course year
        setCourseTerm(Term);        // Assign course term
        setCourseGrade(grade);      // Assign course grade
        
        // No validation necessary
        inCourseNow = now;          // Assign if in course now
    }
    
    // Validate and set the CRN
    private void setCRN(int cnrNum) throws IllegalArgumentException
    {
        crn = crnNum;
    }
    
    // Validate and set the Banner ID
    private void setBanner(int bannerNum) throws IllegalArgumentException
    {
        // If the Banner ID is the wrong size
        if (Integer.parseInt(banner).length() != 9)
            throw new IllegalArgumentException("Banner ID has incorrect number of digits (has " + Integer.parseInt(banner).length() + ", needs 9)");
        
        // Otherwise, set the Banner ID
        banner = bannerNum;
    }
    
    // Validate and set the course title
    private void setTitle(String title) throws IllegalArgumentException
    {
        courseTitle = title;
    }
    
    // Validate and set the course number
    private void setCourseNumber(int courseNum) throws IllegalArgumentException
    {
        courseNumber = courseNum;
    }
    
    // Validate and set the course number extension
    private void setCourseNumberExt(String ext) throws IllegalArgumentException
    {
        courseNumberExt = ext;
    }
    
    // Validate and set the year
    private void setCourseYear(int year) throws IllegalArgumentException
    {
        // If the year is from before ACU was founded
        if (year < 1906)
            throw new IllegalArgumentException("Invalid year (before 1906)");
        
        // If a typo has rendered a year with more than four characters
        if (Integer.parseInt(year).length() > 4)
            throw new IllegalArgumentException("Invalid year (more than 4 characters)");
        
        // If a typo has rendered a year with less than four characters
        if (Integer.parseInt(year).length() < 4)
            throw new IllegalArgumentException("Invalid year (less than 4 characters");
        
        courseYear = year;
    }
    
    // Validate and set the Term
    private void setCourseTerm(Term cTerm) throws IllegalArgumentException
    {
        courseTerm = cTerm;
    }
    
    // Validate and set the grade
    private void setCourseGrade(int grade) throws IllegalArgumentException
    {
        if (grade > 100)
            throw new IllegalArgumentException("Grade above 100% not possible");
        
        if (grade < 0)
            throw new IllegalArgumentException("Grade below 0% not possible");
        
        courseGrade = grade;
    }
    
    // Set whether in course now
    private void setInCourseNow(boolean now) throws IllegalArgumentException
    {
        inCourseNow = now;
    }
}


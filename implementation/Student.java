package implementation;

/**
 * Represents an ACU student taking a course.  Maintains data for
 * <ul>
 *     <li>banner ID (Student's Banner ID)</li>
 *     <li>student section</li>
 *     <li>student year</li>
 *     <li>student term</li>
 *     <li>student grade</li>
 *     <li>whether student is active</li>
 * </ul>
 *
 * <p>
 *     Students and StudentProfile will be able to reference one another
 *     through their identifiers.  StudentProfile is presently capable of equating
 *     itself with a Student object by use of the default equals method.
 * </p>
 *
 * @author Kevin Shurtz
 * @author Isaak Ramirez
 * @version 1.0
 */
public class Student
{
    // Student characteristics
    int studentBannerID;    // Student Banner ID
    String studentSection;  // Course number extension (1, 2, H2, etc.)

    // Time characteristics
    int studentYear;        // Year course is taken
    Term studentTerm;       // Term during which course is taken
    
    // Student data
    String studentGrade;    // Student's grade in course (from from F to A)
    boolean takingNow;      // Whether the student is in the course now

    /**
     * Constructs a Student object to represent a student's course.  Student
     * delegates the instantiation of instance variables to method setStudent.
     *
     * @param bnrID     banner ID
     * @param section   student section, such as 01, H1, etc
     * @param year      year the student takes the course
     * @param cTerm     term the student takes the course
     * @param grade     student grade in the course out of 100 points
     * @param now       whether the student is presently in the course
     * @see             Student#setStudent(int, String, int, Term, double, boolean)
     */
	public Student(int bnrID, String section, int year, Term cTerm, double grade, boolean now)
	{
	    setStudent(bnrID, section, year, cTerm, grade, now);
	}

    /**
     * Assigns values to Student instance variables.  The function
     * delegate assignment to each of the assignment functions
     * for each instance variable, many of which validate the input.
     *
     * @param bnrID     banner ID
     * @param section   student section, such as 01, H1, etc
     * @param year      year the student takes the course
     * @param cTerm     term the student takes the course
     * @param grade     student grade in the course out of 100 points
     * @param now       whether the student is presently in the course
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     */
	public void setStudent(int bnrID, String section, int year, Term cTerm, double grade, boolean now)
    {
        setStudentBannerID(bnrID);      // Assign crn
        setStudentSection(section);     // Assign student section
        setStudentYear(year);           // Assign student year
        setStudentTerm(cTerm);          // Assign student term
        setStudentGrade(grade);         // Assign student grade
        setTakingNow(now);              // Assign if in course now
    }

    /**
     * Assigns the student's banner ID.  No validation is conducted.
     *
     * @param bnrID     student banner ID, a unique course identifier
     */
    public void setStudentBannerID(int bnrID)
    {
        studentBannerID = bnrID;
    }

    /**
     * Assigns the course number extension.  Examples include '01', '02', and 'H2'.
     * The full identifier for a course can be created by concatenating the
     * course number with its extension, joined by a '.'.  For example,
     * 101.H1
     *
     * @param ext       course number extension
     */
    public void setStudentSection(String ext)
    {
        studentSection = ext;
    }

    /**
     * Assigns the year in which the course is taken.  Validation insures that the
     * year is a reasonable input.
     *
     * @param year      the year in which the course is being taken
     * @throws IllegalArgumentException If the year is from before 1906,
     *                                  more than four characters, or
     *                                  less than four characters
     */
    public void setStudentYear(int year)
    {
        // If the year is from before ACU was founded
        if (year < 1906)
            throw new IllegalArgumentException("Invalid year (before 1906)");
        
        // If a typo has rendered a year with more than four characters
        if (Integer.toString(year).length() > 4)
            throw new IllegalArgumentException("Invalid year (more than 4 characters)");
        
        // If a typo has rendered a year with less than four characters
        if (Integer.toString(year).length() < 4)
            throw new IllegalArgumentException("Invalid year (less than 4 characters");
        
        studentYear = year;
    }

    /**
     * Assigns the term in which a course is taken.  The term is represented as
     * a Term enum, which can assume one of several predefined values.  Term exists
     * independently of the Course object.
     *
     * @param cTerm     the term in which a course is taken
     * @see   Term
     */
    public void setStudentTerm(Term cTerm)
    {
        studentTerm = cTerm;
    }

    /**
     * Assigns the grade a student has in a course.  The grade cannot be above
     * a 100.0 or below a 0.0.
     *
     * @param grade     the grade a student has in an instance of Course
     * @throws IllegalArgumentException If a student is assigned a value above 100.0 or
     *                                  if a student is assigned a value below 0.0.
     */
    public void setStudentGrade(double grade)
    {
        // Check for exceptions
        if (grade > 100.0)
            throw new IllegalArgumentException("Grade above 100% not possible");
        
        if (grade < 0.0)
            throw new IllegalArgumentException("Grade below 0% not possible");

        // Assign grades
        if (grade >= 90)
            studentGrade = "A";
        else if (grade >= 80)
            studentGrade = "B";
        else if (grade >= 70)
            studentGrade = "C";
        else if (grade >= 60)
            studentGrade = "D";
        else
            studentGrade = "F";
    }

    /**
     * Assigns the grade a student has in a course.  The grade must be a letter
     * grade, such as (A, B, C, D, or F).
     *
     * @param grade     the grade a student has in an instance of Course
     * @throws IllegalArgumentException If a student is assigned something besides (A, B, C, D, or F).
     */
    public void setStudentGrade(String grade)
    {
        grade = grade.toUpperCase();

        // Check for exceptions
        if (!(grade.equals("A") || grade.equals("B") || grade.equals("C") || grade.equals("D") || grade.equals("F")))
            throw new IllegalArgumentException("Grade must be 'A', 'B', 'C', 'D', or 'F'");

        // Assign grade
        studentGrade = grade;
    }

    /**
     * Assigns boolean regarding whether a student is actively taking the course.
     * There is no validation that can occur in the function.  This value is
     * used to differentiate between past and current courses.
     *
     * @param now       whether a student is actively taking the course
     */
    public void setTakingNow(boolean now)
    {
        takingNow = now;
    }

    /**
     * Returns the student's banner ID.  This can be used to look up
     * a StudentProfile for this course.
     *
     * @return  the banner ID number
     */
    public int getStudentBannerID()
    {
        return studentBannerID;
    }

    /**
     * Returns the course number extension.  Courses often have extensions
     * for their course numbers to differentiate different instances of the
     * course, represented by a mixture of digits and characters.  Examples
     * include '01', '02', 'H2', etc.
     *
     * Often, the full course identifier is presented as the subject, course
     * number and course number extension, ajoined by a period.  For example,
     * BIBL 101.H2.  At ACU, this represnts Bible 101 Jesus: Life and Teachings.
     * The H2 signifies that this is the "Honors 2" class.
     *
     * @return  the course number extension
     */
    public String getStudentSection() {
        return studentSection;
    }

    /**
     * Returns the course year.  Examples include, 2012, 2014, 2016, etc.
     *
     * @return the course year
     */
    public int getStudentYear() {
        return studentYear;
    }

    /**
     * Returns the course Term, represented by an enum.
     *
     * Course terms are limited in number.  Thus, they have been represented as
     * enumerated types to insure strong typing.  Exmamples include FALL,
     * SPRING, JAN_SHORT (January Short), and MAY (Maymester).
     *
     * @return  the course term
     */
    public Term getStudentTerm()
    {
        return studentTerm;
    }

    /**
     * Returns the course grade.  This represents the final grade for the course,
     * or the ongoing grade as an active semester progresses.
     *
     * @return  the course grade
     */
    public String getStudentGrade() {
        return studentGrade;
    }

    /**
     * Returns whether the course is actively being taken by a student.
     *
     * @return  whether the course is actively being taken by a student
     */
    public boolean isTakingNow() {
        return takingNow;
    }
}


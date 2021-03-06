package implementation;

import java.util.Objects;

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
    private int studentBannerID;    // Student Banner ID
    private String studentSection;  // Course number extension (1, 2, H2, etc.)

    // Time characteristics
    private int studentYear;        // Year course is taken
    private Term studentTerm;       // Term during which course is taken
    
    // Student data
    private String studentGrade;    // Student's grade in course (from from F to A)
    private boolean takingNow;      // Whether the student is in the course now

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
     * Constructs a Student object to represent a student's course.  Student
     * delegates the instantiation of instance variables to method setStudent.
     *
     * @param bnrID     banner ID
     * @param section   student section, such as 01, H1, etc
     * @param year      year the student takes the course
     * @param cTerm     term the student takes the course
     * @param grade     student grade in the course as a letter (A, B, etc)
     * @param now       whether the student is presently in the course
     * @see             Student#setStudent(int, String, int, Term, double, boolean)
     */
    public Student(int bnrID, String section, int year, Term cTerm, String grade, boolean now)
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
     * Assigns values to Student instance variables.  The function
     * delegate assignment to each of the assignment functions
     * for each instance variable, many of which validate the input.
     *
     * @param bnrID     banner ID
     * @param section   student section, such as 01, H1, etc
     * @param year      year the student takes the course
     * @param cTerm     term the student takes the course
     * @param grade     student grade in the course as a letter (A, B, etc)
     * @param now       whether the student is presently in the course
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     */
    public void setStudent(int bnrID, String section, int year, Term cTerm, String grade, boolean now)
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
     * grade, such as (A, B, C, D, F, P, S, U, NP, CR, NC, NG, AU, W, WF, I, IP).
     *
     * 'W' signfies 'withdraw', 'WF' signifies 'withdraw' with 'failure', and 'F' just signifies a typical
     * failure.  'W' does not affect the student's GPA, while a 'WF' does.
     *
     * 'IP' is primarily for graduate students and means 'in progress'.  'I' means 'incomplete', and is also
     * more common for graduate students.
     *
     * 'P' is for 'pass', and is exclusive to pass/fail courses.  Similarly, 'NP' represents 'no pass'.
     *
     * 'CR' stands for 'credit', and is for students who sign up for a course on a credit/no credit basis.
     * Similarly, 'NC' stands for 'no credit'.
     *
     * 'AU' represents a 'course audit', and typically results in no course credit.
     *
     * 'NG' stands for 'no grade', and typically represents a course in progress.
     *
     * 'S' stands for 'satisfactory', and 'U' for unsatisfactory.
     *
     * @param grade     the grade a student has in an instance of Course
     * @throws IllegalArgumentException If a student is assigned something besides (A, B, C, D, F, P, S, U, NP, CR, NC, NG, AU W, WF, I, or IP).
     */
    public void setStudentGrade(String grade)
    {
        grade = grade.toUpperCase();

        // Check for exceptions
        if (!(grade.equals("A")    ||
                grade.equals("B")  ||
                grade.equals("C")  ||
                grade.equals("D")  ||
                grade.equals("F")  ||
                grade.equals("P")  ||
                grade.equals("S")  ||
                grade.equals("U")  ||
                grade.equals("NP") ||
                grade.equals("CR") ||
                grade.equals("NC") ||
                grade.equals("NG") ||
                grade.equals("AU") ||
                grade.equals("W")  ||
                grade.equals("WF") ||
                grade.equals("I")  ||
                grade.equals("IP")))
            throw new IllegalArgumentException("Grade must be 'A', 'B', 'C', 'D', 'F', " +
                    "'P', 'S', 'U', 'NP', 'CR', 'NC', 'NG', 'AU', 'W', 'WF', 'I', or 'IP'; Invalid Argument: " + grade);

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
    public String getStudentSection()
    {
        return studentSection;
    }

    /**
     * Returns the course year.  Examples include, 2012, 2014, 2016, etc.
     *
     * @return the course year
     */
    public int getStudentYear()
    {
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
    public String getStudentGrade()
    {
        return studentGrade;
    }

    /**
     * Returns whether the course is actively being taken by a student.
     *
     * @return  whether the course is actively being taken by a student
     */
    public boolean isTakingNow()
    {
        return takingNow;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (other == this)
            return true;

        if (other instanceof Student)
            return equals((Student) other);

        if (other instanceof StudentProfile)
            return equals((StudentProfile) other);

        return false;
    }

    private boolean equals(Student other)
    {
        if (getStudentBannerID() != other.getStudentBannerID())
            return false;

        if (!getStudentSection().equals(other.getStudentSection()))
            return false;

        if (getStudentYear() != other.getStudentYear())
            return false;

        if (!getStudentTerm().equals(other.getStudentTerm()))
            return false;

        return true;
    }

    private boolean equals(StudentProfile other)
    {
        if (getStudentBannerID() != other.getBannerID())
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getStudentBannerID());
    }
}


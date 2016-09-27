package implementation;

/**
 * Represents a single student's profile.  Maintains data for
 * <ul>
 *     <li>Banner ID (a unique identifier)</li>
 *     <li>first name</li>
 *     <li>last name</li>
 *     <li>email address</li>
 * </ul>
 *
 * <p>
 *     Student and StudentProfile objects will be able to identify one another
 *     using a common Banner ID. There will be many Students, but only one
 *     StudentProfile in the system.
 * </p>
 *
 * @author Kevin Shurtz
 * @author Virginia Pettit
 * @version 1.0
 */
public class StudentProfile
{
    // Student Information
    private int bannerID;       // banner ID
    private String firstName;   // first name
    private String lastName;    // last name;
    private String email;       // email address

    /**
     * Constructs a StudentProfile object to represent the canonical
     * set of information about a student.  Assignment is delgated to method
     * setStudentProfile.
     *
     * @param banner    the student profile banner ID
     * @param first     the student profile first name
     * @param last      the student profile last name
     * @param email     the student profile email
     * @see             StudentProfile#setStudentProfile(int, String, String, String)
     */
    public StudentProfile(int banner, String first, String last, String mail)
    {
        setStudentProfile(banner, first, last, email);
    }

    /**
     *
     * @param banner    the student profile banner ID
     * @param first     the student profile first name
     * @param last      the student profile last name
     * @param email     the student profile email
     */
    public void setStudentProfile(int banner, String first, String last, String mail)
    {
        setBannerID(banner);    // set banner ID
        setFirstName(first);    // set first name
        setLastName(last);      // set last name
        setEmail(email);        // set email
    }

    /**
     * Set the student profile banner ID.
     *
     * @param banner    the student profile banner ID
     */
    public void setBannerID(int banner)
    {
        bannerID = banner;
    }

    /**
     * Set the student profile first name.
     *
     * @param first     the student profile first name
     */
    public void setFirstName(String first)
    {
        firstName = first;
    }

    /**
     * Set the student profile last name.
     *
     * @param last      the student profile last name
     */
    public void setLastName(String last)
    {
        lastName = last;
    }

    /**
     * Set the student profile email address.
     *
     * @param mail      the student profile email address
     */
    public void setEmail(String mail)
    {
        email = mail;
    }

    /**
     * Get the student profile Banner ID.
     *
     * @return      the student profile Banner ID
     */
    public int getBannerID()
    {
        return bannerID;
    }

    /**
     * Get the student profile first name.
     *
     * @return      the student profile first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Get the student profile last name.
     *
     * @return      the student profile last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Get the student profile email address.
     *
     * @return      the student profile email address
     */
    public String getEmail()
    {
        return email;
    }
}


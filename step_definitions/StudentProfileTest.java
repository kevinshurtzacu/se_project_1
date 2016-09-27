
package step_definitions;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import implementation.StudentProfile;
import static org.junit.Assert.*;

/**
 * This class tests the StudentProfile object using the test data defined in
 * student_profile.feature.
 *
 * @author Kevin Shurtz
 * @version 1.0
 */
public class StudentProfileTest
{
    // StudentProfile object to be tested
    private static StudentProfile studentProfile = new StudentProfile(0, "John", "Doe", "jxd15a@acu.edu");

    // reuslt of test
    private static Object result = new Object();

    // Givens
    @Given("^the student profile has (?:the)? (.*?)(?: name| id)? ((?:\\w|\\d)+(?:@\\w+|\\d+)?(?:\\.\\w+)?)$")
    public void theStudentProfileHas(String attribute, String value) throws Throwable {
        attribute = attribute.toLowerCase();

        if (attribute.equals("last"))
            studentProfile.setLastName(value);

        if (attribute.equals("first"))
            studentProfile.setFirstName(value);

        if (attribute.equals("email"))
            studentProfile.setEmail(value);

        if (attribute.equals("banner"))
            studentProfile.setBannerID(Integer.parseInt(value));
    }

    // Whens
    @When("^I ask for the student profile (.*?)(?: name| id)?$")
    public void iAskForTheStudentProfile(String attribute) throws Throwable
    {
        attribute = attribute.toLowerCase();

        if (attribute.equals("last"))
            result = studentProfile.getLastName();

        if (attribute.equals("first"))
            result = studentProfile.getFirstName();

        if (attribute.equals("email"))
            result = studentProfile.getEmail();

        if (attribute.equals("banner"))
            result = studentProfile.getBannerID();
    }

    // Thens
    @Then("^I receive the string (.*?) from(?:\\sthe)? student profile$")
    public void iReceiveTheString(String request) throws Throwable
    {
        assertEquals(request, (String)result);
    }

    @Then("^I receive the integer (\\d+) from(?:\\sthe)? student profile$")
    public void iReceiveTheInteger(Integer request) throws Throwable
    {
        assertEquals(request, (Integer)result);
    }
}

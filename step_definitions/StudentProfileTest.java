
package step_definitions;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import implementation.StudentProfile;
import static org.junit.Assert.*;

/**
 * This class tests the StudentProfile object using the test data defined in
 * student_profile.feature.
 */
public class StudentProfileTest {
    private static StudentProfile studentProfile = new StudentProfile()
    private static Object result = new Result();

    // Givens
    @Given("^the student profile has (.*?) (?:name)? (.*?)$")
    public void theStudentProfileHasLastNameRamirez(String attribute, String value) throws Throwable
    {
        if (attribute.equals("last"))

    }

    // Whens
    @When("^I ask for the student profile first name$")
    public void iAskForTheStudentProfileFirstName() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    // Thens
    @Then("^I receive the string ebw(\\d+)a@acu\\.edu from student profile$")
    public void iReceiveTheStringEbwAAcuEduFromStudentProfile(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}

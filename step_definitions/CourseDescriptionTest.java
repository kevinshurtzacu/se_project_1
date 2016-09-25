package step_definitions;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import implementation.CourseDescription;
import static org.junit.Assert.*;

/**
 * These defintions will be used by the cucumber scripts at the root of the project directory
 * to test CourseDescription features, referencing the course_description.feature in the
 * features directory.
 *
 * @author  Kevin Shurtz
 * @version 1.0
 */
public class CourseDescriptionTest
{
    // CourseDescription object to be used for tests
    private static CourseDescription coursedesc = new CourseDescription(0000, "CS", 101, "Software Engineering");

    // Object to store result of tests
    private static Object result = null;

    // Givens
    @Given("^the course description has the \"(\\w+)\" (.*?)$")
    public void theCourseDescriptionHasThe(String attribute, String value) throws Throwable
    {
        attribute = attribute.toLowerCase();

        if (attribute.equals("crn"))
            coursedesc.setCourseCRN(Integer.parseInt(value));

        if (attribute.equals("subject"))
            coursedesc.setCourseSubject(value);

        if (attribute.equals("number"))
            coursedesc.setCourseNumber(Integer.parseInt(value));

        if (attribute.equals("title"))
            coursedesc.setCourseTitle(value);
    }

    // Whens
    @When("^I ask for the course description(?:'s)? \"(.*?)\"$")
    public void iAskForTheCourseDescription(String attribute) throws Throwable
    {
        attribute = attribute.toLowerCase();

        if (attribute.equals("crn"))
            result = coursedesc.getCourseCRN();

        if (attribute.equals("subject"))
            result = coursedesc.getCourseSubject();

        if (attribute.equals("number"))
            result = coursedesc.getCourseNumber();

        if (attribute.equals("title"))
            result = coursedesc.getCourseTitle();
    }

    // Thens
    @Then("^I receive the string (.*?) from(?:the\\s)? course description$")
    public void iReceiveTheString(String expected) throws Throwable
    {
        assertEquals(expected, (String)result);
    }

    @Then("^I receive the integer (\\d+) from(?:the\\s)? course description$")
    public void iReceiveTheInteger(Integer expected) throws Throwable
    {
        assertEquals(expected, (Integer)result);
    }
}
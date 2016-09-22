/**
 * These defintions will be used by the cucumber_progress.sh scripts at the root of the project
 * directory to test Course features, referencing the course.feature in the
 * features directory.
 *
 * @author  Kevin Shurtz
 * @version 1.0
 */
package step_definitions;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import implementation.Transcript;
import static org.junit.Assert.*;

public class TranscriptTest {
    // This Transcript object will be used for all test cases
    private Transcript tran = new Transcript();
    
    /*
     *  This function adds a class to a student's transcript.
     *  Each class contains a grade, course name, and number of hours.
     */ 
    @Given("^I get an? \"?([ABCDF])\"? in \"?(.*?)\"?, an? (\\d+) hour course$")
    public void iGetAInAHourCourse(String grade, String course, int hours) throws Throwable {
        tran.takeClass(course, grade, hours);
    }
       
    /*
     *  This function validates a GPA against the expected outcome.
     *
     *  The GPA calculated by the object is returned by the Transcript's 
     *  gpa() function.  The expected GPA is gathered in the feature files
     *  and is passed as the double value expectedGPA.
     */
    @Then("^my GPA is (\\d+.\\d+)$")
    public void myGPAIs(double expectedGPA) throws Throwable {
        double gpa = tran.gpa();
        assertEquals(gpa, expectedGPA, .001);
    }
    
    /*
     *  This function insures that the Transript tracks a student's academic
     *  standing correctly.
     *
     *  Transcript's standing() function returns a string that reads:
     *  * "good"
     *  * "probation"
     *  * "suspended"
     *  
     *  depending on the students current GPA.  Good standing is a GPA above a
     *  2.0.  Probation occurs when the GPA is between 1.0 and 2.0.  Poor 
     *  standing is below a 1.0.  View the README for further information.
     */
    @Then("^I am in (good|probation|suspended) academic standing$")
    public void iAmInAcademicStanding(String standing) throws Throwable {
        assertEquals(standing, tran.standing());
        System.out.println("Standing: " + tran.standing());
    }
}


Feature: Student instance maintains data about itself
  Student is an object to represent one a person who has the ability to take classes.  It contains information
  regarding their name, email, and banner ID.

  Scenario Outline: System asks for the student last name
    Given the student has last name <lastname>
    And the student has the first name <firstname>
    And the student has the email <email>
    And the student has the banner id <bannerid>
    When I ask for the student last name
    Then I receive <result>

    Examples:
      | lastname  | firstname | email           | bannerid  | result    |
      | Ramirez   | Isaak     | imr13a@acu.edu  | 000486750 | Ramirez   |
      | Shurtz    | Kevin     | kms14d@acu.edu  | 000496884 | Shurtz    |
      | Pettit    | Virginia  | vxp12c@acu.edu  | 000499799 | Pettit    |
      | Heidecker | Tim       | teh13d@acu.edu  | 000777777 | Heidecker |
      | Wareheim  | Eric      | ebw14a@acu.edu  | 000888888 | Wareheim  |
	  
  Scenario Outline: System asks for the student first name
    Given the student has last name <lastname>
    And the student has the first name <firstname>
    And the student has the email <email>
    And the student has the banner id <bannerid>
    When I ask for the student first name
    Then I receive <result>

    Examples:
      | lastname  | firstname | email           | bannerid  | result   |
      | Ramirez   | Isaak     | imr13a@acu.edu  | 000486750 | Isaak    |
      | Shurtz    | Kevin     | kms14d@acu.edu  | 000496884 | Kevin    |
      | Pettit    | Virginia  | vxp12c@acu.edu  | 000499799 | Virginia |
      | Heidecker | Tim       | teh13d@acu.edu  | 000777777 | Tim      |
      | Wareheim  | Eric      | ebw14a@acu.edu  | 000888888 | Eric     |
	  
  Scenario Outline: System asks for the student email
    Given the student has last name <lastname>
    And the student has the first name <firstname>
    And the student has the email <email>
    And the student has the banner id <bannerid>
    When I ask for the student email
    Then I receive <result>

    Examples:
      | lastname  | firstname | email           | bannerid  | result         |
      | Ramirez   | Isaak     | imr13a@acu.edu  | 000486750 | imr13a@acu.edu |
      | Shurtz    | Kevin     | kms14d@acu.edu  | 000496884 | kms14d@acu.edu |
      | Pettit    | Virginia  | vxp12c@acu.edu  | 000499799 | vxp12c@acu.edu |
      | Heidecker | Tim       | teh13d@acu.edu  | 000777777 | teh13d@acu.edu |
      | Wareheim  | Eric      | ebw14a@acu.edu  | 000888888 | ebw14a@acu.edu |
	  
  Scenario Outline: System asks for the student banner id
    Given the student has last name <lastname>
    And the student has the first name <firstname>
    And the student has the email <email>
    And the student has the banner id <bannerid>
    When I ask for the student banner id
    Then I receive <result>

    Examples:
      | lastname  | firstname | email           | bannerid  | result    |
      | Ramirez   | Isaak     | imr13a@acu.edu  | 000486750 | 000486750 |
      | Shurtz    | Kevin     | kms14d@acu.edu  | 000496884 | 000496884 |
      | Pettit    | Virginia  | vxp12c@acu.edu  | 000499799 | 000499799 |
      | Heidecker | Tim       | teh13d@acu.edu  | 000777777 | 000777777 |
      | Wareheim  | Eric      | ebw14a@acu.edu  | 000888888 | 000888888 |
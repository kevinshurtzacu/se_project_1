Feature: Course instance maintains data about itself
  Course is an object to represent one of a student's classes.  It contains information
  regarding the class, when it is offered, and his/her performace as he/she has taken it.

  Course's can refer to classes a student has actively taken, as well as classes he/she
  has taken in the past.

  Scenario Outline: System asks for the course subject
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask for the course subject
    Then I receive the string <result>

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result |
      | IT      | Networking                  | 221    |           | 2016 | Spring   | 92    | is not | IT     |
      | IT      | Intro to Databases and DBMS | 220    |           | 2016 | Fall     | 70    | is     | IT     |
      | CS      | Programming I               | 120    |           | 2015 | Fall     | 90    | is not | CS     |
      | CS      | Programming II              | 130    |           | 2016 | Spring   | 88    | is not | CS     |
      | CS      | Computer Organization       | 220    |           | 2016 | Spring   | 91    | is not | CS     |

  Scenario Outline: System asks for the course title
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask for the course title
    Then I receive the string <result>

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result                      |
      | IT      | Networking                  | 221    |           | 2016 | Spring   | 92    | is not | Networking                  |
      | IT      | Intro to Databases and DBMS | 220    |           | 2016 | Fall     | 70    | is     | Intro to Databases and DBMS |
      | CS      | Programming I               | 120    |           | 2015 | Fall     | 90    | is not | Programming I               |
      | CS      | Programming II              | 130    |           | 2016 | Spring   | 88    | is not | Programming II              |
      | CS      | Computer Organization       | 220    |           | 2016 | Spring   | 91    | is not | Computer Organization       |

  Scenario Outline: System asks for the course number
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask for the course number
    Then I receive the number <result>

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result |
      | IT      | Intro to Databases and DBMS | 220    |           | 2016 | Fall     | 70    | is     | 220    |
      | CS      | Programming I               | 120    |           | 2015 | Fall     | 90    | is not | 120    |
      | CS      | Programming II              | 130    |           | 2016 | Spring   | 88    | is not | 130    |
      | CS      | Computer Organization       | 220    |           | 2016 | Spring   | 91    | is not | 220    |

  Scenario Outline: System asks for the course number extension
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask for the course extension
    Then I receive the string <result>

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result |
      | IT      | Networking                  | 221    | 01        | 2016 | Spring   | 92    | is not | 01     |
      | IT      | Intro to Databases and DBMS | 220    | 02        | 2016 | Fall     | 70    | is     | 02     |
      | CS      | Programming I               | 120    | H1        | 2015 | Fall     | 90    | is not | H1    |
      | CS      | Programming II              | 130    | 05        | 2016 | Spring   | 88    | is not | 05     |
      | CS      | Computer Organization       | 220    | H2        | 2016 | Spring   | 91    | is not | H2     |

  Scenario Outline: System asks for the year in which the course was offered
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask for the course year
    Then I receive the number <result>

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result |
      | IT      | Networking                  | 221    |           | 2016 | Spring   | 92    | is not | 2016   |
      | IT      | Intro to Databases and DBMS | 220    |           | 2013 | Fall     | 70    | is     | 2013   |
      | CS      | Programming I               | 120    |           | 2012 | Fall     | 90    | is not | 2012   |
      | CS      | Programming II              | 130    |           | 2014 | Spring   | 88    | is not | 2014   |
      | CS      | Computer Organization       | 220    |           | 2015 | Spring   | 91    | is not | 2015   |

  Scenario Outline: System asks for the term in which the course was offered
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask for the course term
    Then I receive the string <result>

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result |
      | IT      | Networking                  | 221    |           | 2016 | Spring   | 92    | is not | Spring |
      | IT      | Intro to Databases and DBMS | 220    |           | 2016 | Fall     | 70    | is     | Fall   |
      | CS      | Programming I               | 120    |           | 2015 | Fall     | 90    | is not | Fall   |
      | CS      | Programming II              | 130    |           | 2016 | Spring   | 88    | is not | Spring |
      | CS      | Computer Organization       | 220    |           | 2016 | Spring   | 91    | is not | Spring |

  Scenario Outline: System asks for the student's grade in the course
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask for the course grade
    Then I receive the double <result>

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result |
      | IT      | Networking                  | 221    |           | 2016 | Spring   | 92    | is not | 92     |
      | IT      | Intro to Databases and DBMS | 220    |           | 2016 | Fall     | 70    | is     | 70     |
      | CS      | Programming I               | 120    |           | 2015 | Fall     | 90    | is not | 90     |
      | CS      | Programming II              | 130    |           | 2016 | Spring   | 88    | is not | 88     |
      | CS      | Computer Organization       | 220    |           | 2016 | Spring   | 91    | is not | 91     |

  Scenario Outline: System asks if student is active in course
    Given the course has the subject <subject>
    And the course has the title <title>
    And the course has the number <number>
    And the course has the number extension <extension>
    And the course is in the year <year>
    And the course is in the <term> term
    And the student has a grade of <grade>
    And the student <active> actively taking the course
    When I ask if the student is active in the course
    Then I am told that he <result> active

    Examples:
      | subject | title                       | number | extension | year | term     | grade | active | result |
      | IT      | Networking                  | 221    |           | 2016 | Spring   | 92    | is not | is not |
      | IT      | Intro to Databases and DBMS | 220    |           | 2016 | Fall     | 70    | is     | is     |
      | CS      | Programming I               | 120    |           | 2016 | Fall     | 90    | is     | is     |
      | CS      | Programming II              | 130    |           | 2016 | Spring   | 88    | is not | is not |
      | CS      | Computer Organization       | 220    |           | 2016 | Spring   | 91    | is not | is not |

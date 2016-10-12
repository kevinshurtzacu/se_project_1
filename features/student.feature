Feature: Student instance maintains data about itself
  Student is an object to represent one of a a student associated with a course description.
  It contains information particular a particular student in a course description, such as
  his/her grade, and the time that he/she takes it.

  Student can refer to a present student, or a past student.

  Scenario Outline: System asks for the student's course section
    Given the student's course has the section <section>
      And the student is in the year <year>
      And the student is in the <term> term
      And the student grade is <grade>
      And the student <active> taking the course
    When I ask for the student's course section
    Then I receive the string <result> from student

    Examples:
      | section | year | term     | grade | active | result |
      | 01      | 2016 | Spring   | 92    | is not | 01     |
      | 02      | 2016 | Fall     | 70    | is     | 02     |
      | H1      | 2015 | Fall     | 90    | is not | H1     |
      | 05      | 2016 | Spring   | 88    | is not | 05     |
      | H2      | 2016 | Spring   | 91    | is not | H2     |

  Scenario Outline: System asks for the year in which the student took the course
    Given the student's course has the section <section>
      And the student is in the year <year>
      And the student is in the <term> term
      And the student grade is <grade>
      And the student <active> taking the course
    When I ask for the student year
    Then I receive the integer <result> from student

    Examples:
      | section | year | term     | grade | active | result |
      |         | 2016 | Spring   | 92    | is not | 2016   |
      |         | 2013 | Fall     | 70    | is     | 2013   |
      |         | 2012 | Fall     | 90    | is not | 2012   |
      |         | 2014 | Spring   | 88    | is not | 2014   |
      |         | 2015 | Spring   | 91    | is not | 2015   |

  Scenario Outline: System asks for the term in which the student took the course
    Given the student's course has the section <section>
      And the student is in the year <year>
      And the student is in the <term> term
      And the student grade is <grade>
      And the student <active> taking the course
    When I ask for the student term
    Then I receive the term <result> from student

    Examples:
      | section | year | term     | grade | active | result |
      |         | 2016 | Spring   | 92    | is not | Spring |
      |         | 2016 | Fall     | 70    | is     | Fall   |
      |         | 2015 | Fall     | 90    | is not | Fall   |
      |         | 2016 | Spring   | 88    | is not | Spring |
      |         | 2016 | Spring   | 91    | is not | Spring |

  Scenario Outline: System asks for the student's grade in the course
    Given the student's course has the section <section>
      And the student is in the year <year>
      And the student is in the <term> term
      And the student grade is <grade>
      And the student <active> taking the course
    When I ask for the student grade
    Then I receive the string <result> from student

    Examples:
      | section | year | term     | grade | active | result |
      |         | 2016 | Spring   | A     | is not | A      |
      |         | 2016 | Fall     | C     | is     | C      |
      |         | 2015 | Fall     | 90    | is not | A      |
      |         | 2016 | Spring   | 88    | is not | B      |
      |         | 2016 | Spring   | F     | is not | F      |

  Scenario Outline: System asks if student is active in course
    Given the student's course has the section <section>
      And the student is in the year <year>
      And the student is in the <term> term
      And the student grade is <grade>
      And the student <active> taking the course
    When I ask if the student is taking the course
    Then I am told that the student <result> taking the course

    Examples:
      | section | year | term     | grade | active | result |
      |         | 2016 | Spring   | 92    | is not | is not |
      |         | 2016 | Fall     | 70    | is     | is     |
      |         | 2016 | Fall     | 90    | is     | is     |
      |         | 2016 | Spring   | 88    | is not | is not |
      |         | 2016 | Spring   | 91    | is not | is not |

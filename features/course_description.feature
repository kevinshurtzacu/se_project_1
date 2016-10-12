Feature: Course description
  CourseDescription will be an object to represent a single course
  offered by the university.  This defers from Course, which contains
  data about a single student's and will will possessed by the StudentProfile
  object.
  Scenario Outline: The system asks for the course description subject
    Given the course description has the "subject" <subject>
      And the course description has the "number" <number>
      And the course description has the "title" <title>
    When I ask for the course description "subject"
    Then I receive the string <result> from course description

    Examples:
      | subject | number | title                       | result |
      | IT      | 221    | Networking                  | IT     |
      | IT      | 220    | Intro to Databases and DBMS | IT     |
      | CS      | 120    | Programming I               | CS     |
      | CS      | 130    | Programming II              | CS     |
      | BIBL    | 101    | Jesus Life and Teachings    | BIBL   |

  Scenario Outline: The system asks for the course description number
    Given the course description has the "subject" <subject>
      And the course description has the "number" <number>
      And the course description has the "title" <title>
    When I ask for the course description "number"
    Then I receive the string <result> from course description

    Examples:
      | subject | number | title                        | result |
      | IT      | 221    | Networking                   | 221    |
      | IT      | 220    | Intro to Databases and DBMS  | 220    |
      | CS      | 120    | Programming I                | 120    |
      | CS      | 130    | Programming II               | 130    |
      | BIBL    | 101    | Jesus Life and Teachings     | 101    |

  Scenario Outline: The system asks for the course description title
    Given the course description has the "subject" <subject>
      And the course description has the "number" <number>
      And the course description has the "title" <title>
    When I ask for the course description "title"
    Then I receive the string <result> from course description

    Examples:
      | subject | number | title                        | result                       |
      | IT      | 221    | Networking                   | Networking                   |
      | IT      | 220    | Intro to Databases and DBMS  | Intro to Databases and DBMS  |
      | CS      | 120    | Programming I                | Programming I                |
      | CS      | 130    | Programming II               | Programming II               |
      | BIBL    | 101    | Jesus: Life and Teachings    | Jesus: Life and Teachings    |

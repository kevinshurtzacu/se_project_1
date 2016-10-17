# Software Engineering Project 1
Our system validates Students' course history against the prerequisites 
for courses they are currently in.  

## Starting the System
To start the system, enter `java Run`.  Upon entering the command,
you should see the relevant student and course data being loaded into
the system, followed by the prerequisite data for various departments.
Accounting, Computer Science, Information Systems, and Information 
Technology courses all have prerequisite data at the current time.  

### Help
If the syntax or list of commands in unclear, after starting the system,
type `HELP` to see more information.  

### Case Sensitivity
The system was designed to be case insensitive.  Any keywords entered
are treated as uppercase characters, irrespective of teh actual case of 
the user's input.  Therefore, `HELP` is read the same as `help`.  

## Setting the Year and Term
The Date and Term settings default to the Fall of 2015, but can be 
arbitrarily changed using the `SET` command.  

`SET [YEAR|TERM] [argument]`

Term can accept the values `FALL`, `SPRING`, and `SUMMER`.  Year can 
accept any integer value, though course and student information will
only be found for years and terms described in the cs374_anon.csv file.  


## Updating Data
### Course and Student Data
In order to replace the course and student data, replace the file:
cs374_anon.csv with the preferred CSV, with the same name.  

### Prerequisite Data
Prerequisite data is stored in prereq_catalog.csv.  The format for each
record is:
`"[course subject],[course number],[course title]",["[prereq subject],[prereq number],[prereq title],[prereq grade]" ...]`

For example:
`"ACCT,211,Managerial Accounting","ACCT,210,Financial Accounting,C"`

The course to which we are applying prerequisites is "ACCT 211: 
Managerial Accounting".  The subject, number, and title must be wrapped
in parentheses - this constitutes the identifier for a course.
Then, any prerequisite courses must be added in similar fashion.  

In quotes, each prerequisite must include a subject, number, title, and 
letter grade.  The grade represents the minimum grade necessary to meet 
the prerequisite.  While there are many possible letter grades, the most 
common are 'A', 'B', 'C', 'D', and 'F'.  Other grade identifiers used 
by the school are also accepted however.  

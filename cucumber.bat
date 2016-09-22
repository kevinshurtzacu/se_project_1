
REM Excerpted from "The Cucumber for Java Book",
REM published by The Pragmatic Bookshelf.
REM Copyrights apply to this code. It may not be used to create training material, 
REM courses, books, articles, and the like. Contact us if you are in doubt.
REM We make no guarantees that this code is fit for any purpose. 
REM Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.

javac -cp "jars/*;." step_definitions/CheckoutSteps.java implementation/Checkout.java 
java -cp "jars/*;." cucumber.api.cli.Main -f progress --snippets camelcase -g step_definitions features
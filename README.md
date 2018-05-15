This project is for the Buildit Platform Engineer Exercise.  The full 
text of the exercise can be found at the end of this document.

//**** Build Instructions ****************

This project is written in java, built with Maven, and developed in Eclipse Oxygen.

To build requires Maven and Java 1.8.  I built it with Maven 3.5.3.
1) [Assumed] Checkout the sitemapgenerator project from github
2) From a shell and the sitemapgenerator folder, execute "mvn compile"
>mvn compile
or
>mvn clean compile


//***** Test Instructions **************
1) From a shell run the maven install command (needed for Lg4j config files)
>mvn clean install

The Default profile is "Local" and this runs only tests that don't depend on the internet.
The alternative profile for testing with the dataset of live websites is "Internet"
Also available is the profile "Full" for both Local file based test and Internet based tests.
To run either alternate test configurations AFTER a maven install has completed use:
>mvn test -P Internet
or
>mvn test -P Full

//***** Packaging Instructions ***********
1) From a shell run the maven package command
>mvn package

//***** Running Instructions *************
1) From a shell, navigate to the target director
cd target


TODO Verify dependency installation and add packaging or running instructions and a start script
//****************************************

Design and planning artifacts are located in sitemapgenerator/design


Requirements:
1) A README.md
2) Build Instructions
3) Test Instructions
4) Run instructions
5) Trade Offs explanation and reasoning
6) Possible points of enhancement given more time
7) A webcrawler that indexes every link in a domain
8) A sitemap listing the following link details from the base domain
 8a) Links to pages in the same domain
 8b) Links to pages outside the domain
 8c) Links to static content 
9) Basic view of the output of the data from 8

//************** Challenges/Tradeoffs *****************
This project presents many opportunities to go beyond the minimum but that creates quite a quandary.
From a software project management perspective I believe it is important in many cases to avoid the 
distraction and resource utilization developing features or enhancements that go beyond the requirements,
 or could potentially be wasted by a change in customer direction.  However, going beyond in some cases
can have positive impacts on customer satisfaction or award capture.  And in the case of this project, 
hopefully would demonstrate a greater value to the company in this potential future employee (me).  

Normally those decisions are better made as part of a collaborative team with open communication laterally 
and vertically to key stake holders. Multi-faceted teams with designated opportunities for prototyping and 
customer interaction would definitely make "going beyond" decisions not such a quandary.

In my experience as a software lead for a large corporation excessive distraction is a bigger obstacle to
productivity than going beyond is a positive.  It requires experience and maturity.  Hopefully
my project is evaluated knowing why I chose optimizing efficiency and productivity.


Tradeoffs:
  Initially I planned the architecture for my model around a tree so that the output view could be more easily 
  structured graphically as a tree, or with more complex graphics objects instead of just a text list.  Instead 
  I opted for a flat list and a simple display based on the assumption that a higher priority focus was on process
  and tools rather than improved end user graphics.
  
  Using JUnit 4 instead of 5.  I opted for JUnit 4 based on familiarity and less chance for conflicts with the Maven
   
  
  Combining common tasks in the test classes.  In initial testing it became apparent that I should be testing both
  live web data as well as local static test data.  Since I had decided to depend on jsoup for HTML parsing, my 
  Scraper class had to be refactored so as to handle loading HTML from a file or a connection.  Initially it
  was easier to use my original test classes to verify the functionality of the Scrapers, but the method duplication
  started to get out of hand so a cycle of rework was needed to consolidate the tests as well (their only fundamental 
  differences were for reporting results relative to a file or url source).
   
  What to do about treating sub-domains as different or the same domain?  For example, money.cnn.com is a
  sub-domain of cnn.com.  Should these two domains be considered separate or equal when evaluating links
  to be "internal" or "external?"
  
  Creating configuration files for test cases to use multiple source html files to verify results creates a 
  dilemma for the structure of my test classes.  My preference is to use multiple granular tests for each method
  in the Scraper classes but by wrapping them into one parent test makes it easier for me to pull test conditions
  directly from a file, and greatly increase the test data set.  The negative is now, if a method test fails, then
  other method tests are not completed.
  
  Test suite structure:  I have worked to make the testing more comprehensive at the expense of speed.  Given 
  more time and real goals in a production environment, I would have separated these by scope so that developmental
  tests would not rely on file operations, data sets, and even web queries to keep speed up.  For this project I
  was more interested in providing more then just bare bones testing so the suite has been structured this way.  
  Going beyond, I would like to add other scopes to handle different testing scenarios. --NOTE-- I just added 
  Configuration tags and Profiles to enable dual mode testing.
  
  The WebScrapper should be re-factored to cache the link collections created from each request for internal/external/images.
  
  The Maven plugins should be further configured to properly package the deliverable.  Removing snapshot versions, packaging 
  any dependencies, copying start scripts, managing an installer, etc.
  
  The gui should be updated with a multi threaded architecture for generating the sitemaps, displaying scan status in the GUI, and
  displaying the final output rather than the current hijacking of the event thread for the entire duration of the website scan.
  
  The GUI could use refinement and polish to match the layout of the design.
  
  The GUI could use the "Save Sitemap" button from the original design to preserve the output for later analysis.
  
  The Listener on the URL Text Field should run URL validation as the user enters data so the box border could be
    colored to show status in advance.
  
  
  


//****** Original Project Description Document ****************************
What we are looking for
There are no tricks or hidden agendas. The purpose of this exercise is for you to demonstrate your technical knowledge, reasoning, and engineering practices using current software development technologies and methods. Please make sure your code is clear and demonstrates your best practices. The exercise should be done as if you were building software to hand off to someone else.  Refrain from using this as an opportunity to learn a new framework, library or paradigm besides what you feel would be essential to completing this task.

Your solution will form the basis for discussion in subsequent interviews.

What you need to do
Please write a simple web crawler in a language of your choice.  (Please be aware that we favor Apple hardware, so Microsoft based solutions require the use of containers or virtual machines).

The crawler should be limited to one domain. Given a starting URL – say http://wiprodigital.com - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered) showing links to other pages under the same domain, links to external URLs, and links to static content such as images for each respective page.

Please provide a README.md file that explains how to build, test, and run your solution. Also, detail anything further that you would like to achieve with more time.

Once done, please make your solution available on Github and forward the link. Where possible please include your commit history to provide visibility of your thinking and working practice.

What you need to share with us
A working crawler as per requirements above
A README.md explaining:
How to build and run your solution (including any required installations)
Reasoning and describe any trade offs
Explanation of what could be done with more time
Project builds / runs / tests as per instruction
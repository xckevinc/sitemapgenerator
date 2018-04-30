This project is for the Buildit Platform Engineer Exercise.  The full 
text of the exercise can be found at the end of this document.

//**** Build Instructions ****************

This project is written in java, built with Maven, and developed in Eclipse Oxygen.
To build requires Maven and Java.
1) From a shell and the sitemapgenerator folder, execute "mvn install"
TODO Verify dependency installation and add packaging or running instructions and a start script
//****************************************

Design and planning atrifacts are located in sitemapgenerator/design

TODO Create algorithm and architecture
TODO Develop Tests and Code

Requirements:
1) A README.md
2) Build Instructions
3) Test Instructions
4) Run instructions
5) Trade Offs explaination and reasoning
6) Possible points of enhancement given more time
7) A webcrawler that indexes every link in a domain
8) A sitemap listing the following link details from the base domain
 8a) Links to pages in the same domain
 8b) Links to pages outside the domain
 8c) Links to static content 
9) Basic view of the output of the data from 8

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
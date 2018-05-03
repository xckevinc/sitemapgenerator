package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

/**
 * A set of tests for retrieving and parsing HTML from a file.  These tests will define the structure
 * of the FileScraper class {@link WebScraper}
 * 
 * @author Kevin Carter
 *
 */
public class TestFileScraper extends TestAbstractScraper {

	private String testConfigFile = "/org/carter/sitemapgenerator/controller/testConfig.txt";

	public TestFileScraper ()
	{
		super ("TestFileScraper");
	}

	public void testAllFileScraperMethods ( String fileName, String baseUri, int totalLinkCount, 
			int totalInternalLinkCount, int totalExternalLinkCount,
			int totalImageCount ) 
					throws Exception{
		File file = new File(getClass().getResource(fileName).toURI());
		Scraper fileScraper =  new FileScraper(file, baseUri);
		testParseHtml(fileScraper);
		testRetrieveLinks(fileScraper, totalLinkCount);
		testRetrieveInternalLinks(fileScraper, totalInternalLinkCount);
		testRetrieveExternalLinks(fileScraper, totalExternalLinkCount);
	}

	/**
	 * This test uses the file testConfig.txt to specify which data files should be parsed and what their
	 * expected results are.  The file format is filename, base uri, number of links, number of internal links
	 * number of external links, and number of images.
	 * 
	 * @throws Exception if any fields in the testConfig file are not properly formatted or if any test fails
	 * 
	 */
	@Test
	public void testFileScraper () 
			throws Exception
	{
		try (Scanner scanner = new Scanner(new File(getClass().getResource(testConfigFile).toURI()));) {
			Scanner lineScanner;
			while ( scanner.hasNext())
			{
				String configString = scanner.nextLine();
				// Drop out of the loop if the line begins with a comment delimiter
				if ( configString.startsWith("#") )
				{
					continue;
				}
				lineScanner = new Scanner ( configString );
				String fileName = lineScanner.next();
				String baseUri = lineScanner.next();
				int totalLinkCount = lineScanner.nextInt();
				int totalInternalLinkCount = lineScanner.nextInt();
				int totalExternalLinkCount = lineScanner.nextInt();
				int totalImageCount = lineScanner.nextInt();

				testAllFileScraperMethods(fileName, baseUri, totalLinkCount, totalInternalLinkCount, totalExternalLinkCount, totalImageCount);
			}
		}
	}

}

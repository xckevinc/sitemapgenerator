package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.util.List;

import org.junit.Test;

/**
 * A set of tests for retrieving and parsing HTML from a file.  These tests will define the structure
 * of the FileScraper class {@link WebScraper}
 * 
 * @author Kevin Carter
 *
 */
public class TestFileScraper extends TestAbstractScraper {

	/**
	 * This text file declares the parameters for the test run.  It is a list containing link counts
	 * and file names for the test data
	 */
	private String testConfigFile = "/org/carter/sitemapgenerator/controller/testFileConfig.txt";

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
		testAllScraperMethods ( fileScraper,  totalLinkCount, totalInternalLinkCount,  totalExternalLinkCount,
				 totalImageCount );
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
			throws Exception{

		List<Object> testConfigParams = retrieveConfiguration(testConfigFile);
		testConfigParams.forEach( configParams -> 
		{
			try { // A try catch in a jUnit test is ugly but is required in this case due to the forEach + lambda
				LOGGER.trace ("Testing file with configuration parameters: " + 
						"fileName:" + ((List<Object>) configParams).get(4) + 
						" baseUri:" + ((List<Object>) configParams).get(5) +
						" totalLinks:" + ((List<Object>) configParams).get(0) +
						" Internal Links:" + ((List<Object>) configParams).get(1) + 
						" External Links:" + ((List<Object>) configParams).get(2) + 
						" Images:" + ((List<Object>) configParams).get(3));
				testAllFileScraperMethods((String)((List<Object>) configParams).get(4), (String)((List<Object>) configParams).get(5), (Integer)((List<Object>) configParams).get(0), (Integer)((List<Object>) configParams).get(1), (Integer)((List<Object>) configParams).get(2), (Integer)((List<Object>) configParams).get(3));
			} catch (Exception e) {
				LOGGER.error ("Failed parsing the " + testConfigFile + " file for test execution", e);
			}
		});
	}

}

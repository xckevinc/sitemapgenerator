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

		List<Object> configParams = retrieveConfiguration(testConfigFile);
		testAllFileScraperMethods((String)configParams.get(4), (String)configParams.get(5), (Integer)configParams.get(0), (Integer)configParams.get(1), (Integer)configParams.get(2), (Integer)configParams.get(3));
	}

}

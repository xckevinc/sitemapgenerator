package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * A set of tests for retrieving and parsing HTML from a URL.  These tests will define the structure
 * of the WebScraper class {@link WebScraper}
 * 
 * @author Kevin Carter
 *
 */
public class TestWebScraper extends TestAbstractScraper {

	/**
	 * This text file declares the parameters for the test run.  It is a list containing link counts
	 * and URL names for the test data
	 */
	private String testConfigFile = "/org/carter/sitemapgenerator/controller/testUrlConfig.txt";

	/**
	 * Default URL timeout time for connecting to websites.  My chosen sample site
	 * of ozreport.com is often VERY slow
	 */
	private static final int DEFAULT_URL_TIMEOUT = 25000;


	public void testAllUrlScraperMethods ( String urlName, int totalLinkCount, 
			int totalInternalLinkCount, int totalExternalLinkCount,
			int totalImageCount ) 
					throws Exception{
		Scraper webscraper =  new WebScraper(urlName).withTimeout(DEFAULT_URL_TIMEOUT);
		Optional<Elements> links = webscraper.retrieveExternalLinks();
		testAllScraperMethods ( webscraper,  totalLinkCount, totalInternalLinkCount,  totalExternalLinkCount,
				 totalImageCount );
	}

	/**
	 * This test uses the file testUrlConfig.txt to specify which data files should be parsed and what their
	 * expected results are.  The file format is number of links, number of internal links
	 * number of external links, number of images, and URL.
	 * 
	 * @throws Exception if any fields in the testConfig file are not properly formatted or if any test fails
	 * 
	 */
	@Test
	public void testWebScraper () 
			throws Exception{

		List<Object> testConfigParams = retrieveConfiguration(testConfigFile);
		testConfigParams.forEach( configParams -> {
			
		try { // A try catch in a jUnit test is ugly but is required in this case due to the forEach + lambda
			LOGGER.trace ( "Testing url with configuration parameters: " + 
					"url:" + ((List<Object>) configParams).get(4) + 
					" totalLinks:" + ((List<Object>)configParams).get(0) +
					" Internal Links:" + ((List<Object>)configParams).get(1) + 
					" External Links:" + ((List<Object>) configParams).get(2) + 
					" Images:" + ((List<Object>)configParams).get(3));
			testAllUrlScraperMethods((String)((List<Object>)configParams).get(4), 
									(Integer)((List<Object>)configParams).get(0), 
									(Integer)((List<Object>)configParams).get(1), 
									(Integer)((List<Object>)configParams).get(2), 
									(Integer)((List<Object>)configParams).get(3));
		} catch (Exception e) {
			LOGGER.error ("Failed parsing the " + testConfigFile + " file for test execution", e);
		}
		});
	}

}

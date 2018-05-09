package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.select.Elements;

import junit.framework.TestCase;

public abstract class TestAbstractScraper extends TestCase {

	
	protected Logger LOGGER = LogManager.getLogger( "TestAbstractScraper");
	
	public TestAbstractScraper() {
		super();
	}

	public TestAbstractScraper(String name) {
		super(name);
	}

	public void testRetrieveLinks(Scraper scraper, int totalLinkCount) 
			throws Exception {

		assertTrue ( "The html document should have parsed", scraper.getDoc().isPresent());
		Optional<Elements> links = scraper.retrieveLinks();
		assertTrue ("The parse of " + scraper.getDoc().get().title() + "should return a set of links", links.isPresent()); 
		int linksSize = links.get().size();
		assertEquals ("The parse of " + scraper.getDoc().get().title() + "should return an exact number of links.", 
					   totalLinkCount,  linksSize );
		LOGGER.trace ( "*************** testRetrieveLinks PASSED ********** for " + scraper.getDoc().get().title() );
	}

	public void testRetrieveInternalLinks(Scraper scraper, int totalInteralLinkCount) 
			throws Exception {

		assertTrue ( "The html document should have parsed", scraper.getDoc().isPresent());
		Optional<Elements> links = scraper.retrieveInternalLinks();
		assertTrue ( "The parse of " + scraper.getDoc().get().title() + "should return a set of internal links", 
				links.isPresent());
		int linksSize = links.get().size();
		assertEquals ("The parse of " + scraper.getDoc().get().title() + "should return an exact number of internal links", totalInteralLinkCount, linksSize );
		LOGGER.trace ( "*************** testRetrieveInternalLinks PASSED ********** for " + scraper.getDoc().get().title());
	}

	public void testRetrieveExternalLinks(Scraper scraper, int totalExternalLinkCount) 
			throws Exception {

		assertTrue ( "The html document should have parsed", scraper.getDoc().isPresent());
		Optional<Elements> links = scraper.retrieveExternalLinks();
		assertTrue ( "The parse of " + scraper.getDoc().get().title() + "should return a set of external links", 
				links.isPresent()); 
		int linksSize = links.get().size();
		assertEquals ("The parse of " + scraper.getDoc().get().title() + "should return an exact number of external links", 
				totalExternalLinkCount, linksSize );
		LOGGER.trace ( "*************** testRetrieveExternalLinks PASSED ********** for " + scraper.getDoc().get().title() );

	}
	
	public void testRetrieveImages(Scraper scraper, int totalImageCount) 
			throws Exception {

		assertTrue ( "The html document should have parsed", scraper.getDoc().isPresent());
		Optional<Elements> images = scraper.retrieveImages();
		assertTrue ( "The parse of " + scraper.getDoc().get().title() + "should return a set images", 
				images.isPresent()); 
		int linksSize = images.get().size();
		assertEquals ("The parse of " + scraper.getDoc().get().title() + "should return an exact number of images", 
				totalImageCount, linksSize );
		LOGGER.trace ( "*************** testRetrieveImages PASSED ********** for " + scraper.getDoc().get().title() );

	}

	/**
	 * This test verifies the ability to connect to html and retrieve something
	 * @throws Exception 
	 */
	public void testParseHtml(Scraper scraper) 
			throws Exception {

		Optional<String> html = scraper.parseHtmlDocument();
		assertTrue ( "The html document should have parsed", scraper.getDoc().isPresent());
		assertTrue ( "The parse of " + scraper.getDoc().get().title() + " should have retrieved HTML content from the doc",
				html.isPresent());
		int htmlSize = html.get().length();
		assertTrue ("The parsed html should have more then 1 character", htmlSize > 0);
		LOGGER.trace ( "*************** testParseHtml PASSED ********** for " + scraper.getDoc().get().title() );
	}
	
	public void testAllScraperMethods ( Scraper scraper, int totalLinkCount, 
			int totalInternalLinkCount, int totalExternalLinkCount,
			int totalImageCount ) 
					throws Exception{
		testParseHtml(scraper);
		testRetrieveLinks(scraper, totalLinkCount);
		testRetrieveInternalLinks(scraper, totalInternalLinkCount);
		testRetrieveExternalLinks(scraper, totalExternalLinkCount);
		testRetrieveImages(scraper, totalImageCount);
	}

	
	/**
	 * Parses a test config file for either html files or websites.  The baseUri token is last since it is optional
	 * and only needed for file parsing.
	 * The config file needs to be space delimmeted and the order of fields are
	 * 		-totalLinkCount
	 * 		-totalInternalLinkCount
	 * 		-totalExternalLinkCount
	 * 		-totalImageCount
	 * 		-file/url name
	 * 		-(Optional) baseURI name
	 * @param testConfigFile
	 * @return
	 * @throws Exception
	 */
	public List<Object> retrieveConfiguration (String testConfigFile)
			throws Exception{

		List<Object> testConfigParameters = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(getClass().getResource(testConfigFile).toURI()));) {
			Scanner lineScanner;
			while ( scanner.hasNext())
			{
				List<Object> configValues = new ArrayList<>();
				String configString = scanner.nextLine();
				// Drop out of the loop if the line begins with a comment delimiter
				if ( configString.startsWith("#") )
				{
					continue;
				}
				lineScanner = new Scanner ( configString );
				addInt(lineScanner, configValues);// totalLinkCount 
				addInt(lineScanner, configValues);// totalInternalLinkCount 
				addInt(lineScanner, configValues);// totalExternalLinkCount 
				addInt(lineScanner, configValues);// totalImageCount 
				addString(lineScanner, configValues);// fileName
				addString(lineScanner, configValues);// baseUri 
				testConfigParameters.add(configValues);
			}
		}
		return testConfigParameters;
	}
	
	private void addString ( Scanner lineScanner, List<Object> configValues) {
		if ( lineScanner.hasNext() )
		{
			configValues.add(lineScanner.next());
		}
	}
	
	private void addInt ( Scanner lineScanner, List<Object> configValues) {
		if ( lineScanner.hasNext() )
		{
			configValues.add(lineScanner.nextInt());
		}
	}
	
	
	

}
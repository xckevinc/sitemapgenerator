package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.jsoup.select.Elements;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * A set of tests for retrieving and parsing HTML from a file.  These tests will define the structure
 * of the FileScraper class {@link WebScraper}
 * 
 * @author Kevin Carter
 *
 */
public class TestFileScraper extends TestCase {
	
//	private static final String TEST_HTML_FILE = "/org/carter/sitemapgenerator/controller/ozreport.html";
//	private static final int TOTAL_LINK_COUNT = 217;
//	private static final String TEST_HTML_FILE = "/org/carter/sitemapgenerator/controller/Apache.html";
//	private static final int TOTAL_LINK_COUNT = 334;
	
	private static final String TEST_HTML_FILE = "/org/carter/sitemapgenerator/controller/jsoup.html";
	private static final String TEST_BASE_URI = "https://jsoup.org/";
	private static final int TOTAL_LINK_COUNT = 45;
	private static final int TOTAL_INTERNAL_LINK_COUNT = 36;
	private static final int TOTAL_EXTERNAL_LINK_COUNT = 9;

	
	/**
	 * This test verifies the ability to connect to a local file and retrieve something
	 * @throws URISyntaxException 
	 */
	@Test
	public void testParseFile() throws URISyntaxException
	{
		File file = new File(getClass().getResource(TEST_HTML_FILE).toURI());
		Scraper fileScraper =  new FileScraper(file, TEST_BASE_URI);
		Optional<String> html = fileScraper.parseHtmlDocument();
		assertTrue ( "The parse should have retrieved HTML content from the local file", html.isPresent());
		if ( html.isPresent() )
			{
				int htmlSize = html.get().length();
				assertTrue ("The parsed html file should have more then 1 character", htmlSize > 0);
			}
	}
	
	@Test
	public void testRetrieveLinksFromFile() throws URISyntaxException
	{
		File file = new File(getClass().getResource(TEST_HTML_FILE).toURI());
		Scraper fileScraper =  new FileScraper(file, TEST_BASE_URI);
		Optional<Elements> links = fileScraper.retrieveLinks();
		assertTrue ( "The parsed html file should return a set of links", links.isPresent()); 
		if ( links.isPresent() )
		{
			int linksSize = links.get().size();
			assertEquals ("The parsed html file should return an exact number of links.", TOTAL_LINK_COUNT,  linksSize );
		}
	}
	
	
	@Test
	public void testRetrieveInternalLinksFromFile() throws URISyntaxException {
		File file = new File(getClass().getResource(TEST_HTML_FILE).toURI());
		Scraper fileScraper =  new FileScraper(file, TEST_BASE_URI);
		Optional<Elements> links = fileScraper.retrieveInternalLinks();
		assertTrue ( "The parsed website should return a set of links", links.isPresent()); 
		if ( links.isPresent() )
		{
			int linksSize = links.get().size();
			assertEquals ("The parsed html file should return an exact number of internal links", TOTAL_INTERNAL_LINK_COUNT, linksSize );
			System.out.println( "# links: " + linksSize);
		}
	}
	
	@Test
	public void testRetrieveExternalLinksFromFile() throws URISyntaxException {
		File file = new File(getClass().getResource(TEST_HTML_FILE).toURI());
		Scraper fileScraper =  new FileScraper(file, TEST_BASE_URI);		
		Optional<Elements> links = fileScraper.retrieveExternalLinks();
		assertTrue ( "The parsed website should return a set of links", links.isPresent()); 
		if ( links.isPresent() )
		{
			int linksSize = links.get().size();
			assertEquals ("The parsed html file should return an exact number of external links", TOTAL_EXTERNAL_LINK_COUNT, linksSize );
			System.out.println( "# links: " + linksSize);
		}
	}

}

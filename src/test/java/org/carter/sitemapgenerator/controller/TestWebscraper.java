package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
public class TestWebScraper extends TestCase {

	private static final String TEST_URL = "http://www.cnn.com"; 
	//	private static final String TEST_URL = "http://ozreport.com/";

	/**
	 * Default URL timeout time for connecting to websites.  My chosen sample site
	 * of ozreport.com is often VERY slow
	 */
	private static final int DEFAULT_URL_TIMEOUT = 25000;

	/**
	 * This test verifies the ability to connect to a website and retrieve something
	 */
	@Test
	public void testParseUrl()
	{
		Scraper webscraper =  new WebScraper( TEST_URL).withTimeout(DEFAULT_URL_TIMEOUT);
		Optional<String> html = webscraper.parseHtmlDocument();
		assertTrue ( "The parse should have retrieved HTML content from the url", html.isPresent());
		if ( html.isPresent() )
		{
			int htmlSize = html.get().length();
			assertTrue ("The parsed website should have more then 1 character", htmlSize > 0);
		}
	}


	@Test
	public void testRetrieveLinksFromUrl()
	{
		Scraper webscraper =  new WebScraper( TEST_URL).withTimeout(DEFAULT_URL_TIMEOUT);
		Optional<Elements> links = webscraper.retrieveLinks();
		assertTrue ( "The parsed website should return a set of links", links.isPresent()); 
		if ( links.isPresent() )
		{
			int linksSize = links.get().size();
			assertTrue ("The parsed website should return at least one link", linksSize > 0 );
			System.out.println( "# links: " + linksSize);
		}
	}

	@Test
	public void testRetrieveInternalLinksFromUrl() {
		Scraper webscraper =  new WebScraper( TEST_URL).withTimeout(DEFAULT_URL_TIMEOUT);
		Optional<Elements> links = webscraper.retrieveInternalLinks();
		assertTrue ( "The parsed website should return a set of links", links.isPresent()); 
		if ( links.isPresent() )
		{
			int linksSize = links.get().size();
			assertTrue ("The parsed website should return at least one internal link", linksSize > 0 );
			System.out.println( "# links: " + linksSize);
		}
	}

	@Test
	public void testRetrieveExternalLinksFromUrl() {
		Scraper webscraper =  new WebScraper( TEST_URL).withTimeout(DEFAULT_URL_TIMEOUT);
		Optional<Elements> links = webscraper.retrieveExternalLinks();
//		int linksSize = links.size();
//		assertTrue ("The parsed website should return at least one external link", linksSize > 0 );
	}

}

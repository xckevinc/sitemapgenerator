package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
	
	private static final String TEST_HTML_FILE = "/org/carter/sitemapgenerator/controller/ozreport.html";
	
	
	/**
	 * This test verifies the ability to connect to a local file and retrieve something
	 * @throws URISyntaxException 
	 */
	@Test
	public void testParseFile() throws URISyntaxException
	{
		File file = new File(getClass().getResource(TEST_HTML_FILE).toURI());
		Scraper fileScraper =  new FileScraper(file);
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
		Scraper fileScraper =  new FileScraper(file);
		List<String> links = fileScraper.retrieveLinks();
		int linksSize = links.size();
		assertTrue ("The parsed html file should return at least one link", linksSize > 0 );
	}
	
	@Test
	public void testRetrieveInternalLinksFromFile() throws URISyntaxException {
		File file = new File(getClass().getResource(TEST_HTML_FILE).toURI());
		Scraper fileScraper =  new FileScraper(file);
		List<String> links = fileScraper.retrieveInternalLinks();
		int linksSize = links.size();
		assertTrue ("The parsed html file should return at least one internal link", linksSize > 0 );
	}
	
	@Test
	public void testRetrieveExternalLinksFromFile() throws URISyntaxException {
		File file = new File(getClass().getResource(TEST_HTML_FILE).toURI());
		Scraper fileScraper =  new FileScraper(file);		
		List<String> links = fileScraper.retrieveExternalLinks();
		int linksSize = links.size();
		assertTrue ("The parsed html file should return at least one external link", linksSize > 0 );
	}

}

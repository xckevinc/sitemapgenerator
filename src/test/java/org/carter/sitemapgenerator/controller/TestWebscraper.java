package org.carter.sitemapgenerator.controller;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * A set of tests for retrieving and parsing HTML from a URL.  These tests will define the structure
 * of the Webscraper class {@link Webscraper}
 * @author krcarte
 *
 */
public class TestWebscraper extends TestCase {
	
	/**
	 * This test verifies the ability to connect to a website and retrieve something
	 */
	@Test
	public void testParseUrl()
	{
		Webscraper webscraper =  new Webscraper( "http://ozreport.com/");
		String html = webscraper.parseUrl();
		int htmlSize = html.length();
		assertTrue ("The parsed website should have more then 1 character", htmlSize > 0);
	}
	
	@Test
	public void testRetrieveLinks()
	{
		Webscraper webscraper =  new Webscraper( "http://ozreport.com/");
		List<String> links = webscraper.retrieveLinks();
		int linksSize = links.size();
		assertTrue ("The parsed website should return at least one link", linksSize > 0 );
	}
	
	@Test
	public void testRetrieveInternalLinks() {
		Webscraper webscraper =  new Webscraper( "http://ozreport.com/");
		List<String> links = webscraper.retrieveInternalLinks();
		int linksSize = links.size();
		assertTrue ("The parsed website should return at least one internal link", linksSize > 0 );
	}
	
	@Test
	public void testRetrieveExternalLinks() {
		Webscraper webscraper =  new Webscraper( "http://ozreport.com/");
		List<String> links = webscraper.retrieveExternalLinks();
		int linksSize = links.size();
		assertTrue ("The parsed website should return at least one external link", linksSize > 0 );
	}

}

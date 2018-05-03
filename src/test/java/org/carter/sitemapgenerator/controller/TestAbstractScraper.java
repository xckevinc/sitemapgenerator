package org.carter.sitemapgenerator.controller;

import java.util.Optional;

import org.jsoup.select.Elements;

import junit.framework.TestCase;

public abstract class TestAbstractScraper extends TestCase {

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
		assertEquals ("The parsed html should return an exact number of links.", totalLinkCount,  linksSize );
	}

	public void testRetrieveInternalLinks(Scraper scraper, int totalInteralLinkCount) 
			throws Exception {
		
		assertTrue ( "The html document should have parsed", scraper.getDoc().isPresent());
		Optional<Elements> links = scraper.retrieveInternalLinks();
		assertTrue ( "The parse of " + scraper.getDoc().get().title() + "should return a set of internal links", 
				links.isPresent());
		int linksSize = links.get().size();
		assertEquals ("The parse of " + scraper.getDoc().get().title() + "should return an exact number of internal links", totalInteralLinkCount, linksSize );
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
	}

}
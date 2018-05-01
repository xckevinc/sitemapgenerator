package org.carter.sitemapgenerator.controller;

import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This sitemapgenerator tool should be able to scrape html documents from the internet or
 * from the local filesystem.  This Scraper interface allows the different concrete classes 
 * to be used interchangeably.
 * 
 * @author Kevin Carter
 *
 */
public interface Scraper {

	
	/**
	 * Used to retrieve a collection of all external links present in the website found
	 * at the urlName specified for this scraper.
	 * 
	 * @return List<String> a list of links from the target url
	 */
	Optional<Elements> retrieveExternalLinks();

	/**
	 * Used to retrieve a collection of all internal links present in the website found
	 * at the urlName specified for this scraper.
	 * 
	 * @return List<String> a list of links from the target url
	 */
	Optional<Elements> retrieveInternalLinks();

	/**
	 * This method uses the jsoup parsing to extract all "a[href]" links from
	 * the HTML source this Scraper was initialzed to.
	 * @return Optional<Elements> an Elements collection of Elements representing the complete
	 * set of links from the source HTML
	 */
	Optional<Elements>  retrieveLinks();

	/**
	 * Used to retrieve the entire HTML content from the Document, if present.
	 * 
	 * @return Optional<String> the html string retrieved from the set urlName for this class
	 */
	Optional<String> parseHtmlDocument();

	/**
	 * Method responsible for lazy instantiating the Document field for other methods to access.
	 * 
	 * @return Optional<Document> the Document object created by the jsoup connection or parse
	 */
	Optional<Document> getDoc();

}

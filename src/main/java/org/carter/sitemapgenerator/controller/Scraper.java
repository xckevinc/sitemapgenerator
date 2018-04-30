package org.carter.sitemapgenerator.controller;

import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Document;

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
	List<String> retrieveExternalLinks();

	/**
	 * Used to retrieve a collection of all internal links present in the website found
	 * at the urlName specified for this scraper.
	 * 
	 * @return List<String> a list of links from the target url
	 */
	List<String> retrieveInternalLinks();

	/**
	 * Used to retrieve a collection of all links present in the website found
	 * at the urlName specified for this scraper.
	 * 
	 * @return List<String> a list of links from the target url
	 */
	List<String> retrieveLinks();

	/**
	 * Used to retrieve the entire HTML content from the Document, if present.
	 * 
	 * @return Optional<String> the html string retrieved from the set urlName for this class
	 */
	Optional<String> parseHtmlDocument();

	/**
	 * Method responsible for lazy instantiating the Document field for other methods to access.
	 * 
	 * @return the jsoup document file associated with the url specified in the constructor
	 */
	Optional<Document> getDoc();

}

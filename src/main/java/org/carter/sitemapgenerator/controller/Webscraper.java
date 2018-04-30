package org.carter.sitemapgenerator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * A Webscraper is the foundation class for the business logic of this application.
 * It is responsible for querying URL's, parsing the resultant HTML, and optionally
 * filtering the results
 * @author krcarte
 *
 */

public class Webscraper {
	
	/** 
	 * The immutable url name for this scraper
	 */
	private final String urlName;
	
	/**
	 * The jsoup Document lazy instatiated by a query for utility methods to parse
	 */
	private Document doc = null;
	
	/**
	 * The log4j logger used to provide class levell logging functions
	 */
	private static final Logger LOGGER = LogManager.getLogger("Webscraper");

	/* 
	 * The no argument constructor is hidden to force initialization with the urlName
	 */
	private Webscraper()
	{
		urlName = null;
	}

	/**
	 * Creates a new Webscraper with the given url string.
	 * @param urlName This is the web address this scraper will crawl
	 */
	public Webscraper(String urlName) {
		this.urlName = urlName;
	}
	
	/**
	 * The Document initializer method that establishes the HTTPConnection to the
	 * specified urlName
	 */
	private void initializeDoc()
	{
		try {
			doc = Jsoup.connect(urlName)
					.data("query", "Java")
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(3000)
					.post();
		} catch (IOException e) {
			LOGGER.error("Could not establish connection to url: " + urlName, e);
		}
	}
	
	/**
	 * Method responsible for lazy instantiating the Document field for other methods to access.
	 * 
	 * @return the jsoup document file associated with the url specified in the constructor
	 */
	public Optional<Document> getDoc()
	{
		if ( doc == null )
		{
			initializeDoc();
		}
		return Optional.ofNullable( doc );
	}

	/**
	 * Used to retrieve the entire HTML content from the Document, if present.
	 * 
	 * @return Optional<String> the html string retrieved from the set urlName for this class
	 */
	public Optional<String> parseUrl ()
	{
		Optional<Document> doc = getDoc();
		Optional<String> optionalString = Optional.empty();
		if ( doc.isPresent() )
		{
			optionalString = Optional.of(doc.get().wholeText());
		}
		return optionalString;
	}

	/**
	 * Used to retrieve a collection of all links present in the website found
	 * at the urlName specified for this scraper.
	 * 
	 * @return List<String> a list of links from the target url
	 */
	public List<String> retrieveLinks() {
		List<String> links = new ArrayList();
		return links;
	}

	/**
	 * Used to retrieve a collection of all internal links present in the website found
	 * at the urlName specified for this scraper.
	 * 
	 * @return List<String> a list of links from the target url
	 */
	public List<String> retrieveInternalLinks() {
		List<String> links = new ArrayList();
		return links;
	}

	/**
	 * Used to retrieve a collection of all external links present in the website found
	 * at the urlName specified for this scraper.
	 * 
	 * @return List<String> a list of links from the target url
	 */
	public List<String> retrieveExternalLinks() {
		List<String> links = new ArrayList();
		return links;
	}

}

package org.carter.sitemapgenerator.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;


/**
 * {@inheritDoc}
 * 
 * A WebScraper is the primary accessor to the business logic of this application.
 * It is responsible for querying URL's, then utilizing parsing of the resultant HTML, 
 * and optionally filtering the results from its parent class AbstractScraper.
 * 
 * @author Kevin Carter
 *
 */

public class WebScraper extends AbstractScraper{

	/** 
	 * The immutable file name for this scraper
	 */

	private int timeout = 3000;

	/**
	 * The log4j logger used to provide class level logging functions
	 */
	private static final Logger LOGGER = LogManager.getLogger("WebScraper");

	/* 
	 * The no argument constructor is hidden to force initialization with the urlName and baseUri
	 */
	private WebScraper()
	{
		//		name = "";
	}

	/**
	 * Creates a new Webscraper with the given url string.
	 * @param urlName This is the web address this scraper will crawl
	 */
	public WebScraper(String urlName, String baseUri ) {
		this.name = urlName;
		this.domainName = baseUri;
	}

	/**
	 * Optionally specify a timeout following the Builder Pattern
	 * @param timeout
	 * @return
	 */
	public AbstractScraper withTimeout( int timeout )
	{
		this.timeout = timeout;
		return this;
	}

	/**
	 * The Document initializer method that either establishes the HTTPConnection to the
	 * specified name or reads from the local file if this Webscraper was initialized
	 * with isUrl equal to false.
	 */
	protected void initializeDoc() throws IOException
	{
		LOGGER.trace ( "Connecting to:" + name);
		doc = Jsoup.connect(name)
				.data("query", "Java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(timeout).ignoreHttpErrors(true).followRedirects(true)
				.post();

		// Alternate code to capture connection errors
		//			Connection con = Jsoup.connect(urlName)
		//					.data("query", "Java")
		//					.userAgent("Mozilla")
		//					.cookie("auth", "token")
		//					.timeout(timeout);
		//			Connection.Response resp = con.execute();
		//			doc = con.post();
	}

}

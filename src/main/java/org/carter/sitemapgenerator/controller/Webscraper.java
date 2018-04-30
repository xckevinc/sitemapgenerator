package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


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
	 * The immutable url name for this scraper
	 */
	private final String urlName;

	/** 
	 * The immutable file name for this scraper
	 */
	
	private int timeout = 3000;
	
	/**
	 * The log4j logger used to provide class level logging functions
	 */
	private static final Logger LOGGER = LogManager.getLogger("WebScraper");

	/* 
	 * The no argument constructor is hidden to force initialization with the urlName
	 */
	private WebScraper()
	{
		urlName = "";
	}

	/**
	 * Creates a new Webscraper with the given url string.
	 * @param urlName This is the web address this scraper will crawl
	 */
	public WebScraper(String urlName ) {
		this.urlName = urlName;
	}
	
	/**
	 * Optionally specify a timeout following the Builder Pattern
	 * @param timeout
	 * @return
	 */
	public WebScraper withTimeout( int timeout )
	{
		this.timeout = timeout;
		return this;
	}
	
	/**
	 * The Document initializer method that either establishes the HTTPConnection to the
	 * specified name or reads from the local file if this Webscraper was initialized
	 * with isUrl equal to false.
	 */
	protected void initializeDoc()
	{
		try {
			doc = Jsoup.connect(urlName)
					.data("query", "Java")
					.userAgent("Mozilla")
					.cookie("auth", "token")
					.timeout(timeout)
					.post();
		} catch (IOException e) {
			LOGGER.error("Could not establish connection to url: " + urlName, e);
		}
	}

}

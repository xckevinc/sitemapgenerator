package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

/**
 * {@inheritDoc}
 * 
* A FileScraper is the secondary accessor to the business logic of this application.
 * It is responsible for loading HTML files from disc, then utilizing parsing of the resultant 
 * HTML, and optionally filtering the results from its parent class AbstractScraper.
 * 
 * @author krcarte
 *
 */

public class FileScraper extends AbstractScraper {
	
	/** 
	 * The immutable file name for this scraper
	 */
	private final File file;

	/**
	 * Stores the baseUri used to concat absolute URL paths when parsing an HTML file
	 */
	protected String baseUri;
	
	/**
	 * The log4j logger used to provide class level logging functions
	 */
	private static final Logger LOGGER = LogManager.getLogger("FileScraper");

	/* 
	 * The no argument constructor is hidden to force initialization with the urlName
	 */
	private FileScraper()
	{
		file = null;
	}
	
	/**
	 * Creates a new FileScraper with the given filename string.
	 * @param urlName This is the web address this scraper will crawl
	 */
	public FileScraper(String fileName, String baseUri) {
		this.file = new File(fileName);
		this.name = fileName;
		this.baseUri = baseUri;
	}

	@Override
	protected void initializeDoc() {
		try {
				doc = Jsoup.parse(file, "UTF-8", baseUri);
		} catch (IOException e) {
			LOGGER.error("Could not open file: " + file.getName(), e);
		}
	}
}

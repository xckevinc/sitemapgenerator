package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.io.IOException;

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
	public FileScraper(File file ) {
		this.file = file;
	}

	@Override
	protected void initializeDoc() {
		try {
//				File input = new File(fileName);
				doc = Jsoup.parse(file, "UTF-8", "http://ozreport.com/");
		} catch (IOException e) {
			LOGGER.error("Could not open file: " + file.getName(), e);
		}
	}

}

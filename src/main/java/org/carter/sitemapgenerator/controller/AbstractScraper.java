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
 * An AbstractScraper is the foundation class for the business logic of this application.
 * It is responsible for parsing the resultant HTML from a query, and optionally
 * filtering the results
 * 
 * @author Kevin Carter
 *
 */

public abstract class AbstractScraper implements Scraper{


	/**
	 * The jsoup Document lazy instatiated by a query for utility methods to parse
	 */
	protected Document doc = null;

	public Optional<Document> getDoc()
	{
		if ( doc == null )
		{
			initializeDoc();
		}
		return Optional.ofNullable( doc );
	}


	public Optional<String> parseHtmlDocument ()
	{
		Optional<Document> doc = getDoc();
		Optional<String> optionalString = Optional.empty();
		if ( doc.isPresent() )
		{
			optionalString = Optional.of(doc.get().wholeText());
		}
		return optionalString;
	}


	public List<String> retrieveLinks() {
		List<String> links = new ArrayList<String>();
		return links;
	}


	public List<String> retrieveInternalLinks() {
		List<String> links = new ArrayList<String>();
		return links;
	}


	public List<String> retrieveExternalLinks() {
		List<String> links = new ArrayList<String>();
		return links;
	}

	protected abstract void initializeDoc();

}

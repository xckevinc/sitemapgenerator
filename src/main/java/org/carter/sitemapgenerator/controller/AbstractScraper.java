package org.carter.sitemapgenerator.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


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
	 * The log4j logger used to provide class level logging functions
	 */
	private static final Logger LOGGER = LogManager.getLogger("AbstractScraper");

	/**
	 * The jsoup Document lazy instantiated by a query for utility methods to parse
	 */
	protected Document doc = null;


	@Override
	public Optional<Document> getDoc()
	{
		if ( doc == null )
		{
			initializeDoc();
		}
		return Optional.ofNullable( doc );
	}


	@Override
	public Optional<String> parseHtmlDocument (){
		Optional<Document> doc = getDoc();
		Optional<String> optionalString = Optional.empty();
		if ( doc.isPresent() )
		{
			optionalString = Optional.of(doc.get().wholeText());
		}
		return optionalString;
	}


	@Override
	public Optional<Elements> retrieveLinks() {
		Optional<Document> doc = getDoc();
		Optional<Elements> links = Optional.empty();
		if ( doc.isPresent() )
		{
			links = Optional.of (doc.get().select("a[href]"));
		}
		return links;
	}

	@Override
	public Optional<Elements> retrieveInternalLinks() {
		LOGGER.trace("Filtering Links for Internal Links");
		return filterElements ( retrieveLinks(), AbstractScraper::isInternalLink );
	}

	@Override
	public Optional<Elements> retrieveExternalLinks() {
		LOGGER.trace("Filtering Links for External Links");
		return filterElements ( retrieveLinks(), AbstractScraper::isExternalLink );
	}
	
	
	/**
	 * Parses the HTML and retrieves a collection of all the images found.
	 * 
	 * @return Optional<Elements> a collection of img refs
	 */
	@Override
	public Optional<Elements> retrieveImages() {
		Optional<Document> doc = getDoc();
		Optional<Elements> images = Optional.empty();
		if ( doc.isPresent() )
		{
			images = Optional.of (doc.get().getElementsByTag("img"));
		}
		return images;
	}
	
	
	/**
	 * This is a simple conditional test to verify if an Element
	 * contains its baseUri in the actual href.  This conditional does not treat sub-domains
	 * as part of the same domain, also referred to as an "Internal Link".  For example, money.cnn.com
	 * and cnn.com are treated as different domains and links from one to the other are not considered 
	 * Internal.
	 * 
	 * @param element the Element to be checked against the baseUri
	 * @return boolean representing if the Element contains the baseUri 
	 */
	private static boolean isInternalLink ( Element element ){
		return element.absUrl("href").contains(element.baseUri());
	}

	
	/**
	 * This is a simple conditional test to verify if an Element
	 * does not contain its baseUri in the actual href
	 * @param element the Element to be checked against the baseUri
	 * @return boolean representing if the Element doesn't contain the baseUri 
	 */
	private static boolean isExternalLink ( Element element ){
		return !isInternalLink(element);
	}
	

	/**
	 * This method uses the boolean Predicate p to determine which Elements from the elements
	 * parameter are filtered out of the collection.  A new Optional collection of elements is
	 * returned that contains only Elements that were not filtered.
	 * 
	 * @param elements the source collection of elements
	 * @param p the Predicate<Element> used to test if an Element should be filtered
	 * @return an Optional<Elements> collection of Elements that have had all non-predicate matching
	 * items removed
	 */
	private Optional<Elements> filterElements ( Optional<Elements> elements, Predicate<Element> p )
	{
		Optional<Elements> allLinks = retrieveLinks();
		Optional<Elements> filteredLinks = Optional.empty();
		List<Element> tempElements = new ArrayList<>();

		if ( allLinks.isPresent() && getDoc().isPresent() )
		{
			Iterator<Element> it = allLinks.get().iterator();
			while ( it.hasNext() )
			{
				Element element = it.next();

				if ( p.test(element) )
				{
					tempElements.add(element);
					LOGGER.trace(element.absUrl("href") + " added to Links");
				}
			}
			filteredLinks = Optional.of(new Elements (tempElements));
		}

		return filteredLinks;		
	}

	/**
	 * The way a Document Object is obtained is dependent on the type of source.  It is
	 * the responsibility of the concrete sub classes to initialize this Object
	 */
	protected abstract void initializeDoc();


}

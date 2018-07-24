package org.carter.sitemapgenerator.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger LOGGER = LogManager.getLogger(AbstractScraper.class);

	/**
	 * The jsoup Document lazy instantiated by a query for utility methods to parse
	 */
	protected Document doc = null;

	/** 
	 * The immutable url/file name for this scraper
	 */
	protected String name;
	
	/**
	 * The base URL the scan was initiated from that is used to define the base domain
	 */
	protected String domainName;

	protected AbstractScraper()
	{
		name = "";
		domainName = "";
	}

	@Override
	public Optional<Document> getDoc()
	{
		if ( doc == null )
		{
			try {
				initializeDoc();
			} catch (IOException e) {
				LOGGER.warn("Could not retrieve: " + name );
				LOGGER.trace(e);
			}
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
	public Optional<Set<String>> retrieveInternalLinks() {
		LOGGER.trace("Filtering Links for Internal Links");
		Optional<Elements> allLinks = retrieveLinks();
		Optional<Set<String>> internalLinks = Optional.empty();
		
		//Don't allow duplicates in the collection of links
		Set<String> tempElements = new LinkedHashSet<>();

		if ( allLinks.isPresent() && getDoc().isPresent() )
		{
			Iterator<Element> it = allLinks.get().iterator();
			while ( it.hasNext() )
			{
				Element element = it.next();

				if ( isInternalLink(element, domainName) )
				{
					tempElements.add(element.absUrl("href"));
					LOGGER.trace(element.absUrl("href") + " added to Internal Links");
				}
			}
			internalLinks = Optional.of(tempElements);
		}

		return internalLinks;		
	}

	@Override
	public Optional<Set<String>> retrieveExternalLinks() {
		LOGGER.trace("Filtering Links for External Links");
		Optional<Elements> allLinks = retrieveLinks();
		Optional<Set<String>> externalLinks = Optional.empty();
		
		//Don't allow duplicates in the collection of links
		Set<String> tempElements = new LinkedHashSet<>();
		
		if ( allLinks.isPresent() && getDoc().isPresent() )
		{
			Iterator<Element> it = allLinks.get().iterator();
			while ( it.hasNext() )
			{
				Element element = it.next();

				if ( isExternalLink(element, domainName) )
				{
					tempElements.add(element.absUrl("href"));
					LOGGER.trace(element.absUrl("href") + " added to External Links");
				}
			}
			externalLinks = Optional.of(tempElements);
		}

		return externalLinks;		
	}
	
	
	/**
	 * Parses the HTML and retrieves a collection of all the images found.
	 * 
	 * @return Optional<Elements> a collection of img refs
	 */
	@Override
	public Optional<Set<String>> retrieveImages() {
		Optional<Document> doc = getDoc();
		Optional<Set<String>> images = Optional.empty();
		//Don't allow duplicates in the collection of links
				Set<String> tempImages = new LinkedHashSet<>();
				
		if ( doc.isPresent() ) {
			Elements elems = doc.get().getElementsByTag("img");
			Iterator<Element> it = elems.iterator(); // { //.forEach(image -> tempImages.add(image.absUrl("href")));
			while ( it.hasNext() ) {
				Element next = it.next();
				tempImages.add(next.attr("src"));
				LOGGER.trace(next.attr("src") + " added to Images");
			}
		}
		return Optional.of (tempImages);
	}
	
	
	/**
	 * This is a simple conditional test to verify if an Element
	 * contains its baseUri in the actual href.  This conditional does not treat sub-domains
	 * as part of the same domain, also referred to as an "Internal Link".  For example, money.cnn.com
	 * and cnn.com are treated as different domains and links from one to the other are not considered 
	 * Internal.
	 * 
	 * @param element the Element to be checked against the parentUrl
	 * @param domainName the original source URL that defines an internal link
	 * @return boolean representing if the Element contains the baseUri 
	 */

	protected static boolean isInternalLink ( Element element, String domainName ){
		return element.absUrl("href").contains(domainName);
	}
	
	/**
	 * This is a simple conditional test to verify if an Element
	 * does not contain its baseUri in the actual href
	 * @param element the Element to be checked against the parentUrl
	 * @param domainName the original source URL that defines an external link
	 * @return boolean representing if the Element doesn't contain the baseUri 
	 */
	protected static boolean isExternalLink ( Element element, String domainName ){
		return !isInternalLink(element, domainName);
	}
	

	/**
	 * The way a Document Object is obtained is dependent on the type of source.  It is
	 * the responsibility of the concrete sub classes to initialize this Object
	 * @throws IOException 
	 */
	protected abstract void initializeDoc() throws IOException;


}

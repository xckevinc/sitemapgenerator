package org.carter.sitemapgenerator.controller;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carter.sitemapgenerator.model.PageModel;
import org.carter.sitemapgenerator.model.SitemapModel;
import org.junit.Test;


public class TestSiteCrawler {

	/*
	 * This is more of an integration test then a Unit Test since it depends on other objects
	 * and the internet.  
	 * 
	 * TODO: Make file based testing so that internet inconsistencies do not cause failure. 
	 */
	protected Logger LOGGER = LogManager.getLogger(TestSiteCrawler.class);
	
	private static final int DEFAULT_URL_TIMEOUT = 25000;
	
	@Test
	public void testGenerateSiteMap() {
		// NOTES
		// need a SiteModel mock
		// need PageModel mocks - returns a set of external links, totals external links?
		
		SiteCrawler siteGenerator = new SiteCrawler("https://jsoup.org/").withTimeout(DEFAULT_URL_TIMEOUT); 
		//siteGenerator = new SiteCrawler("https://www.apache.org/").withTimeout(DEFAULT_URL_TIMEOUT); 

		//siteGenerator = new SiteCrawler("http://ralstones.jeffcopublicschools.org/").withTimeout(DEFAULT_URL_TIMEOUT); 
		SitemapModel sitemapModel = siteGenerator.generateSiteMap();
		LOGGER.trace ( "Has Size:" + sitemapModel.size() );
		assertTrue("sitemapModel has content", sitemapModel.size() > 0 );
		Iterator<PageModel> it = sitemapModel.getPages().values().iterator();
		
		while ( it.hasNext() )
		{
			PageModel pm =  it.next();
			LOGGER.trace ( pm.getTitle());
			LOGGER.trace ( pm.getUrl());
			LOGGER.trace( "External Links:" + pm.getExternalLinkCount() );
			LOGGER.trace( "Internal Links:" + pm.getInternalLinkCount() );
			LOGGER.trace( "Image Refs:" + pm.getImageRefCount() );
		}
	}
}

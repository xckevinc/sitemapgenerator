package org.carter.sitemapgenerator.controller;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carter.InternetTests;
import org.carter.LocalTests;
import org.carter.sitemapgenerator.model.PageModel;
import org.carter.sitemapgenerator.model.SitemapModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class TestSiteCrawler {

	/*
	 * This is more of an integration test then a Unit Test since it depends on other objects
	 * and the internet. Since this is not production code I am taking this shortcut to speed up development 
	 * 
	 * TODO: Make file based testing so that internet inconsistencies do not cause failure. 
	 * TODO: Use Mocks to remove dependency on other objects
	 */
	protected Logger LOGGER = LogManager.getLogger(TestSiteCrawler.class);
	
	private static final int DEFAULT_URL_TIMEOUT = 25000;
	
	@Test
	@Category(InternetTests.class)
	public void testGenerateSiteMap() {
		// NOTES
		// need PageModel mocks - returns a set of external links, totals external links?
		
		SiteCrawler siteGenerator = new SiteCrawler("http://www.rmhpa.org/").withTimeout(DEFAULT_URL_TIMEOUT); 
		SitemapModel sitemapModel = siteGenerator.generateSiteMap();
		LOGGER.trace ( "Has Size:" + sitemapModel.size() );
		assertTrue("sitemapModel has content", sitemapModel.size() > 0 );
	}
}

package org.carter.sitemapgenerator.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carter.sitemapgenerator.model.PageModel;
import org.carter.sitemapgenerator.model.SitemapModel;

public class SiteCrawler {

	private String rootUrl;
	
	private int timeout = 100;
	
	private static int NUM_RETRIES = 5;

	private static final Logger LOGGER = LogManager.getLogger(SiteCrawler.class);

	public SiteCrawler (String rootUrl) {
		this.rootUrl = rootUrl;
	}
	
	/**
	 * Optionally specify a timeout following the Builder Pattern
	 * @param timeout
	 * @return
	 */
	public SiteCrawler withTimeout( int timeout )
	{
		this.timeout = timeout;
		return this;
	}

	public SitemapModel generateSiteMap() {
		SitemapModel sitemapModel = new SitemapModel(rootUrl);
		analyzeHtml ( rootUrl, sitemapModel);
		return sitemapModel;
	}

	private void analyzeHtml(String sourceUrl, SitemapModel sitemapModel) {

		LOGGER.trace("Analyzing url: " + sourceUrl );
		// Check to see if this page was already scanned and logged
		if ( sitemapModel.containsUrl(sourceUrl) ) { 
			LOGGER.trace("Skipping the url because it was already scanned: " + sourceUrl);
			return;
		}
		LOGGER.trace("Processing the contents of url: " + sourceUrl );

		PageModel pageModel = createPageModel(sourceUrl, sitemapModel.getDomainName());
		sitemapModel.add(pageModel);
		pageModel.getInternalLinks().forEach(link -> analyzeHtml( link, sitemapModel));
	}


	private PageModel createPageModel (String sourceUrl, String domainName){
		Scraper scraper = new WebScraper(sourceUrl, domainName).withTimeout(timeout);
		PageModel pageModel = new PageModel(sourceUrl);
		for (int i = 0; i < NUM_RETRIES; i++)
		{
			if (scraper.getDoc().isPresent()) {
				pageModel.setTitle(scraper.getDoc().get().title());
				break;
			}
			else {
				pageModel.setTitle( "Non-HTML Page: " + sourceUrl);
			}
		}
		if ( scraper.retrieveExternalLinks().isPresent() ){
			pageModel.setExternalLinks(scraper.retrieveExternalLinks().get());
		}
		if ( scraper.retrieveInternalLinks().isPresent() ){
			pageModel.setInternalLinks(scraper.retrieveInternalLinks().get());
		}
		if ( scraper.retrieveImages().isPresent() ){
			pageModel.setImageRefs(scraper.retrieveImages().get());
		}
		return pageModel;
	}
}

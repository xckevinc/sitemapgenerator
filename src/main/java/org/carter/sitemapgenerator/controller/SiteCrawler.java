package org.carter.sitemapgenerator.controller;

import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.carter.sitemapgenerator.model.PageModel;
import org.carter.sitemapgenerator.model.SitemapModel;
import org.jsoup.nodes.Element;

public class SiteCrawler {

	private Scraper scraper;

	private String rootUrl;
	
	private int timeout = 100;

	private static final Logger LOGGER = LogManager.getLogger(SiteCrawler.class);

	public SiteCrawler(Scraper scraper) {
		this.scraper = scraper;
	}

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
		SitemapModel sitemapModel = new SitemapModel();
		analyzeHtml ( rootUrl, sitemapModel);
		return sitemapModel;
	}

	private void analyzeHtml(String url, SitemapModel sitemapModel) {

		LOGGER.trace("Analyzing url: " + url );
		if ( sitemapModel.containsUrl(url) ) {
			LOGGER.trace("Skipping the url because it was already scanned: " + url);
			return;
		}
		LOGGER.trace("Processing the contents of url: " + url );

		PageModel pageModel = createPageModel(url);
		sitemapModel.add(pageModel);
		ArrayList<Element> links = pageModel.getInternalLinks();
		for ( int i = 0; i < links.size(); i++ )
		{
			Element link = links.get(i);
			//LOGGER.trace ( "Analyzing link: " + link.absUrl("href") );
			analyzeHtml( link.absUrl("href"), sitemapModel);
			//					Set<String> keySet = sitemapModel.getPages().keySet();
			//					keySet.forEach(key->	LOGGER.trace ( "Page Key:" + key));

		}
	}


	private PageModel createPageModel (String url){

		Scraper scraper = new WebScraper(url).withTimeout(timeout);
		PageModel pageModel = new PageModel(url);
		pageModel.setTitle(scraper.getDoc().get().title());


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

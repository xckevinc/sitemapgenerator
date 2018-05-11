package org.carter.sitemapgenerator.view;

import static org.junit.Assert.assertTrue;

import org.carter.sitemapgenerator.controller.SiteCrawler;
import org.carter.sitemapgenerator.model.SitemapModel;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Test;

public class TestSitemapView {
	

	private static final int DEFAULT_URL_TIMEOUT = 25000;
	
	@Test
	public void generateSitemapHtml() {
		SiteCrawler siteGenerator = new SiteCrawler("https://jsoup.org/").withTimeout(DEFAULT_URL_TIMEOUT); 
		//siteGenerator = new SiteCrawler("https://www.apache.org/").withTimeout(DEFAULT_URL_TIMEOUT); 

		siteGenerator = new SiteCrawler("http://ralstones.jeffcopublicschools.org/").withTimeout(DEFAULT_URL_TIMEOUT); 
		SitemapModel sitemapModel = siteGenerator.generateSiteMap();
		SitemapView sitemapView = new SitemapView(sitemapModel);
		String htmlView = sitemapView.generateHtmlView();
		assertTrue("SitemapView is not empty", htmlView.length() > 0);
		boolean valid = Jsoup.isValid(htmlView, Whitelist.basic());
		assertTrue ("SitemapView is valid html", valid);
		
		
	}

}

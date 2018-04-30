package org.carter.sitemapgenerator.controller;

import java.util.ArrayList;
import java.util.List;

public class Webscraper {
	
	private String urlName;

	private Webscraper()
	{
	}

	public Webscraper(String urlName) {
		this.urlName = urlName;
	}
	
	public String parseUrl ()
	{
		return "";
	}

	public List<String> retrieveLinks() {
		List<String> links = new ArrayList();
		return links;
	}

	public List<String> retrieveInternalLinks() {
		List<String> links = new ArrayList();
		return links;
	}

	public List<String> retrieveExternalLinks() {
		List<String> links = new ArrayList();
		return links;
	}

}

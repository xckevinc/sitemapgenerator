package org.carter.sitemapgenerator.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A SitemapModel is an ADT comprised of a collection of page metadata all derived from a common root domain
 * @author krcarte
 *
 */
public class SitemapModel {
	/*
	 * This class could have easily been implemented as just an instance of a templated ArrayList but I originally envisioned
	 * this being an ADT in case other behavior was needed.
	 */

	private Map<String, PageModel> pages;
	
	private String domainName;

	private SitemapModel() {
		pages = new LinkedHashMap<>();
	}
	
	public SitemapModel(String baseUri) {
		this();
		this.domainName = baseUri;
	}
	
	public String getDomainName() {
		return domainName;
	}
	
	public void set(PageModel pageModel) {
		pages.put(pageModel.getUrl(), pageModel);
	}
	
	public boolean add(PageModel pageModel) {
		if ( containsUrl(pageModel.getUrl()) )
		{
			return false;
		}
		pages.put(pageModel.getUrl(), pageModel);
		return true;
	}

	public int size() {
		return pages.size();
	}
	
	public boolean containsUrl (String url) {
		return get(url).isPresent();
	}

	public Optional<PageModel> get(String url) {
		Optional<PageModel> optional = Optional.ofNullable(pages.get(url));
		return optional;
	}
	
	public Map <String, PageModel> getPages(){
		return pages; // TODO consider returning an immutable clone
	}

}

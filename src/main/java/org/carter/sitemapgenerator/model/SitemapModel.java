package org.carter.sitemapgenerator.model;

import java.util.ArrayList;
import java.util.List;

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

	private List<PageModel> pages;
	
	public SitemapModel() {
		pages = new ArrayList<>();
	}
	
	public void add(PageModel pageModel) {
		pages.add(pageModel);
	}

	public Object size() {
		return pages.size();
	}

	public PageModel get(int index) {
		return pages.get(index);
	}

}

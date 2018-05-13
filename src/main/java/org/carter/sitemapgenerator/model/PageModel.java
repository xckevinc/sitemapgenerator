package org.carter.sitemapgenerator.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A PageModel is a basic ADT to encapsulate the metadata from a html page scan
 * @author krcarte
 *
 */
public class PageModel {
	
	private Set<String> externalLinks;
	
	private Set<String> internalLinks;
	
	private Set<String> imageRefs;

	private String title;

	private String url;

	private PageModel() {
		externalLinks = new LinkedHashSet<>();
		internalLinks = new LinkedHashSet<>();
		imageRefs = new LinkedHashSet<>();
	}
	
	public PageModel(String url) {
		this();
		this.url = url;
	}
	
	public void setExternalLinks(Set<String> externalLinks) {
		this.externalLinks = externalLinks;
		
	}

	public Set<String> getExternalLinks() {
		return this.externalLinks;
	}

	public void addExternalLink(String newLink) {
		externalLinks.add(newLink);
	}

	public int getExternalLinkCount() {
		return externalLinks.size();
	}

	public void setInternalLinks(Set<String> internalLinks) {
		this.internalLinks = internalLinks;
	}
	
	public Set<String> getInternalLinks() {
		return internalLinks;
	}

	public void addInternalLink(String newLink) {
		internalLinks.add(newLink);
	}

	public int getInternalLinkCount() {
		return internalLinks.size();
	}
	
	public void setImageRefs(Set<String> imageRefs) {
		this.imageRefs = imageRefs;
	}

	public Set<String> getImageRefs() {
		return imageRefs;
	}

	public void addImageRef(String newImageRef) {
		imageRefs.add(newImageRef);
	}

	public int getImageRefCount() {
		return imageRefs.size();
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl( String url ) {
		this.url = url;
	}



}

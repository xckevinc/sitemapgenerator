package org.carter.sitemapgenerator.model;

import java.util.ArrayList;

import org.apache.logging.log4j.message.Message;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A PageModel is a basic ADT to encapsulate the metadata from a html page scan
 * @author krcarte
 *
 */
public class PageModel {
	
	private Elements externalLinks;
	
	private Elements internalLinks;
	
	private Elements imageRefs;

	private String title;

	private String url;

	private PageModel()
	{}
	
	public PageModel(String url) {
		externalLinks = new Elements();
		internalLinks = new Elements();
		imageRefs = new Elements();
		this.url = url;
	}
	
	public void setExternalLinks(Elements externalLinks) {
		this.externalLinks = externalLinks;
		
	}

	public Elements getExternalLinks() {
		return this.externalLinks;
	}

	public void addExternalLink(Element newLink) {
		externalLinks.add(newLink);
	}

	public int getExternalLinkCount() {
		return externalLinks.size();
	}

	public void setInternalLinks(Elements internalLinks) {
		this.internalLinks = internalLinks;
	}
	
	public ArrayList<Element> getInternalLinks() {
		return internalLinks;
	}

	public void addInternalLink(Element newLink) {
		internalLinks.add(newLink);
	}

	public int getInternalLinkCount() {
		return internalLinks.size();
	}
	
	public void setImageRefs(Elements imageRefs) {
		this.imageRefs = imageRefs;
	}

	public Elements getImageRefs() {
		return imageRefs;
	}

	public void addImageRef(Element newImageRef) {
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

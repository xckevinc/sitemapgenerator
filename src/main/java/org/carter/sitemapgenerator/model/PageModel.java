package org.carter.sitemapgenerator.model;

import java.util.ArrayList;

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

	public PageModel() {
		externalLinks = new Elements();
		internalLinks = new Elements();
		imageRefs = new Elements();
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



}

package org.carter.sitemapgenerator.view;

import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.h3;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.main;
import static j2html.TagCreator.p;
import static j2html.TagCreator.table;
import static j2html.TagCreator.title;

import java.util.Map;
import java.util.Set;

import org.carter.sitemapgenerator.model.PageModel;
import org.carter.sitemapgenerator.model.SitemapModel;

import j2html.tags.ContainerTag;

public class SitemapView {

	private SitemapModel sitemapModel;

	public SitemapView(SitemapModel sitemapModel) {
		this.sitemapModel = sitemapModel;
	}

	public String generateHtmlView() {
		Set<String> keys = sitemapModel.getPages().keySet();
		Map<String, PageModel> pages = sitemapModel.getPages();
		ContainerTag ct = html(
				head(
						title("Sitemp")
						),
				body(
						main(
								each(keys, key -> 
								div ( p(""),
										table(	div(
												p("."),
												h2("Page Title: " + pages.get(key).getTitle()),
												h2("Page URL: " + pages.get(key).getUrl()),
												h3("Internal Links: " + pages.get(key).getInternalLinkCount()),
												table(
														each(pages.get(key).getInternalLinks(), internalLink -> p(internalLink)))
							                			.withStyle ("border: 2px solid black"),
							                	h3("External Links: " + pages.get(key).getExternalLinkCount()),
							                	table(
							                			each(pages.get(key).getExternalLinks(), externalLink -> p(externalLink)))
							                		.	withStyle ("border: 2px solid yellow"),
							                	h3("Images: " + pages.get(key).getImageRefCount()),
							                	table(
							                			each(pages.get(key).getImageRefs(), imageRef -> p(imageRef)))
							                			.withStyle ("border: 2px solid red")
												) 
											)
											.withStyle ("border: 4px solid black")
										)
								)
							)
						)
				);
		String htmlString = ct.renderFormatted();
		return htmlString;
	}
}

package org.carter.sitemapgenerator.view;

import org.carter.sitemapgenerator.model.PageModel;
import org.carter.sitemapgenerator.model.SitemapModel;

import j2html.tags.ContainerTag;
import j2html.tags.Tag;

import static j2html.TagCreator.*;

import java.util.Map;
import java.util.Set;

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
						title("Sitemp"),
						link().withRel("stylesheet").withHref("/css/main.css")
						),
				body(
						main(attrs("#main.content"),
								h1("Site Crawler"),
								p("This application will take the url you provide and scan that site for a map of all its pages and links"),
								each(keys, key -> 
									div(
						                h2(pages.get(key).getTitle()),
						                p(pages.get(key).getUrl()),
						                h3("Internal Links"),
						                //each(pages.get(key).getInternalLinks(), internalLink -> h3(TAB_STRING + internalLink.absUrl("href"))),
						                table(
						                		each(pages.get(key).getInternalLinks(), internalLink -> h3(internalLink.absUrl("href"))))
						                		.withStyle("margin-left:20px"),
						                h3("Internal Link Count:" + pages.get(key).getInternalLinkCount()),
						                h3("External Links"),
						                table(
						                		each(pages.get(key).getExternalLinks(), externalLink -> h3(externalLink.absUrl("href"))))
						                		.withStyle("margin-left:20px"),
						                h3("External Link Count:" + pages.get(key).getExternalLinkCount()),
						                h3("Images"),
						                table(
						                		each(pages.get(key).getImageRefs(), imageRef -> h3(imageRef. nodeName())))
						                		.withStyle("margin-left:20px"),
						                h3("Image Count:" + pages.get(key).getImageRefCount())
						                ) 
//						                each(pages.get(key).getInternalLinks(), internalLink -> h3("test") )
//									.withStyle("font-weight: bold")
									)
								)
						)
				);
		String htmlString = ct.renderFormatted();
		return htmlString;
	}
	
	public static Tag createPageModelView(PageModel pageModel) {
		return   createPageModelTitle(pageModel);
	}
	
	public static Tag createPageModelTitle(PageModel pageModel) {
		return h2( "Title: " + pageModel.getTitle() + " Link: " + pageModel.getUrl() );
	}
	
//	public static Tag createPageModelInternalLinks(PageModel pageModel) {
//		
//		return each(pageModel.getInternalLinks(), internalLink -> h3(internalLink));
//		
//	}
	
	public static Tag createPageModelExternalLinks(PageModel pageModel) {
		return null;
		
	}
	
	public static Tag createPageModelImageRefs(PageModel pageModel) {
		return null;
		
	}

}

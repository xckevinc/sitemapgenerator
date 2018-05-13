package org.carter.sitemapgenerator;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.carter.sitemapgenerator.controller.SiteCrawler;
import org.carter.sitemapgenerator.model.SitemapModel;
import org.carter.sitemapgenerator.view.SitemapView;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);   

		SiteCrawler siteGenerator = new SiteCrawler("https://jsoup.org/").withTimeout(25000); 
		//siteGenerator = new SiteCrawler("https://www.apache.org/").withTimeout(DEFAULT_URL_TIMEOUT); 

		siteGenerator = new SiteCrawler("http://ralstones.jeffcopublicschools.org/").withTimeout(25000); 
		siteGenerator = new SiteCrawler("http://www.rmhpa.org/").withTimeout(25000); 
		
		SitemapModel sitemapModel = siteGenerator.generateSiteMap();

		SitemapView sitemapView = new SitemapView(sitemapModel);
		String htmlView = sitemapView.generateHtmlView();

		jep.setContentType("text/html");
		jep.setText(htmlView);

		JScrollPane scrollPane = new JScrollPane(jep);     
		JFrame f = new JFrame("title");
		f.getContentPane().add(scrollPane);
		f.setSize(812, 442);
		f.show();
	}
}

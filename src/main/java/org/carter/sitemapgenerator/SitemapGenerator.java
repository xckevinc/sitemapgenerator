package org.carter.sitemapgenerator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.carter.sitemapgenerator.controller.SiteCrawler;
import org.carter.sitemapgenerator.model.SitemapModel;
import org.carter.sitemapgenerator.view.SitemapView;

/**
 * Hello world!
 *
 */
public class SitemapGenerator 
{
	
	JPanel panel;
	
	JScrollPane scrollPane;
	
	JSplitPane splitPane;
	
	JTextField urlEntry;
	
	JLabel urlLabel;
	
	JPanel titlePane;
	
	JPanel inputPane;
	
	JScrollPane outputPane;
	
	JEditorPane sitemapOutputPane;
	
	JButton generateSitemapButton;
	
	protected void createComponents() {
		
		urlLabel = new JLabel("URL:");
		urlEntry = new JTextField(30);
		urlEntry.addPropertyChangeListener( new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		generateSitemapButton = new JButton ("Generate Sitemap");
		generateSitemapButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiteCrawler siteGenerator = new SiteCrawler(urlEntry.getText()).withTimeout(25000); 
				SitemapModel sitemapModel = siteGenerator.generateSiteMap();
				SitemapView sitemapView = new SitemapView(sitemapModel);
				String htmlView = sitemapView.generateHtmlView();
				sitemapOutputPane.setText(htmlView);
				sitemapOutputPane.setCaretPosition(0);
				
			}
			
		});
		
		inputPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		outputPane =  new JScrollPane();     
		sitemapOutputPane = new JEditorPane();
		sitemapOutputPane.setContentType("text/html");
		sitemapOutputPane.setEditable(false);
		
		outputPane =  new JScrollPane(sitemapOutputPane);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel = new JPanel(new BorderLayout());
	}
	
	protected void layoutComponents() {
		inputPane.add(urlLabel);
		inputPane.add(urlEntry);
		inputPane.add(generateSitemapButton);
	
		splitPane.setTopComponent(inputPane);
		splitPane.setBottomComponent(outputPane);
		
		panel.add(splitPane);		
	}
	

	private Component getPanel() {
		return panel;
	}
	
	public static void main( String[] args )
	{

		SitemapGenerator app = new SitemapGenerator();
		app.createComponents();
		app.layoutComponents();

		JScrollPane scrollPane = new JScrollPane(app.getPanel());     
		JFrame f = new JFrame("Sitemap Generator");
		f.getContentPane().add(app.getPanel());
		f.setSize(812, 442);
		f.show();
	}

}

package org.carter.sitemapgenerator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.carter.sitemapgenerator.controller.SiteCrawler;
import org.carter.sitemapgenerator.controller.UrlValidator;
import org.carter.sitemapgenerator.model.SitemapModel;
import org.carter.sitemapgenerator.view.SitemapView;

/**
 * Hello world!
 *
 */
public class SitemapGenerator 
{
	
	protected JPanel panel;
	
	protected JScrollPane scrollPane;
	
	protected JSplitPane splitPane;
	
	protected JTextField urlEntry;
	
	protected JLabel urlLabel;
	
	protected JPanel titlePane;
	
	protected JPanel inputPane;
	
	protected JScrollPane outputPane;
	
	protected JEditorPane sitemapOutputPane;
	
	protected JButton generateSitemapButton;
	
	protected void createComponents() {
		
		urlLabel = new JLabel("URL:");
		urlEntry = new JTextField(30);
		UrlValidator urlValidator = new UrlValidator();
		
		urlEntry.addKeyListener( new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// Here is the alternate logical structure Kashif alluding to
				generateSitemapButton.setEnabled(urlEntry.getText().startsWith("http://"));
				
				// here is the regex matching we pulled up on Google but opted for a simple prefix check instead.
				String urlText = urlEntry.getText();
				Pattern urlPattern = Pattern.compile ( "^(?:(?:http(?:s)?|ftp)://)(?:\\S+(?::(?:\\S)*)?@)?(?:(?:[a-z0-9\u00a1-\uffff](?:-)*)*(?:[a-z0-9\u00a1-\uffff])+)(?:\\.(?:[a-z0-9\u00a1-\uffff](?:-)*)*(?:[a-z0-9\u00a1-\uffff])+)*(?:\\.(?:[a-z0-9\u00a1-\uffff]){2,})(?::(?:\\d){2,5})?(?:/(?:\\S)*)?$");
				
				// and here is an alternate regex that is not as restrictive
				urlPattern = Pattern.compile ( "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
				generateSitemapButton.setEnabled ( urlPattern.matcher(urlText).matches());
				
				// but the best solution is with a Validator
				generateSitemapButton.setEnabled(urlValidator.isValid(urlEntry.getText()));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// Do Nothing
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Do Nothing				
			}
			
		});

		generateSitemapButton = new JButton ("Generate Sitemap");
		generateSitemapButton.setEnabled(false);
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
		
		// TODO:  Add a "Save Sitemap" button here
		
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

		JFrame f = new JFrame("Sitemap Generator");
		f.getContentPane().add(app.getPanel());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 800);
		f.setVisible(true);
	}

}

package org.carter.sitemapgenerator.model;


import static org.junit.Assert.assertEquals;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the PageModel for basic accessors
 * @author krcarte
 *
 */
public class TestPageModel  {

	protected String link1;
	protected String link2;
	protected String link3;
	protected Set<String> links;
	protected PageModel sut;

	protected Logger LOGGER = LogManager.getLogger( "TestPageModel");

	@Before
	public void runBeforeEachTest()
	{
		sut = new PageModel("Title");
		link1 = new String("test1");
		link2 = new String("test2");
		links = new LinkedHashSet<>();
		link3 = new String("test3");
	}


	@Test
	public void testSetExternalLinks()
	{
		links.add(link1);
		links.add(link2);
		sut.setExternalLinks (links);
		assertEquals ( "Verify external links is correct", 2, sut.getExternalLinks().size() );
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testSetExternalLinks PASSED ********** "  );
	}

	@Test
	public void testSetInternalLinks()
	{
		links.add(link1);
		links.add(link2);
		sut.setInternalLinks (links);
		assertEquals ( "Verify external links is correct", 2, sut.getInternalLinks().size() );
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testSetInternalLinks PASSED ********** "  );	}

	@Test
	public void testSetImageRefs()
	{
		links.add(link1);
		links.add(link2);
		sut.setImageRefs (links);
		assertEquals ( "Verify set/getImageRefs is correct", 2, sut.getImageRefs().size() );

		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testSetImageRefs PASSED ********** "  );
	}


	@Test 
	public void testAddExternalLink() {
		sut.addExternalLink(link1);
		assertEquals ("External links should have added one entry", 1, sut.getExternalLinkCount());
		sut.addExternalLink(link2);
		assertEquals ("External links should have added a second entry", 2, sut.getExternalLinkCount());
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testAddExternalLink PASSED ********** "  );
	}

	@Test 
	public void testAddInternalLink() {
		sut.addInternalLink(link1);
		assertEquals ("Internal links should have added one entry", 1, sut.getInternalLinkCount());
		sut.addInternalLink(link2);
		assertEquals ("Internal links should have added a second entry", 2, sut.getInternalLinkCount());
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testAddInternalLink PASSED ********** "  );
	}

	@Test 
	public void testAddImageRef() {
		sut.addImageRef(link1);
		assertEquals ("Image Refs should have added one entry", 1, sut.getImageRefCount());
		sut.addImageRef(link2);
		assertEquals ("Image Refs should have added one entry", 2, sut.getImageRefCount());
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testAddImageRef PASSED ********** "  );
	}
}

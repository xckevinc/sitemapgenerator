package org.carter.sitemapgenerator.model;


import static org.junit.Assert.assertEquals;

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

	protected Element element1;
	protected Element element2;
	protected Element element3;
	protected Elements elements;
	protected PageModel sut;

	protected Logger LOGGER = LogManager.getLogger( "TestPageModel");

	@Before
	public void runBeforeEachTest()
	{
		sut = new PageModel("Title");
		element1 = new Element("test1");
		element2 = new Element("test2");
		elements = new Elements();
		element3 = new Element("test3");
	}


	@Test
	public void testSetExternalLinks()
	{
		elements.add(element1);
		elements.add(element2);
		sut.setExternalLinks (elements);
		assertEquals ( "Verify external links is correct", 2, sut.getExternalLinks().size() );
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testSetExternalLinks PASSED ********** "  );
	}

	@Test
	public void testSetInternalLinks()
	{
		elements.add(element1);
		elements.add(element2);
		sut.setInternalLinks (elements);
		assertEquals ( "Verify external links is correct", 2, sut.getInternalLinks().size() );
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testSetInternalLinks PASSED ********** "  );	}

	@Test
	public void testSetImageRefs()
	{
		elements.add(element1);
		elements.add(element2);
		sut.setImageRefs (elements);
		assertEquals ( "Verify set/getImageRefs is correct", 2, sut.getImageRefs().size() );

		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testSetImageRefs PASSED ********** "  );
	}


	@Test 
	public void testAddExternalLink() {
		sut.addExternalLink(element1);
		assertEquals ("External links should have added one entry", 1, sut.getExternalLinkCount());
		sut.addExternalLink(element2);
		assertEquals ("External links should have added a second entry", 2, sut.getExternalLinkCount());
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testAddExternalLink PASSED ********** "  );
	}

	@Test 
	public void testAddInternalLink() {
		sut.addInternalLink(element1);
		assertEquals ("Internal links should have added one entry", 1, sut.getInternalLinkCount());
		sut.addInternalLink(element2);
		assertEquals ("Internal links should have added a second entry", 2, sut.getInternalLinkCount());
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testAddInternalLink PASSED ********** "  );
	}

	@Test 
	public void testAddImageRef() {
		sut.addImageRef(element1);
		assertEquals ("Image Refs should have added one entry", 1, sut.getImageRefCount());
		sut.addImageRef(element2);
		assertEquals ("Image Refs should have added one entry", 2, sut.getImageRefCount());
		// This is redundant for JUnit but helps find problems faster when initially implementing
		LOGGER.trace ( "*************** testAddImageRef PASSED ********** "  );
	}
}

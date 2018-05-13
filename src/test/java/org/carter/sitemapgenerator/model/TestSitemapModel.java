package org.carter.sitemapgenerator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.TestCase;

/**
 * Test the SitemapModel accessors
 * @author krcarte
 *
 */
public class TestSitemapModel {

	/*
	 * This is just a basic storage class so even testing accessors could be considered excessive.
	 * I am including just the basics as a step in my data structure design process
	 */

	SitemapModel sut;
	PageModel pageModel;
	
	@Before
	public void setup()
	{
		 sut = new SitemapModel("testBaseUri");
		 pageModel = new PageModel ("TestUrl");
		 // TODO This data should be mocked, I will come back to it or consider this a noted point
		 // to remove this unit test's dependency on another class and improve it
				 //Mockito.mock ( PageModel.class );
	}

	@Test
	public void testPageAdd()
	{
		sut.add(pageModel);
		assertEquals ("Sitemap should have one page", 1, sut.size());
	}
	
	@Test
	public void testGetPage()
	{
		sut.add(pageModel);
		Optional<PageModel> pm = sut.get(pageModel.getUrl());
		assertTrue ("Page Model should exist, it was just added", pm.isPresent());
		assertEquals ("Pages should be equal", pageModel, pm.get());
	}
}

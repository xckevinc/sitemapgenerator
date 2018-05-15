package org.carter.sitemapgenerator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.carter.InternetTests;
import org.carter.LocalTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;
/**
 * Test the SitemapModel accessors
 * @author krcarte
 *
 */
@Category({InternetTests.class, LocalTests.class})
public class TestSitemapModel {

	/*
	 * This is just a basic storage class so even testing accessors could be considered excessive.
	 * I am including just the basics as a step in my data structure design process
	 */

	protected SitemapModel sut;
	protected PageModel pageModelMock;
	
	@Before
	public void setup()
	{
		 sut = new SitemapModel("testBaseUri");
		 pageModelMock = mock(PageModel.class);
		 when(pageModelMock.getUrl()).thenReturn("TestUrl");
	}

	@Test
	public void testPageAdd()
	{
		sut.add(pageModelMock);
		assertEquals ("Sitemap should have one page", 1, sut.size());
	}
	
	@Test
	public void testGetPage()
	{
		sut.add(pageModelMock);
		Optional<PageModel> pm = sut.get(pageModelMock.getUrl());
		assertTrue ("Page Model should exist, it was just added", pm.isPresent());
		assertEquals ("Pages should be equal", pageModelMock, pm.get());
	}
}

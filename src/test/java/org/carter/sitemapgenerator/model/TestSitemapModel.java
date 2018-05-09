package org.carter.sitemapgenerator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.TestCase;

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
		 sut = new SitemapModel();
		 pageModel = Mockito.mock ( PageModel.class );
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
		assertEquals ("Pages should be equal", pageModel, sut.get(0));
	}
}

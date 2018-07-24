package org.carter.sitemapgenerator.controller;

import java.util.regex.Pattern;

public class UrlValidator {
	
	// Make this a configurable resource
	private final static String DEFAULT_REGEX = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	// alternate regex
	//regex = "^(?:(?:http(?:s)?|ftp)://)(?:\\S+(?::(?:\\S)*)?@)?(?:(?:[a-z0-9\u00a1-\uffff](?:-)*)*(?:[a-z0-9\u00a1-\uffff])+)(?:\\.(?:[a-z0-9\u00a1-\uffff](?:-)*)*(?:[a-z0-9\u00a1-\uffff])+)*(?:\\.(?:[a-z0-9\u00a1-\uffff]){2,})(?::(?:\\d){2,5})?(?:/(?:\\S)*)?$");
	
	private Pattern urlPattern;
	
	public UrlValidator()
	{
		this(DEFAULT_REGEX);
	}
	
	public UrlValidator (String regex)
	{
		urlPattern = Pattern.compile (regex);
	}
	
	public void setRegex(String regex)
	{
		urlPattern =  Pattern.compile (regex);
	}
	
	public boolean isValid (String testUrlString)
	{
		return urlPattern.matcher(testUrlString).matches();
	}
}

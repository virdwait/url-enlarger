package com.snipchars.url.enlarger.server;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Test {@link Enlarger} with some services and cases.
 * 
 * @author Laurent Pellegrino
 * 
 * @version $Id$
 */
public class TestEnlarger {

	private static final String defaultRedirection = "http://www.snipchars.com";

	@Test
	public void testEnlargerWithSomeServices() {
		String[] shortURLsAsString = { "http://tinyurl.com/388boh9",
				"http://goo.gl/mIRV", "http://snipurl.com/w3eo9",
				"http://bit.ly/bNwpxE", "http://www.tinyurls.co.uk/E3273",
				"http://tiny.cc/j5l3j" };

		for (String shortURLAsString : shortURLsAsString) {
			URL shortURL = null;
			try {
				shortURL = new URL(shortURLAsString);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			String longURLAsString = Enlarger.enlarge(shortURL).getLongURL()
					.toString();
			if (longURLAsString.endsWith("/")) {
				longURLAsString = longURLAsString.substring(0, longURLAsString
						.length() - 1);
			}
			Assert.assertEquals(defaultRedirection, longURLAsString);
		}
	}

	public void testEnlargerWithURLWithoutRedirection() {
		try {
			Assert.assertEquals("http://www.snipchars.com", Enlarger.enlarge(
					new URL("http://www.snipchars.com")).getLongURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEnlargeWithMalformedURLs() {
		// TODO
	}

}

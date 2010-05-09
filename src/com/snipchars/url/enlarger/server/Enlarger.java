package com.snipchars.url.enlarger.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Enlarges short URL from any services.
 * 
 * @author Laurent Pellegrino
 * 
 * @version $Id$
 */
public class Enlarger {

	/**
	 * Enlarges an URL by resolving redirection.
	 * 
	 * @param shortURL
	 *            the short URL to enlarge.
	 * @return an {@link EnlargerResult}.
	 */
	public static EnlargerResult enlarge(URL shortURL) {
		EnlargerResult result = new EnlargerResult(shortURL);
		HttpURLConnection conn = null;
		int responseCode;

		try {
			conn = (HttpURLConnection) shortURL.openConnection();
		} catch (IOException e) {
			result.setShortURLValid(false);
		}
		try {
			responseCode = conn.getResponseCode();
			switch (responseCode) {
			case 200:
				result.setLongURL(conn.getURL());
				break;
			case 301:
				result.setLongURL(new URL(conn.getHeaderField("Location")));
				break;
			case 401:
				result.setShortURLValid(false);
			}
			result.setResponseCode(responseCode);
		} catch (MalformedURLException e) {
			result.setLongURLValid(false);
		} catch (IOException e) {
			result.setShortURLValid(false);
		}

		return result;
	}

}

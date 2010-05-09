package com.snipchars.url.enlarger.server;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Result associated to {@link Enlarger} operations.
 * 
 * @author Laurent Pellegrino
 * 
 * @version $Id$
 */
public class EnlargerResult {

	private final URL shortURL;

	private URL longURL;

	private boolean isShortURLValid = true;

	private boolean isLongURLValid = true;

	private int responseCode;

	public EnlargerResult(URL shortURL) {
		this.shortURL = shortURL;
	}

	public EnlargerResult(URL shortURL, URL longURL) {
		this.shortURL = shortURL;
		this.longURL = longURL;
	}

	public boolean isShortURLValid() {
		return this.isShortURLValid;
	}

	public boolean isLongURLValid() {
		return this.isLongURLValid;
	}

	public URL getShortURL() {
		return this.shortURL;
	}

	public URL getLongURL() {
		return this.longURL;
	}

	public int getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public void setLongURL(URL longURL) {
		this.longURL = longURL;
	}

	public void setShortURLValid(boolean isShortURLValid) {
		this.isShortURLValid = isShortURLValid;
	}

	public void setLongURLValid(boolean isLongURLValid) {
		this.isLongURLValid = isLongURLValid;
	}

	public URL getShortUrlCodeQR() {
		try {
			return new URL(
					"http://chart.apis.google.com/chart?cht=qr&amp;chs=150x150&amp;choe=UTF-8&amp;chld=H&amp;chl="
							+ this.shortURL.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public URL getLongUrlCodeQR() {
		try {
			return new URL(
					"http://chart.apis.google.com/chart?cht=qr&amp;chs=150x150&amp;choe=UTF-8&amp;chld=H&amp;chl="
							+ this.longURL.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("Short URL: ");
		buf.append(this.shortURL);
		if (!this.isShortURLValid) {
			buf.append(" (not valid)");
		}
		buf.append("\n");

		if (this.isShortURLValid) {
			buf.append("Long URL: ");
			buf.append(this.longURL);
			buf.append(" (");
			if (!this.isLongURLValid) {
				buf.append("not valid)");
			} else {
				buf.append("valid");
			}
			buf.append(")");
			buf.append("\n");
		}

		return buf.toString();
	}

	public String toJSON() {
		StringBuilder buf = new StringBuilder();

		buf.append("{\n");
		buf.append("  \"url-enlarger\": {\n");
		buf.append("    \"shorturl\": {\n");
		buf.append("      \"qrcode\": \"");
		buf.append(this.getShortUrlCodeQR());
		buf.append("\",\n");
		buf.append("      \"valid\": \"");
		buf.append(this.isShortURLValid);
		buf.append("\",\n");
		buf.append("      \"value\": \"");
		buf.append(this.shortURL.toString());
		buf.append("\"\n");
		if (this.isShortURLValid) {
			buf.append("    },\n");
			buf.append("    \"longurl\": {\n");
			buf.append("      \"qrcode\": \"");
			buf.append(this.getLongUrlCodeQR());
			buf.append("\",\n");
			buf.append("      \"valid\": \"");
			buf.append(this.isLongURLValid);
			buf.append("\",\n");
			buf.append("      \"value\": \"");
			buf.append(this.longURL.toString());
			buf.append("\"\n");
		}
		buf.append("    }\n  }\n}\n");

		return buf.toString();
	}

	public String toXML() {
		StringBuilder buf = new StringBuilder();
		buf.append("<?xml version=\"1.0\" ?>\n");
		buf.append("<url-enlarger>\n");
		buf.append("  <shorturl>\n");
		buf.append("    <qrcode>");
		buf.append(this.getShortUrlCodeQR());
		buf.append("</qrcode>\n");
		buf.append("    <valid>");
		buf.append(this.isShortURLValid);
		buf.append("</valid>\n");
		buf.append("    <value>");
		buf.append(this.shortURL.toString());
		buf.append("</value>\n");
		buf.append("  </shorturl>\n");

		if (this.isShortURLValid) {
			buf.append("  <longurl>\n");
			buf.append("    <qrcode>");
			buf.append(this.getLongUrlCodeQR());
			buf.append("</qrcode>\n");
			buf.append("    <valid>");
			buf.append(this.isLongURLValid);
			buf.append("</valid>\n");
			buf.append("    <value>");
			buf.append(this.longURL.toString());
			buf.append("</value>\n");
			buf.append("  </longurl>\n");
		}
		buf.append("</url-enlarger>");
		return buf.toString();
	}

	public String toYAML() {
		StringBuilder buf = new StringBuilder();

		buf.append("url-enlarger:\n");
		buf.append("  shorturl:\n");
		buf.append("    qrcode: ");
		buf.append(this.getShortUrlCodeQR());
		buf.append("\n");
		buf.append("    valid: ");
		buf.append(this.isShortURLValid);
		buf.append("\n");
		buf.append("    value: ");
		buf.append(this.shortURL.toString());
		buf.append("\n");
		if (this.isShortURLValid) {
			buf.append("  longurl:\n");
			buf.append("    qrcode: ");
			buf.append(this.getLongUrlCodeQR());
			buf.append("\n");
			buf.append("    valid: ");
			buf.append(this.isLongURLValid);
			buf.append("\n");
			buf.append("    value: ");
			buf.append(this.longURL.toString());
		}
		buf.append("\n");

		return buf.toString();
	}

}

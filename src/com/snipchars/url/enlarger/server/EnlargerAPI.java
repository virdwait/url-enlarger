package com.snipchars.url.enlarger.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snipchars.url.enlarger.shared.OutputType;

/**
 * Provides API operations.
 * 
 * @author Laurent Pellegrino
 * 
 * @version $Id$
 */
@SuppressWarnings("serial")
public class EnlargerAPI extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		if (req.getParameterMap().size() > 0) {
			String shortURL = req.getParameter("url");
			String output = req.getParameter("output");
			OutputType type = OutputType.TXT;

			if (output == null) {
				output = "default";
			}

			if (output.equals("json")) {
				type = OutputType.JSON;
			} else if (output.equals("xml")) {
				type = OutputType.XML;
			} else if (output.equals("yaml")) {
				type = OutputType.YAML;
			}

			if (shortURL != null) {
				if (output == null) {
					output = "default";
				} else {
					output = output.toLowerCase();
					output = output.trim();
				}

				if (shortURL != null && !shortURL.isEmpty()) {
					EnlargerResult result = null;

					try {
						result = Enlarger.enlarge(new URL(shortURL));
					} catch (MalformedURLException e) {
						out.print(this.error("malformed url", type));
					}

					switch (type) {
					case JSON:
						out.print(result.toJSON());
						break;
					case XML:
						out.print(result.toXML());
						break;
					case YAML:
						out.print(result.toYAML());
						break;
					default:
						if (result.isShortURLValid()) {
							out.print(result.getLongURL().toString());
						} else {
							out.print(this.error("url " + result.getShortURL()
									+ " is not valid", OutputType.TXT));
						}
						break;
					}
					out.flush();
				}
			}
		}
	}

	private String error(String msg, OutputType type) {
		StringBuilder buf = new StringBuilder();

		switch (type) {
		case JSON:
			buf.append("{\n");
			buf.append("  \"error\": \"");
			buf.append(msg);
			buf.append("\"\n");
			buf.append("}");
			break;
		case XML:
			buf.append("<?xml version=\"1.0\" ?>\n");
			buf.append("<error>");
			buf.append(msg);
			buf.append("</error>");
			break;
		case YAML:
			buf.append("error: ");
			buf.append(msg);
			break;
		default:
			buf.append(msg);
			break;
		}

		return buf.toString();
	}
}
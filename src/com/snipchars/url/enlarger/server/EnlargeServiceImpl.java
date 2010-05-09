package com.snipchars.url.enlarger.server;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.snipchars.url.enlarger.client.EnlargeService;
import com.snipchars.url.enlarger.shared.OutputType;

/**
 * The server side implementation of the RPC service.
 * 
 * @author Laurent Pellegrino
 * 
 * @version $Id$
 */
@SuppressWarnings("serial")
public class EnlargeServiceImpl extends RemoteServiceServlet implements
		EnlargeService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String enlarge(String shortURL) throws IllegalArgumentException {
		try {
			return Enlarger.enlarge(new URL(shortURL)).getLongURL().toString();
		} catch (MalformedURLException e) {
			return "";
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String enlarge(String shortURL, OutputType type)
			throws IllegalArgumentException {
		EnlargerResult result = null;
		try {
			result = Enlarger.enlarge(new URL(shortURL));

			switch (type) {
			case JSON:
				return result.toJSON();
			case XML:
				return result.toXML();
			case YAML:
				return result.toYAML();
			}
		} catch (MalformedURLException e) {
			return "";
		}
		return result.getLongURL().toString();
	}

}

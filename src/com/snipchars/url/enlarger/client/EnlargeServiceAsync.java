package com.snipchars.url.enlarger.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.snipchars.url.enlarger.shared.OutputType;

/**
 * The client side stub for the Asynchronous RPC service.
 * 
 * @author Laurent Pellegrino
 * 
 * @version $Id$
 */
public interface EnlargeServiceAsync {

	public abstract void enlarge(String shortURL, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	public abstract void enlarge(String shortURL, OutputType type,
			AsyncCallback<String> callback) throws IllegalArgumentException;

}

package com.snipchars.url.enlarger.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.snipchars.url.enlarger.shared.OutputType;

/**
 * The client side stub for the RPC service.
 * 
 * @author Laurent Pellegrino
 * 
 * @version $Id$
 */
@RemoteServiceRelativePath("enlarge")
public interface EnlargeService extends RemoteService {

	public abstract String enlarge(String shortURL)
			throws IllegalArgumentException;

	public abstract String enlarge(String shortURL, OutputType type)
			throws IllegalArgumentException;

}

/*
 * Copyright Aduna (http://www.aduna-software.com/) (c) 1997-2006.
 *
 * Licensed under the Aduna BSD-style license.
 */
package com.data2semantics.replication.sparqlproxy.util;

public abstract class Protocol {

	/**
	 * Protocol version.
	 */
	public static final String VERSION = "4";
	
	/**
	 * Parameter name for the query parameter.
	 */
	public static final String QUERY_PARAM_NAME = "query";

	/**
	 * Parameter name for the query language parameter.
	 */
	public static final String QUERY_LANGUAGE_PARAM_NAME = "queryLn";

	/**
	 * Relative location of the template schemas resource.
	 */
	public static final String SCHEMAS = "schemas";

	/**
	 * Custom header used to convey query types from a server to a client. The
	 * client can use the header to decide which set of result parsers are
	 * relevant for the response body.
	 */
	public static final String X_QUERY_TYPE = "X-Query-Type";

	/**
	 * Value for {@link #X_QUERY_TYPE} for tuple query results.
	 */
	public static final String BINDINGS_QUERY = "bindings";

	

	/**
	 * MIME type for transactions: <tt>application/x-rdftransaction</tt>.
	 */
	public static final String TXN_MIME_TYPE = "application/x-rdftransaction";

	public static final String OFFSET = "offset";

	public static final String LIMIT = "limit";
}
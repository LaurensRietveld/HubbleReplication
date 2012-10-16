package com.data2semantics.replication.localDaemon;

import com.hp.hpl.jena.sparql.engine.http.HttpQuery;

public class HttpQueryExtension extends HttpQuery {

	public HttpQueryExtension(String serviceURL) {
		super(serviceURL);
	}

}

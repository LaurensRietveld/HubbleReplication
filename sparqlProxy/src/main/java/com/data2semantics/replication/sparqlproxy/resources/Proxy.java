package com.data2semantics.replication.sparqlproxy.resources;

import org.restlet.Context;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Redirector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data2semantics.replication.sparqlproxy.util.Util;
import com.hp.hpl.jena.query.ResultSet;

public class Proxy extends Redirector {
	public Proxy(Context context, String targetTemplate) {
		super(context, targetTemplate);
	}

	public Proxy(Context context, String target, int modeClientPermanent) {
		super(context, target, modeClientPermanent);
	}

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
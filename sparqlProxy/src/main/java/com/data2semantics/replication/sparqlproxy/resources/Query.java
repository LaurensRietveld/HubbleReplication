package com.data2semantics.replication.sparqlproxy.resources;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data2semantics.replication.sparqlproxy.util.Util;
import com.hp.hpl.jena.query.ResultSet;

public class Query extends ServerResource{
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Get
	public Representation getEntryPoint() throws Exception {
//		Map<String, Object> requestAttributes = getRequestAttributes();
//		boolean hasQuery = false;
//		String queryStr = (String) getRequestAttributes().get(Protocol.QUERY_PARAM_NAME);
//
//		if (queryStr == null) {
//			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing parameter: " + Protocol.QUERY_PARAM_NAME);
//		}
		ResultSet result = Util.query("http://eculture2.cs.vu.nl:5020/sparql/", "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
				"PREFIX patient: <http://www.data2semantics.org/ontology/patient/>\n" + 
				"SELECT DISTINCT * WHERE {\n" + 
				" <http://aers.data2semantics.org/resource/patient/john_doe> ?x ?y\n" + 
				"}");
		// Write the response entity on the console
		return new StringRepresentation("woeiii, in GET" + result.toString());
	}

	
	@Post
	public Representation postEntryPoint(Representation entity) throws Exception {
//		String mimeType = entity.getMediaType().getName();
		return new StringRepresentation("woeiii, in POST");
	}
}
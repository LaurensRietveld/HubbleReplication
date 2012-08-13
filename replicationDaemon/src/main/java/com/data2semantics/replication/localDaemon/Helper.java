package com.data2semantics.replication.localDaemon;

import org.openjena.riot.WebContent;
import org.openjena.riot.web.HttpOp;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class Helper {
	public static String ENDPOINT_ECULTURE2 = "http://eculture2.cs.vu.nl:5020/sparql/";
	public static String ENDPOINT_ECULTURE2_UPDATE = "http://eculture2.cs.vu.nl:5020/update/";
	public static String ENDPOINT_REPLICA = "http://localhost:8080/openrdf-workbench/repositories/localrep/query";
	public static String ENDPOINT_REPLICA_UPDATE = "http://localhost:8080/openrdf-workbench/repositories/localrep/update";
	public static String ENDPOINT_LLD = "http://linkedlifedata.com/sparql";
	
	public static String PREFIXES_PATIENT = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "PREFIX patient: <http://www.data2semantics.org/ontology/patient/>\n";
	public static String PREFIXES_AERS = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
			+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
			+ "PREFIX r4: <http://aers.data2semantics.org/vocab/>\n" + "PREFIX ns3: <tag:eric@w3.org:2009/tmo/translator#>\n"
			+ "PREFIX ns4: <http://www.obofoundry.org/ro/ro.owl#>\n" + "PREFIX ns1: <http://purl.org/cpr/0.75#>\n"
			+ "PREFIX : <http://aers.data2semantics.org/vocab/>\n" +
			"PREFIX patient: <http://www.data2semantics.org/ontology/patient/>\n";
	public static String PREFIXES_ANNOTATIONS = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
			+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
			+ "PREFIX ao: <http://purl.org/ao/core#>\n" + "PREFIX aot: <http://purl.org/ao/types#>\n"
			+ "PREFIX aos: <http://purl.org/ao/selectors#>\n" + "PREFIX aof: <http://purl.org/ao/foaf#>\n"
			+ "PREFIX aoa: <http://purl.org/ao/annotea#>\n" + "PREFIX pav: <http://purl.org/pav#>\n"
			+ "PREFIX ann: <http://www.w3.org/2000/10/annotation-ns#>\n" + "PREFIX pro: <http://purl.obolibrary.org/obo#>\n"
			+ "PREFIX dcterms: <http://purl.org/dc/terms/>\n" + "PREFIX patient: <http://www.data2semantics.org/ontology/patient/>\n";
	public static String PREFIXES_ANNOTATIONS_SWAN = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
			+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
			+ "PREFIX oa: <http://www.w3.org/ns/openannotation/core/>\n"
			+ "PREFIX oax: <http://www.w3.org/ns/openannotation/extension/>\n" + "PREFIX cnt: <http://www.w3.org/2011/content#>\n"
			+ "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + "PREFIX dcterms: <http://purl.org/dc/terms/>\n"
			+ "PREFIX aers: <http://aers.data2semantics.org/>\n"
			+ "PREFIX swanrel: <http://purl.org/swan/2.0/discourse-relationships/>\n"
			+ "PREFIX d2sa: <http://aers.data2semantics.org/vocab/annotation/>\n"
			+ "PREFIX patient: <http://www.data2semantics.org/ontology/patient/>\n";
	public static String PREFIXES_LLD = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
			+ "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
			+ "PREFIX skos-xl: <http://www.w3.org/2008/05/skos-xl#>\n";
	
	public static String getInsertQuery(String endpoint, String prefixes, String queryBody) {
		return prefixes + "INSERT { GRAPH <" + endpoint + "> {\n" + queryBody + "\n" + "}} WHERE { \n" + "SERVICE <" + endpoint + "> {\n"
				+ queryBody + "\n" + "}}";
	}
	
	public static void executeQuery(String endpoint, String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService(endpoint, query, "250");
		ResultSet resultSet = queryExecution.execSelect();
		System.out.println(ResultSetFormatter.asText(resultSet));
	}

	// somehow jena doesnt use 'update' as param in post, and it uses the
	// sparql-update content-type (not recognized by sesame...)
	public static void executeUpdateQuery(String endpoint, String queryString) {
		//UpdateRequest update = UpdateFactory.create(queryString);
		//UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, endpoint);
		// processor.execute();
		HttpOp.execHttpPost(endpoint, WebContent.contentTypeForm, "update=" + queryString);
	}

}

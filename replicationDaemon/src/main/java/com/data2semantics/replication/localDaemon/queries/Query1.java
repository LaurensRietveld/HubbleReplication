package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query1  implements Query {

	public String getInsertQuery() {
		return Helper.getInsertQuery(Helper.ENDPOINT_FOR_SERVICE_CALL, Helper.PREFIXES_PATIENT, "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientID.\n");
	}
	
	public String getSelectAllQuery() {
		return Helper.PREFIXES_PATIENT + "SELECT ?patientID {\n" + "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientID.\n" + "}";
	}
	
	//same as select all
	public String getSelectExampleQuery() {
		return Helper.PREFIXES_PATIENT + "SELECT ?patientID {\n" + "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientID.\n" + "}";
	}

	public String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

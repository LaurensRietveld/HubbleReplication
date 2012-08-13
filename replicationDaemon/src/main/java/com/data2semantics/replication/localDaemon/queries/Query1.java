package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query1 {

	public static String getInsertQuery() {
		return Helper.getInsertQuery(Helper.ENDPOINT_ECULTURE2, Helper.PREFIXES_PATIENT, "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientID.\n");
	}
	
	public static String getSelectAllQuery() {
		return Helper.PREFIXES_PATIENT + "SELECT ?patientID {\n" + "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientID.\n" + "}";
	}
	
	//same as select all
	public static String getSelectExampleQuery() {
		return Helper.PREFIXES_PATIENT + "SELECT ?patientID {\n" + "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientID.\n" + "}";
	}

	public static String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

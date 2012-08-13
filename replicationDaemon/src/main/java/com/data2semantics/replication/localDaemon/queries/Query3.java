package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query3 {

	public static String getInsertQuery() {
		return Helper.getInsertQuery(Helper.ENDPOINT_ECULTURE2, Helper.PREFIXES_ANNOTATIONS_SWAN,
				"?recommendationUri  a d2sa:RecommendationAnnotation . " + "\n 		?recommendationUri oa:hasBody ?recommendationBody .");
	}

	public static String getSelectAllQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n	SELECT ?recommendationUri ?recommendationBody WHERE {"
				+ "\n		?recommendationUri  a d2sa:RecommendationAnnotation . " + "\n 		?recommendationUri oa:hasBody ?recommendationBody ."
				+ "\n}";
	}
	
	//same as select all
	public static String getSelectExampleQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n	SELECT ?recommendationUri ?recommendationBody WHERE {"
				+ "\n		?recommendationUri  a d2sa:RecommendationAnnotation . " + "\n 		?recommendationUri oa:hasBody ?recommendationBody ."
				+ "\n}";
	}

	public static String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

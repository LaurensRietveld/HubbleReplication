package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query3 implements Query  {

	public String getInsertQuery() {
		return Helper.getInsertQuery(Helper.ENDPOINT_PARTIAL_AERS, Helper.PREFIXES_ANNOTATIONS_SWAN,
				"?recommendationUri  a d2sa:RecommendationAnnotation . " + "\n 		?recommendationUri oa:hasBody ?recommendationBody .");
	}

	public String getSelectAllQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n	SELECT ?recommendationUri ?recommendationBody WHERE {"
				+ "\n		?recommendationUri  a d2sa:RecommendationAnnotation . " + "\n 		?recommendationUri oa:hasBody ?recommendationBody ."
				+ "\n}";
	}
	
	//same as select all
	public String getSelectExampleQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n	SELECT ?recommendationUri ?recommendationBody WHERE {"
				+ "\n		?recommendationUri  a d2sa:RecommendationAnnotation . " + "\n 		?recommendationUri oa:hasBody ?recommendationBody ."
				+ "\n}";
	}

	public String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

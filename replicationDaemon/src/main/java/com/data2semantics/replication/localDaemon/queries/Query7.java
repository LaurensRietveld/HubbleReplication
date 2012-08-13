package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query7  implements Query {

	public String getInsertQuery() {
		return Helper.getInsertQuery(Helper.ENDPOINT_ECULTURE2, Helper.PREFIXES_ANNOTATIONS_SWAN,
				"?recommendationUri  a d2sa:RecommendationAnnotation . " + "\n 		?recommendationUri oa:hasBody ?recommendationBody ."
						+ "\n			?recommendationUri d2sa:hasEvidenceSummary ?es . "
						+ "\n		    ?es swanrel:referencesAsSupportingEvidence ?ev ." + "\n 		?ev oa:hasTarget ?tg ."
						+ "\n 		?tg oa:hasSource ?s ." + "\n 		?s owl:sameAs ?doc ." + "\n 		?ta2 oa:hasSource ?doc ."
						+ "\n 		?taga oa:hasTarget ?ta2 . " + "\n					?pp ?p ?aerstag . " + "\n         ?tag skos:relatedMatch ?aerstag ."
						+ "\n			?taga oax:hasSemanticTag ?tag .");
	}

	public String getSelectAllQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n	SELECT ?recommendationUri ?recommendationBody ?aerstag WHERE {"
				+ "\n			?recommendationUri  a d2sa:RecommendationAnnotation . "
				+ "\n 		?recommendationUri oa:hasBody ?recommendationBody ." + "\n			?recommendationUri d2sa:hasEvidenceSummary ?es . "
				+ "\n		    ?es swanrel:referencesAsSupportingEvidence ?ev ." + "\n 		?ev oa:hasTarget ?tg ." + "\n 		?tg oa:hasSource ?s ."
				+ "\n 		?s owl:sameAs ?doc ." + "\n 		?ta2 oa:hasSource ?doc ." + "\n 		?taga oa:hasTarget ?ta2 . "
				+ "\n						?pp ?p ?aerstag . " + "\n         ?tag skos:relatedMatch ?aerstag ." + "\n			?taga oax:hasSemanticTag ?tag ."
				+ "\n}";
	}

	public String getSelectExampleQuery() {
		String patientId = "John Doe";
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n	SELECT DISTINCT ?recommendationUri ?recommendationBody ?aerstag WHERE {" +
				"\n			?recommendationUri  a d2sa:RecommendationAnnotation . " +
				"\n 		?recommendationUri oa:hasBody ?recommendationBody ." +
				"\n			?recommendationUri d2sa:hasEvidenceSummary ?es . " +
				"\n		    ?es swanrel:referencesAsSupportingEvidence ?ev ." +
				"\n 		?ev oa:hasTarget ?tg ." +
				"\n 		?tg oa:hasSource ?s ." +
				"\n 		?s owl:sameAs ?doc ." +
				"\n 		?ta2 oa:hasSource ?doc ." +
				"\n 		?taga oa:hasTarget ?ta2 . " +
				"\n			?pp rdfs:label '" + patientId + "'@en ." + 
				"\n			?pp ?p ?aerstag . "+
				"\n         ?tag skos:relatedMatch ?aerstag ."+
				"\n			?taga oax:hasSemanticTag ?tag ."+			
				"\n}";
	}

	public String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

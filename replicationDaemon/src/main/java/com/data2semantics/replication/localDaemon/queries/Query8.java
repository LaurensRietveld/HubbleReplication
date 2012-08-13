package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query8 implements Query {

	public String getInsertQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "INSERT { GRAPH <http://eculture2.cs.vu.nl:5020/sparql/> {\n"
				+ "   ?evidenceUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceAnnotation> . \n"
				+ "?evidenceSummaryUri <http://purl.org/swan/2.0/discourse-relationships/referencesAsSupportingEvidence> ?evidenceUri .\n"
				+ "	?evidenceUri oa:hasBody ?evidenceBody .\n" + "   ?evidenceUri oa:hasTarget ?evidenceTgt . \n"
				+ "   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + "   ?evidenceSrc owl:sameAs ?realSrc  .\n" + "}\n" + "} WHERE { \n"
				+ "SERVICE <http://eculture2.cs.vu.nl:5020/sparql/> {\n"
				+ "   ?evidenceUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceAnnotation> . \n"
				+ "?evidenceSummaryUri <http://purl.org/swan/2.0/discourse-relationships/referencesAsSupportingEvidence> ?evidenceUri .\n"
				+ "	?evidenceUri oa:hasBody ?evidenceBody .\n" + "   ?evidenceUri oa:hasTarget ?evidenceTgt . \n"
				+ "   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + "   OPTIONAL { ?evidenceSrc owl:sameAs ?realSrc } .\n" + "}\n" + "}\n"
				+ "";
	}

	public String getSelectAllQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "SELECT * WHERE {"
				+ "   ?evidenceUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceAnnotation> . \n"
				+ "?evidenceSummaryUri <http://purl.org/swan/2.0/discourse-relationships/referencesAsSupportingEvidence> ?evidenceUri .\n"
				+ "	?evidenceUri oa:hasBody ?evidenceBody .\n" + "   ?evidenceUri oa:hasTarget ?evidenceTgt . \n"
				+ "   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + "   OPTIONAL { ?evidenceSrc owl:sameAs ?realSrc } .\n" + "}";
	}

	public String getSelectExampleQuery() {
		String evidenceSummaryUri = "http://aers.data2semantics.org/resource/annotation/2012-06-22T13:40:44/13e4bbbfd0005c6a6d0b93b10268f65c";
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n" +
				"SELECT DISTINCT ?realSrc ?evidenceSrc ?evidenceUri ?evidenceBody \n WHERE {" +
				"   ?evidenceUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceAnnotation> . \n" +
				"	<" + evidenceSummaryUri + "> <http://purl.org/swan/2.0/discourse-relationships/referencesAsSupportingEvidence> ?evidenceUri .\n" +
				"	?evidenceUri oa:hasBody ?evidenceBody .\n" + 
				"   ?evidenceUri oa:hasTarget ?evidenceTgt . \n" +
				"   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + 
				"   OPTIONAL { ?evidenceSrc owl:sameAs ?realSrc } .\n" +
				"}" ;
	}

	public String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

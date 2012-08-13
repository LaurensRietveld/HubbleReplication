package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query6  implements Query {

	public String getInsertQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "INSERT { GRAPH <http://eculture2.cs.vu.nl:5020/sparql/> {\n"
				+ "?evidenceSummaryUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceSummaryAnnotation> .\n"
				+ " 	?recommendationUri <http://aers.data2semantics.org/vocab/annotation/hasEvidenceSummary> ?evidenceSummaryUri .\n"
				+ "	?evidenceSummaryUri oa:hasBody ?evidenceSummaryBody .\n" + "   ?evidenceSummaryUri oa:hasTarget ?evidenceTgt . \n"
				+ "   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + "   ?evidenceSrc owl:sameAs ?realSrc.\n" + "\n" + "}} WHERE {   \n"
				+ "SERVICE <http://eculture2.cs.vu.nl:5020/sparql/> {\n"
				+ "?evidenceSummaryUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceSummaryAnnotation> .\n"
				+ " 	?recommendationUri <http://aers.data2semantics.org/vocab/annotation/hasEvidenceSummary> ?evidenceSummaryUri .\n"
				+ "	?evidenceSummaryUri oa:hasBody ?evidenceSummaryBody .\n" + "   ?evidenceSummaryUri oa:hasTarget ?evidenceTgt . \n"
				+ "   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + "   OPTIONAL { ?evidenceSrc owl:sameAs ?realSrc } .\n" + "}}\n" + "";
	}

	public String getSelectAllQuery() {
		return Helper.PREFIXES_ANNOTATIONS_SWAN
				+ "SELECT DISTINCT ?realSrc ?evidenceSrc ?evidenceSummaryBody ?evidenceSummaryUri \n WHERE {"
				+ "   ?evidenceSummaryUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceSummaryAnnotation> .\n "
				+ "	?recommendationUri <http://aers.data2semantics.org/vocab/annotation/hasEvidenceSummary> ?evidenceSummaryUri .\n"
				+ "	?evidenceSummaryUri oa:hasBody ?evidenceSummaryBody .\n" + "   ?evidenceSummaryUri oa:hasTarget ?evidenceTgt . \n"
				+ "   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + "   OPTIONAL { ?evidenceSrc owl:sameAs ?realSrc } .\n" + "}";
	}

	public String getSelectExampleQuery() {
		String recommendationUri = "http://aers.data2semantics.org/resource/annotation/2012-06-22T13:40:44/25e855bf1a657296936f69721cc9d1ad";
		return Helper.PREFIXES_ANNOTATIONS_SWAN + "\n" +
				"SELECT DISTINCT ?realSrc ?evidenceSrc ?evidenceSummaryBody ?evidenceSummaryUri \n WHERE {" +
				"   ?evidenceSummaryUri a  <http://aers.data2semantics.org/vocab/annotation/EvidenceSummaryAnnotation> .\n "+
				"	<" + recommendationUri + "> <http://aers.data2semantics.org/vocab/annotation/hasEvidenceSummary> ?evidenceSummaryUri .\n" +
				"	?evidenceSummaryUri oa:hasBody ?evidenceSummaryBody .\n" + 
				"   ?evidenceSummaryUri oa:hasTarget ?evidenceTgt . \n" +
				"   ?evidenceTgt oa:hasSource ?evidenceSrc . \n" + 
				"   OPTIONAL { ?evidenceSrc owl:sameAs ?realSrc } .\n" +
				"}" ;
	}

	public String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

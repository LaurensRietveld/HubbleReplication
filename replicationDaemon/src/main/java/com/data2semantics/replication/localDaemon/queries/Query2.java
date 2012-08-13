package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query2 {

	public static String getInsertQuery() {
		return Helper.PREFIXES_AERS
				+ "INSERT { GRAPH <http://eculture2.cs.vu.nl:5020/sparql/> {\n"
				+ "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientLabel.\n"
				+ "?patient patient:hasAge ?age.\n"
				+ "?patient rdfs:comment ?comment.\n"
				+ "?patient patient:usesMedication ?drug.?drug rdfs:label ?drug_label;skos:exactMatch ?drug_sameAs.?drug_sameAs <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n"
				+ "?patient patient:hasStatus ?status.\n"
				+ "?patient patient:hasMeasurement ?measurement.\n"
				+ "\n"
				+ "?patient patient:hasDiagnosis ?indication.\n"
				+ "\n"
				+ "?indication rdfs:label ?indication_label.\n"
				+ "\n"
				+ "\n"
				+ "?patient patient:hadRecentTreatment ?recentTreatment.\n"
				+ "?recentTreatment rdfs:label ?recentTreatment_label.\n"
				+ "\n"
				+ "?patient patient:hadPreviousIndication ?previousIndication.\n"
				+ "\n"
				+ "}} WHERE { \n"
				+ "SERVICE <http://eculture2.cs.vu.nl:5020/sparql/> {\n"
				+ "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientLabel.\n"
				+ "?patient patient:hasAge ?age.\n"
				+ "?patient rdfs:comment ?comment.\n"
				+ "OPTIONAL{?patient patient:usesMedication ?drug.?drug rdfs:label ?drug_label;skos:exactMatch ?drug_sameAs.?drug_sameAs <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .}.\n"
				+ "OPTIONAL{?patient patient:hasStatus ?status}.\n" + "OPTIONAL{?patient patient:hasMeasurement ?measurement}.\n"
				+ "OPTIONAL{\n" + "?patient patient:hasDiagnosis ?indication.\n" + "OPTIONAL{\n"
				+ "?indication rdfs:label ?indication_label.\n" + "}\n" + "}.\n" + "OPTIONAL{\n"
				+ "?patient patient:hadRecentTreatment ?recentTreatment.\n" + "?recentTreatment rdfs:label ?recentTreatment_label.\n"
				+ "}.\n" + "OPTIONAL{?patient patient:hadPreviousIndication ?previousIndication}.\n" + "\n" + "}}";
	}

	public static String getSelectAllQuery() {
		return Helper.PREFIXES_AERS
				+ "SELECT DISTINCT ?age \n"
				+ "?comment \n"
				+ "?status \n"
				+ "?measurement \n"
				+ "?indication \n"
				+ "?indication_label \n"
				+ "?recentTreatment \n"
				+ "?recentTreatment_label \n"
				+ "?previousIndication \n"
				+ "?drug \n"
				+ "?drug_label \n"
				+ "?drug_sameAs \n"
				+ "{\n"
				+ "?patient rdf:type patient:Patient.\n"
				+ "?patient rdfs:label ?patientLabel.\n"
				+ "?patient patient:hasAge ?age.\n"
				+ "?patient rdfs:comment ?comment.\n"
				+ "OPTIONAL{?patient patient:usesMedication ?drug.?drug rdfs:label ?drug_label;skos:exactMatch ?drug_sameAs.?drug_sameAs <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .}.\n"
				+ "OPTIONAL{?patient patient:hasStatus ?status}.\n" + "OPTIONAL{?patient patient:hasMeasurement ?measurement}.\n"
				+ "OPTIONAL{\n" + "?patient patient:hasDiagnosis ?indication.\n" + "OPTIONAL{\n"
				+ "?indication rdfs:label ?indication_label.\n" + "}\n" + "}.\n" + "OPTIONAL{\n"
				+ "?patient patient:hadRecentTreatment ?recentTreatment.\n" + "?recentTreatment rdfs:label ?recentTreatment_label.\n"
				+ "}.\n" + "OPTIONAL{?patient patient:hadPreviousIndication ?previousIndication}.\n" + "}";
	}

	public static String getSelectExampleQuery() {
		String patientId = "John Doe";
		return Helper.PREFIXES_AERS + "SELECT DISTINCT " +
				"?age \n" +
				"?comment \n" +
				"?status \n" +
				"?measurement \n" +
				"?indication \n" +
				"?indication_label \n" +
				"?recentTreatment \n" +
				"?recentTreatment_label \n" +
				"?previousIndication \n" +
				"?drug \n" +
				"?drug_label \n" +
				"?drug_sameAs \n" +
			"{\n" + 
				"?patient rdf:type patient:Patient.\n" + 
				"?patient rdfs:label '" + patientId + "'@en.\n" + 
				"?patient patient:hasAge ?age.\n" + 
				"?patient rdfs:comment ?comment.\n" + 
				"OPTIONAL{?patient patient:usesMedication ?drug.\n" +
					"?drug rdfs:label ?drug_label;\n" +
						"skos:exactMatch ?drug_sameAs.\n" +
					"?drug_sameAs <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n" +
//					"FILTER regex(str(?drug_sameAs), \"^http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB\", \"i\")\n" + 
				"}.\n" + 
				"OPTIONAL{?patient patient:hasStatus ?status}.\n" + 
				"OPTIONAL{?patient patient:hasMeasurement ?measurement}.\n" + 
				"OPTIONAL{\n" +
					"?patient patient:hasDiagnosis ?indication.\n" +
					"OPTIONAL{\n" + 
						"?indication rdfs:label ?indication_label.\n" +
					"}\n" +
				"}.\n" + 
				
				//Workaround to get 'hadRecentTreatment' from our own 4store. This SHOULD BE in the lld rdf, but isnt :(
				"OPTIONAL{\n" +
					"?patient patient:hadRecentTreatment ?recentTreatment.\n" +
					"?recentTreatment rdfs:label ?recentTreatment_label.\n" +
				"}.\n" + 
				"OPTIONAL{?patient patient:hadPreviousIndication ?previousIndication}.\n" + 
			"}\n" + 
			"";
	}

	public static String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

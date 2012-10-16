package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query5a  implements Query  {

	public String getInsertQuery() {
		return Helper.PREFIXES_AERS + "INSERT { GRAPH <" + Helper.ENDPOINT_FOR_SERVICE_CALL + "> {\n"
				+ "?indicationLabel :reaction_of ?report.\n" + "	?report :age ?age;\n" + "		:event_date ?eventDate;\n"
				+ "		:gender ?gender;\n" + "		:manufacturer ?manufacturer.\n" + "	?involvement :involved_in ?report;\n"
				+ "		:drug ?drug.\n" + "	?involvement :drug_role <http://aers.data2semantics.org/resource/drug/role/PS>.\n"
				+ "	?drug skos:exactMatch ?drugBankUri.\n"
				+ "   ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n"
				+ "	?drugBankUri rdfs:label ?drugLabel.\n" + "\n" + "}} WHERE { \n"
				+ "SERVICE <"+ Helper.ENDPOINT_FOR_SERVICE_CALL  +"> {\n" + "?indicationLabel :reaction_of ?report.\n"
				+ "	?report :age ?age;\n" + "		:event_date ?eventDate;\n" + "		:gender ?gender;\n" + "		:manufacturer ?manufacturer.\n"
				+ "	?involvement :involved_in ?report;\n" + "		:drug ?drug.\n"
				+ "	?involvement :drug_role <http://aers.data2semantics.org/resource/drug/role/PS>.\n"
				+ "	?drug skos:exactMatch ?drugBankUri.\n"
				+ "   ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n"
				+ "	?drugBankUri rdfs:label ?drugLabel.\n" + "\n" + "}}\n" + "";
	}

	public String getSelectAllQuery() {
		return Helper.PREFIXES_AERS + "SELECT DISTINCT * {\n"
				+ "	{?indicationLabel :reaction_of ?report.} UNION { ?indicationUri skos:exactMatch ?ind . ?ind :reaction_of ?report . }\n"
				+ "	?report :age ?age;\n" + "		:event_date ?eventDate;\n" + "		:gender ?gender;\n" + "		:manufacturer ?manufacturer.\n"
				+ "	?involvement :involved_in ?report;\n" + "		:drug ?drug.\n"
				+ "	?involvement :drug_role <http://aers.data2semantics.org/resource/drug/role/PS>.\n"
				+ "	?drug skos:exactMatch ?drugBankUri.\n"
				+ "   ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n"
				+ "	?drugBankUri rdfs:label ?drugLabel.\n" + "}\n" ;
	}

	public String getSelectExampleQuery() {
		String indicationLabel = "NEUTROPENIA";
		String indicationUri = "http://aers.data2semantics.org/resource/diagnosis/NEUTROPENIA";
		return Helper.PREFIXES_AERS + "\n" + 
				"SELECT DISTINCT \n" +
				"?report \n" +
				"?age \n" +
				"?eventDate \n" +
				"?gender \n" +
				"?manufacturer \n" +
				"?drug \n" +
				"?drugLabel \n" +
				"?drugBankUri \n" +
				"(\"" + indicationLabel + "\" AS ?indicationLabel) \n" +
				"{\n" + 
				"	{<" + indicationUri + "> :reaction_of ?report.} UNION { <" + indicationUri + "> skos:exactMatch ?ind . ?ind :reaction_of ?report . }\n" +
				"	?report :age ?age;\n" +
				"		:event_date ?eventDate;\n" +
				"		:gender ?gender;\n" +
				"		:manufacturer ?manufacturer.\n" +
				"	?involvement :involved_in ?report;\n" +
				"		:drug ?drug.\n" +
				"	?involvement :drug_role <http://aers.data2semantics.org/resource/drug/role/PS>.\n" +	
				"	?drug skos:exactMatch ?drugBankUri.\n" + 
				"   ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n" +
				"	?drugBankUri rdfs:label ?drugLabel.\n" +
//				"	FILTER regex(str(?drugBankUri), \"^http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB\", \"i\")\n" + 
				"}\n"
				;
	}

	public String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

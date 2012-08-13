package com.data2semantics.replication.localDaemon.queries;

import com.data2semantics.replication.localDaemon.Helper;

public class Query4 {

	public static String getInsertQuery() {
		return Helper.getInsertQuery(Helper.ENDPOINT_ECULTURE2, Helper.PREFIXES_AERS, "	?report :age ?age;\n"
				+ "		:event_date ?eventDate;\n" + "		:gender ?gender;\n" + "		:manufacturer ?manufacturer.\n"
				+ "	?involvement :involved_in ?report;\n" + "		:drug ?drugUri.\n" + "?drugUri	skos:exactMatch ?drugBankUri.\n"
				+ "   ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n"
				+ "	?drugBankUri rdfs:label ?drugLabel.\n");
	}

	public static String getSelectAllQuery() {
		return Helper.PREFIXES_AERS + "SELECT * {\n" + "	?report :age ?age;\n" + "		:event_date ?eventDate;\n"
				+ "		:gender ?gender;\n" + "		:manufacturer ?manufacturer.\n" + "	?involvement :involved_in ?report;\n"
				+ "		:drug ?drugUri.\n" + "?drugUri	skos:exactMatch ?drugBankUri.\n"
				+ "   ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n"
				+ "	?drugBankUri rdfs:label ?drugLabel.\n" + "}\n";
	}

	public static String getSelectExampleQuery() {
		String drugUri = "http://aers.data2semantics.org/resource/drug/AMBISOME";
		return Helper.PREFIXES_AERS + "\n" + 
				"SELECT DISTINCT \n" +
				"?report \n" +
				"?age \n" +
				"?eventDate \n" +
				"?gender \n" +
				"?manufacturer \n" +
				"(\"" + drugUri + "\" AS ?drug) \n" +
				"?drugLabel \n" +
				"?drugBankUri \n" +
				"{\n" +
				"	?report :age ?age;\n" +
				"		:event_date ?eventDate;\n" +
				"		:gender ?gender;\n" +
				"		:manufacturer ?manufacturer.\n" +
				"	?involvement :involved_in ?report;\n" +
				"		:drug <" + drugUri + ">.\n" +
				"	<" + drugUri + ">	skos:exactMatch ?drugBankUri.\n" + 
				"   ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/primaryAccessionNo> ?pan .\n" +
				"	?drugBankUri rdfs:label ?drugLabel.\n" +
//				"	FILTER regex(str(?drugBankUri), \"^http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugs/DB\", \"i\")\n" +
				"}\n" + 
				"LIMIT 100";
	}

	public static String getConstructQuery() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.data2semantics.replication.localDaemon;

import org.openjena.atlas.logging.Log;

import com.data2semantics.replication.localDaemon.queries.*;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.resultset.ResultSetCompare;

public class TestInsert {

	/**
	 * Extendible by removing "select" part from querybody, removing optional
	 * clauses (just the OPTIONAL{ and } part, not the BGP) from the insert part
	 * not using [] in insert part Dealing with unions: when there is a union x
	 * U y and some other stuff in the query, run the query once for x and the
	 * remainder, and once for y and the remainder
	 * 
	 * Problem: all eculture queries use variables from their own dataset for
	 * each of the queries. This makes it easy to replace by removing the java
	 * variable, and adding a sparql var instead. This is not possible anymore
	 * when a query (in this case to the lld endpoint) uses a java variable
	 * which value originates from another endpoint. Replacing the java var with
	 * a sparql var doesnt work anymore. Essentially we would need two service
	 * calls: one to retrieve the possible values for the sparql variable from
	 * endpoint 1, and another to execute the actual query using these values
	 * 
	 * Problem: we are storing things multiple times, as there is overlap in
	 * what the hubble sparql queries retrieve
	 * 
	 * Problem: hubble uses some queries with LIMIT100. This is bad practice,
	 * and results in different execution results on different triplestore (e.g.
	 * replicas)
	 * 
	 * Problem: query4 yields different number of results on replica and
	 * original... replica: 4700 triple, original: around 8000. When executing a
	 * DISTINCT query, the numbers are the same. Conclusion: the sesame service
	 * query only retrieve distinct values? Even more: the results of query 4 in
	 * darwin differ greatly... e.g. the query below
	 * 
	 * INSERT QUERY TO COPY STUFF PREFIX rdf:
	 * <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX skos:
	 * <http://www.w3.org/2004/02/skos/core#> PREFIX rdfs:
	 * <http://www.w3.org/2000/01/rdf-schema#> PREFIX owl:
	 * <http://www.w3.org/2002/07/owl#> PREFIX foaf:
	 * <http://xmlns.com/foaf/0.1/> PREFIX r4:
	 * <http://aers.data2semantics.org/vocab/> PREFIX ns3:
	 * <tag:eric@w3.org:2009/tmo/translator#> PREFIX ns4:
	 * <http://www.obofoundry.org/ro/ro.owl#> PREFIX ns1:
	 * <http://purl.org/cpr/0.75#> PREFIX :
	 * <http://aers.data2semantics.org/vocab/> PREFIX patient:
	 * <http://www.data2semantics.org/ontology/patient/> INSERT { GRAPH
	 * <http://eculture2.cs.vu.nl:5020/sparql/> { ?report :age ?age; :event_date
	 * ?eventDate; :gender ?gender; :manufacturer ?manufacturer. ?involvement
	 * :involved_in ?report; :drug ?drug. ?drug skos:exactMatch ?drugBankUri.
	 * ?drugBankUri <http://www4.wiwiss.fu-berlin.de/drugbank/resource/drugbank/
	 * primaryAccessionNo> ?pan . ?drugBankUri rdfs:label ?drugLabel. }} WHERE {
	 * SERVICE <http://eculture2.cs.vu.nl:5020/sparql/> { ?report :age ?age;
	 * :event_date ?eventDate; :gender ?gender; :manufacturer ?manufacturer.
	 * ?involvement :involved_in ?report; :drug ?drug. ?drug skos:exactMatch
	 * ?drugBankUri. ?drugBankUri
	 * <http://www4.wiwiss.fu-berlin.de/drugbank/resource
	 * /drugbank/primaryAccessionNo> ?pan . ?drugBankUri rdfs:label ?drugLabel.
	 * }} 
	 * 
	 * USING SELECT ABOVE Results (using regular java store): select on
	 * replica with a service call: 450 triples select on replica after the
	 * insert query (insert just for this drug!): 450 triples select on original
	 * (4store): 450 retrieved triples
	 * 
	 * Results (using regular java store): select on replica with a service
	 * call: 450 triples select on replica after the insert query (using the
	 * insert statement above): 1 triple select on original (4store): 450
	 * retrieved triples
	 * 
	 * USING DINSTINCT SELECT * for all drugs Results (using regular java
	 * store): select on replica with a service call: 4741 triples select on
	 * replica after the insert query (using the insert statement above): 4741
	 * triple select on original (4store): 4741 retrieved triples
	 * 
	 * 
	 * 
	 * SELECT QUERY, AS USED BY HUBBLE PREFIX rdf:
	 * <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX skos:
	 * <http://www.w3.org/2004/02/skos/core#> PREFIX rdfs:
	 * <http://www.w3.org/2000/01/rdf-schema#> PREFIX owl:
	 * <http://www.w3.org/2002/07/owl#> PREFIX foaf:
	 * <http://xmlns.com/foaf/0.1/> PREFIX r4:
	 * <http://aers.data2semantics.org/vocab/> PREFIX ns3:
	 * <tag:eric@w3.org:2009/tmo/translator#> PREFIX ns4:
	 * <http://www.obofoundry.org/ro/ro.owl#> PREFIX ns1:
	 * <http://purl.org/cpr/0.75#> PREFIX :
	 * <http://aers.data2semantics.org/vocab/> PREFIX patient:
	 * <http://www.data2semantics.org/ontology/patient/> SELECT DISTINCT ?report
	 * ?age ?eventDate ?gender ?manufacturer
	 * 
	 * ?drugLabel ?drugBankUri { ?report :age ?age; :event_date ?eventDate;
	 * :gender ?gender; :manufacturer ?manufacturer. ?involvement :involved_in
	 * ?report; :drug <http://aers.data2semantics.org/resource/drug/AMBISOME>.
	 * <http://aers.data2semantics.org/resource/drug/AMBISOME> skos:exactMatch
	 * ?drugBankUri. ?drugBankUri
	 * <http://www4.wiwiss.fu-berlin.de/drugbank/resource
	 * /drugbank/primaryAccessionNo> ?pan . ?drugBankUri rdfs:label ?drugLabel.
	 * } LIMIT 100
	 * 
	 * Tried Using CONSTRUCT instead (with the same graph pattern from the
	 * select). Construct executed on sesame contain redundant triples (1 and
	 * the same triple is shown 5 times). This explains the large differences
	 * between sesame and 4store
	 * 
	 * USING OWLIM: construct on replica with a service call: 35526 triples
	 * construct on replica after the insert query: 27280 triples construct on
	 * original (4store): retrieves 4269 triples...
	 * 
	 * USING regular java store construct on replica with a service call: 35526
	 * triples construct on replica after the insert query: 30050 triples
	 * construct on original (4store): retrieves 4269 triples...
	 * 
	 * 
	 * @param endpoint
	 * @param prefixes
	 * @param queryBody
	 * @return 
	 * @return
	 */
	
	public static void testSelectAll(Query query) {
		System.out.print("Select all: ");
		Helper.clearTriples(Helper.ENDPOINT_REPLICA_UPDATE);
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, query.getInsertQuery());
		ResultSet result1 = Helper.executeQuery(Helper.ENDPOINT_ECULTURE2, query.getSelectAllQuery());
		ResultSet result2 = Helper.executeQuery(Helper.ENDPOINT_REPLICA, query.getSelectAllQuery());
		Helper.compareResults(result1, result2);
//		System.out.println(ResultSetCompare.equalsByValue(result1, result2));
	}
	
	public static void testSelectExample(Query query) {
		System.out.print("Select example: ");
		Helper.clearTriples(Helper.ENDPOINT_REPLICA_UPDATE);
		Helper.executeUpdateQuery(Helper.ENDPOINT_REPLICA_UPDATE, query.getInsertQuery());
		ResultSet result1 = Helper.executeQuery(Helper.ENDPOINT_ECULTURE2, query.getSelectExampleQuery());
		ResultSet result2 = Helper.executeQuery(Helper.ENDPOINT_REPLICA, query.getSelectExampleQuery());
		Helper.compareResults(result1, result2);
//		System.out.println(ResultSetCompare.equalsByValue(result1, result2));
	}
	
	public static void test(Query query) {
		System.out.println(query.getClass().getName() + ": ");
		testSelectAll(query);
		testSelectExample(query);
		System.out.println("");
	}
	
	public static void main(String[] args) {
		Log.setLog4j();
//		TestInsert.test(new Query1());//ok
//		TestInsert.test(new Query2());//ok
//		TestInsert.test(new Query3());//ok
//		TestInsert.test(new Query4());//wrong
//		TestInsert.test(new Query5a());//wrong (only select example)
//		TestInsert.test(new Query5b());//wrong (only select example)
//		TestInsert.test(new Query6());//ok
//		TestInsert.test(new Query7());//wrong
//		TestInsert.test(new Query8());//wrong
		

	}

}

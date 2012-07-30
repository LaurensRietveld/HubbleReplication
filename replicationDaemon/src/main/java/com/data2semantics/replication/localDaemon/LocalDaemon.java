package com.data2semantics.replication.localDaemon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.restlet.Client;
import org.restlet.Response;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * Hello world!
 *
 */
public class LocalDaemon 
{
	public static boolean isReachable(String url) {
		boolean available;
		try{
		    final URLConnection connection = new URL(url).openConnection();
		    connection.setConnectTimeout(300);
		    connection.connect();
		    available = true;
		} catch(final MalformedURLException e){
		    throw new IllegalStateException("Bad URL: " + url, e);
		} catch(final IOException e){
		    available = false;
		}
		return available;
	}
	
	
	public static void executeQuery() {
		String endpoint = "";
		//endpoint = "http://eculture2.cs.vu.nl:5020/sparql/";
		//endpoint = "http://localhost:8080/sparqlproxy/http://eculture2.cs.vu.nl:5020/sparql/";
		//endpoint = "http://localhost:9080/sparqlProxy/http://eculture2.cs.vu.nl:5020/sparql/";
		Query query = QueryFactory.create("SELECT * {?x ?f ?g} LIMIT 10");
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService(endpoint, query);
		ResultSet resultSet = queryExecution.execSelect();
		System.out.println(ResultSetFormatter.asText(resultSet));
		
	}
    public static void main(String[] args) throws IOException, InterruptedException
    {
    	//LocalDaemon.executeQuery();
    	System.out.println(".");
        System.out.println(LocalDaemon.isReachable("http://eculture2.cs.vu.nl:5020"));
        //System.out.println(LocalDaemon.isReachable2("http://eculture2.cs.vu.nl:5020/sparql"));
    }
}

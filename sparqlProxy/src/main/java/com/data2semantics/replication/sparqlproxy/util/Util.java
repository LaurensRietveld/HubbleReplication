package com.data2semantics.replication.sparqlproxy.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import com.data2semantics.replication.sparqlproxy.EntryPoint;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

import freemarker.template.Configuration;

public class Util {
	public static Representation getErrorPage(EntryPoint entryPoint,
			String error) {
		entryPoint.getLogger().severe(error);
		Configuration configuration = entryPoint.getFMConfiguration();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error",
				error.replace("\t", "&nbsp;&nbsp;&nbsp;").replace("\n", "<br>"));
		return new TemplateRepresentation("error.ftl", configuration, map,
				MediaType.TEXT_HTML);
	}

	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dt = new Date();
		return sdf.format(dt); // formats to 09/23/2009 13:53:28
	}

	/**
	 * Execute query
	 * 
	 * @param queryString
	 */
	public static ResultSet query(String endpoint, String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService(endpoint, query);
		ResultSet results = queryExecution.execSelect();
		return results;
	}

	public static boolean isReachable(String uri) throws IOException {
		InetAddress Address = InetAddress.getByName(uri); 
		return Address.isReachable(100);
	}
}
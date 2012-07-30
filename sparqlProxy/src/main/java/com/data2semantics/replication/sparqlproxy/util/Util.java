package com.data2semantics.replication.sparqlproxy.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import com.data2semantics.replication.sparqlproxy.EntryPoint;

import freemarker.template.Configuration;

public class Util {
	public static Representation getErrorPage(EntryPoint entryPoint, String error) {
		entryPoint.getLogger().severe(error);
		Configuration configuration = entryPoint.getFMConfiguration();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", error.replace("\t", "&nbsp;&nbsp;&nbsp;").replace("\n", "<br>"));
		return new TemplateRepresentation("error.ftl", configuration, map, MediaType.TEXT_HTML);
	}

	public static boolean isReachable(String uri) throws IOException, InterruptedException {
		boolean available;
		try{
		    final URLConnection connection = new URL(uri).openConnection();
		    connection.setConnectTimeout(300);
		    connection.connect();
		    available = true;
		} catch(final MalformedURLException e){
		    throw new IllegalStateException("Bad URL: " + uri, e);
		} catch(final IOException e){
		    available = false;
		}
		return available;
	}
}
package com.data2semantics.replication.sparqlproxy.redirectors;

import java.io.IOException;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.Reference;
import org.restlet.routing.Redirector;

import com.data2semantics.replication.sparqlproxy.util.Util;

public class EndPointRedirector extends Redirector {
	private static String ENDPOINT_LOCAL = "http://localhost:8080/openrdf-workbench/repositories/localrep/query";
	private String endpointUri = "";
	
	public EndPointRedirector(Context context, String targetTemplate) {
		super(context, targetTemplate);
	}

	public EndPointRedirector(Context context, String targetTemplate, int modeServerOutbound) {
		super(context, targetTemplate, modeServerOutbound);
	}
	
	@Override
	protected Reference getTargetRef(Request request, Response response) throws RedirectException {
		Reference reference = super.getTargetRef(request, response);
		endpointUri = reference.toString();
		if (!endpointUri.startsWith("http://")) {
			endpointUri = "http://" + endpointUri;
		}
		try {
			decideUri();
			appendQuery(request);
		} catch (Exception e) {
			throw new RedirectException(e.getMessage());
		}
		
		return new Reference(endpointUri);
	}

	private void decideUri() throws IOException, InterruptedException {
		if (!Util.isReachable(endpointUri)) {
			endpointUri = ENDPOINT_LOCAL;
		}
		getLogger().severe("Redirecting to: " + endpointUri);
	}
	
	private void appendQuery(Request request) throws IOException {
		if (request.getResourceRef().hasQuery()) {
			Form form = request.getResourceRef().getQueryAsForm();
			String queryString = rewriteQuery(form.getFirstValue("query"));
			getLogger().severe("queryString: " + queryString);
			form.set("query", queryString);
			endpointUri += "?" + form.getQueryString();
			getLogger().severe("result uri: " + endpointUri);
		}
	}
	
	private String rewriteQuery(String queryString) {
		String pattern = "(.*SELECT.+)\\s*(WHERE|)(\\s*\\{.*)";
		String replacePattern = "$1 FROM <http://eculture2\\.cs\\.vu\\.nl:5020/sparql/> $3";
		queryString = queryString.replaceAll(pattern, replacePattern);
		return queryString;
	}
}

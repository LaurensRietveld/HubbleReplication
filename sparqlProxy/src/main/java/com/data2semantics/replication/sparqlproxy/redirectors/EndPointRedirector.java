package com.data2semantics.replication.sparqlproxy.redirectors;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Reference;
import org.restlet.routing.Redirector;

public class EndPointRedirector extends Redirector {

	public EndPointRedirector(Context context, String targetTemplate) {
		super(context, targetTemplate);
	}

	public EndPointRedirector(Context context, String targetTemplate, int modeServerOutbound) {
		super(context, targetTemplate, modeServerOutbound);
	}
	@Override
	protected Reference getTargetRef(Request request, Response response) {
		Reference reference = super.getTargetRef(request, response);
		String rr = reference.toString();
		getLogger().severe("Form: " + request.getResourceRef().getQuery());
		rr += "?" + request.getResourceRef().getQuery(true);
		return new Reference(rr);
	}
}

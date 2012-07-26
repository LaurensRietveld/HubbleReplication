package com.data2semantics.replication.sparqlproxy.filters;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Status;
import org.restlet.routing.Filter;

public class EndPointFilter extends Filter {

	public EndPointFilter(Context context, Restlet next) {
		super(context, next);
	}

	@Override
	protected int beforeHandle(Request request, Response response) {
		String rr = request.getResourceRef().getRemainingPart(true);
		
		if (rr.length() == 0) {
			response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "No SPARQL endpoint passed as parameter");
			return Filter.STOP;
		}
		
		if (!request.getResourceRef().hasQuery()) {
			response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "No SPARQL query provided");
			return Filter.STOP;
		}

		return Filter.CONTINUE;
	}

}
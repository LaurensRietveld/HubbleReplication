package com.data2semantics.replication.sparqlproxy.filters;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.routing.Filter;

public class EndPointFilter extends Filter {

	public EndPointFilter(Context context, Restlet next) {
		super(context, next);
	}

	
	//TODO: add checks here (valid endpoint, query and stuff)
	@Override
	protected int beforeHandle(Request request, Response response) {
//		Form queryParams = request.getResourceRef().getQueryAsForm();
//		
//		String rr = request.getResourceRef().getRemainingPart(true);
//		if (!IMG_PATTERN.matcher(rr).find()) {
//			response.setStatus(CLIENT_ERROR_UNSUPPORTED_MEDIA_TYPE,
//					"Not an image type");
//			return Filter.STOP;
//		}

		return Filter.CONTINUE;
	}
	
				
}
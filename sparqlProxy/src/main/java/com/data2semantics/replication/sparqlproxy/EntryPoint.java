package com.data2semantics.replication.sparqlproxy;

import java.util.Map;

import freemarker.template.Configuration;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Method;
import org.restlet.ext.freemarker.ContextTemplateLoader;
import org.restlet.representation.Representation;
import org.restlet.routing.Redirector;
import org.restlet.routing.Router;
import org.restlet.routing.TemplateRoute;
import org.restlet.routing.Variable;
import com.data2semantics.replication.sparqlproxy.filters.EndPointFilter;
import com.data2semantics.replication.sparqlproxy.redirectors.EndPointRedirector;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class EntryPoint extends Application {
	private Configuration fmConfiguration; // Freemarker Configuration
	private Config config;// Typesafe config

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		getLogger().severe("In entrypoint");
		loadConfigurations();
		// Create a router Restlet that routes each call
		Restlet router = createRouter();

		// Allow Accept-parameters to override Accept-headers:
		router = new EndPointFilter(getContext(), router);

		return router;
	}

	@Override
	public void start() throws Exception {
		setStatusService(new ErrorStatusService(this));
		super.start();
	}

	/**
	 * Returns the Freemarker's configuration.
	 * 
	 * @return The Freemarker's configuration.
	 */
	public Configuration getFMConfiguration() {
		return fmConfiguration;
	}

	/**
	 * Get the Typesafe config object
	 * 
	 * @return
	 */
	public Config getConfig() {
		return config;
	}

	private Restlet createRouter() {
		Router router = new Router(getContext());
		Redirector redirector = new EndPointRedirector(getContext(), "{uriPath}", Redirector.MODE_CLIENT_TEMPORARY);
		
		TemplateRoute route = router.attach("/{uriPath}", redirector);
		Map<String, Variable> routeVariables = route.getTemplate().getVariables();
		//The form part is missing here... Need to append this again in the EndpointRedirector class
		routeVariables.put("uriPath", new Variable(Variable.TYPE_ALL)); 
		// System.out.println(Util.isReachable("http://www.google.com"));
		
		//router.attachDefault(new Redirector(getContext(), "war:///index.html"));
		return router;
	}

	/**
	 * Load both the typesafe config, and the freemarker template config
	 */
	private void loadConfigurations() {
		// Load typesafe config
		Restlet client = getContext().getClientDispatcher();
		String configFile = "war:///WEB-INF/config/config.conf";
		try {
			Response response = client.handle(new Request(Method.GET, configFile));
			Representation rep = response.getEntity();
			this.config = ConfigFactory.parseString(rep.getText());
		} catch (Exception e) {
			getLogger().severe("Unable to load config file " + configFile + ": " + e.getMessage());
		}

		// Load freemarker config
		fmConfiguration = new Configuration();
		fmConfiguration.setTemplateLoader(new ContextTemplateLoader(getContext(), getConfig().getString("master.restlet.templatesDir")));
	}

}
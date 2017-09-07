package com.orders.server;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Jetty {

	private static final int PORT = 8080;

	private static final String CONTEXT_PATH = "/";
	private static final String CONFIG_LOCATION_PACKAGE = "com.orders.server.config";
	private static final String MAPPING_URL = "/";
	private static final String WEBAPP_DIRECTORY = "webapp";

	public static void main(String[] args) throws Exception {
		new Jetty().startJetty(PORT);
	}

	private void startJetty(int port) throws Exception {

		System.out.println(String.format("Starting server at port %s", port));
		Server server = new Server(port);

		server.setHandler(getServletContextHandler());

		addRuntimeShutdownHook(server);

		server.start();
		System.out.println(String.format("Server started at port %s", port));
		server.join();
	}

	private static ServletContextHandler getServletContextHandler()
			throws IOException {
		ServletContextHandler contextHandler = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		contextHandler.setErrorHandler(null);

		String webapp = new ClassPathResource(WEBAPP_DIRECTORY).getURI().toString();
		
		contextHandler.setResourceBase(webapp);
		contextHandler.setContextPath(CONTEXT_PATH);

		WebApplicationContext webAppContext = getWebApplicationContext();
		DispatcherServlet dispatcherServlet = new DispatcherServlet(
				webAppContext);
		ServletHolder springServletHolder = new ServletHolder("mvc-dispatcher",
				dispatcherServlet);
		contextHandler.addServlet(springServletHolder, MAPPING_URL);
		contextHandler
				.addEventListener(new ContextLoaderListener(webAppContext));

		return contextHandler;
	}

	private static WebApplicationContext getWebApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION_PACKAGE);
		return context;
	}

	private static void addRuntimeShutdownHook(final Server server) {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				if (server.isStarted()) {
					server.setStopAtShutdown(true);
					try {
						server.stop();
					} catch (Exception e) {
						System.out
								.println("Error while stopping jetty server: "
										+ e.getMessage());
					}
				}
			}
		}));
	}

}

package com.journaldev.mongodb.listener;

import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mongodb.MongoClient;

@WebListener
public class MongoDBContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		MongoClient mongo = (MongoClient) sce.getServletContext()
							.getAttribute("MONGO_CLIENT");
		mongo.close();
		System.out.println("MongoClient closed successfully");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			//
			// The following variables are provided by the Kubernetes "mongod" service.
			//
			System.out.println("MongoClient init started.");
			System.out.println("MONGODB_SERVICE_HOST = " + System.getenv("MONGODB_SERVICE_HOST"));
			System.out.println("MONGODB_SERVICE_PORT = " + System.getenv("MONGODB_SERVICE_PORT"));
			String mongodb_user = System.getenv("MONGODB_SERVICE_PORT");
			System.out.println("MONGODB_USER = " + mongodb_user);
			System.out.println("Integer.parseInt = " + Integer.parseInt(System.getenv("MONGODB_SERVICE_PORT")));
			ServletContext ctx = sce.getServletContext();
			MongoClient mongo = new MongoClient(System.getenv("MONGODB_SERVICE_HOST"), Integer.parseInt(System.getenv("MONGODB_SERVICE_PORT")));
			// ctx.getInitParameter("MONGODB_HOST"), 
			// System.getenv("MONGOD_SERVICE_HOST"),
			// Integer.parseInt(ctx.getInitParameter("MONGODB_SERVICE_PORT")));
			System.out.println("MongoClient initialized successfully");
			sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
		} catch (UnknownHostException e) {
			throw new RuntimeException("MongoClient init failed");
		}
	}

}

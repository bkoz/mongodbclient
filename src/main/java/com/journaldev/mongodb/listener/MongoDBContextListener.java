package com.journaldev.mongodb.listener;

import java.net.UnknownHostException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.mongodb.MongoClient;
import com.mongodb.DB;

@WebListener
public class MongoDBContextListener implements ServletContextListener {

  private DB mongoDB;

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
      // Variables provided by the Kubernetes "mongod" service.
      //
      System.out.println("MongoClient init started.");
      System.out.println("MONGODB_SERVICE_HOST = " + System.getenv("MONGODB_SERVICE_HOST"));
      System.out.println("MONGODB_SERVICE_PORT = " + System.getenv("MONGODB_SERVICE_PORT"));
      //
      // Environment variables injected into the deployment config by OpenShift.
      //
      String mongodb_user = System.getenv("MONGODB_USER");
      System.out.println("MONGODB_USER = " + System.getenv("MONGODB_USER"));
      String mongodb_password = System.getenv("MONGODB_PASSWORD");
      System.out.println("MONGODB_PASSWORD = " + mongodb_password);
      String mongodb_database = System.getenv("MONGODB_DATABASE");
      System.out.println("MONGODB_DATABASE = " + mongodb_database);
      System.out.println("Integer.parseInt = " + Integer.parseInt(System.getenv("MONGODB_SERVICE_PORT")));
      ServletContext ctx = sce.getServletContext();
      // MongoCredential	mongoCredential = new createCredential("userWTW", "sampledb", "IxD3dSgnnWLl6Kv3");
      // System.out.println("Credentials: " + mongoCredential.getUserName());
      MongoClient mongo = new MongoClient(System.getenv("MONGODB_SERVICE_HOST"), Integer.parseInt(System.getenv("MONGODB_SERVICE_PORT")));
      //
      // Variables injected from the web.xml file
      //
      System.out.println("Variables defined in web.xml:");
      System.out.println("MONGODB_HOST = " + ctx.getInitParameter("MONGODB_HOST"));
      // System.getenv("MONGOD_SERVICE_HOST")
      // Integer.parseInt(ctx.getInitParameter("MONGODB_SERVICE_PORT")));
      // 
      // This check does not work
      //
      if (mongo != null) {
        System.out.println("MongoClient initialized successfully");
      } else {
        System.out.println("MongoClient: ERROR initializing mongodb!");
      }
      mongoDB = mongo.getDB("journaldev");
      if (mongoDB.authenticate("user", "password".toCharArray()) == false) {
        System.out.println("Failed to authenticate DB ");
      }
      sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
    } catch (UnknownHostException e) {
      e printStackTrace();
      throw new RuntimeException("MongoClient init failed");
    } catch (MongoException e) {
       e printStackTrace();
    }
  }

}

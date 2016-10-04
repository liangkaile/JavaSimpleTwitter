package com.kaile.rest.dal;

import java.util.ResourceBundle;
import org.mongodb.morphia.Datastore;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;

public class MongoDBHelper {

    private static MongoClient mongoSingleton = null;
    private static Morphia morphia = new Morphia();

    private static MongoClient getMongoClient() {
        //Double-Checked Locking idiom
        if (mongoSingleton == null) {

            synchronized (MongoDBHelper.class) {
                if (mongoSingleton == null) {
                    ResourceBundle bundle = ResourceBundle.getBundle("mongodb");
                    String host = bundle.getString("host");
                    int port = Integer.parseInt(bundle.getString("port"));

                    mongoSingleton = new MongoClient(host, port);
                }
            }
        }
        return mongoSingleton;
    }

    public static Datastore getMongoDataStore() {
        MongoClient mongoClient = MongoDBHelper.getMongoClient();

        ResourceBundle bundle = ResourceBundle.getBundle("mongodb");
        String datastore = bundle.getString("datastore");
        return morphia.createDatastore(mongoClient, datastore);
    }

    public static Morphia getMorphia() {
        return morphia;
    }
}
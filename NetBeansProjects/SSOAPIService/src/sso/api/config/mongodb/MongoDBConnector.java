/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.config.mongodb;

import sso.api.configuration.ConfigHelper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bson.Document;

/**
 *
 * @author longmd
 */
public class MongoDBConnector {

	private static MongoDBConnector instance = null;
	public static final FindOneAndUpdateOptions foauoReturnAfter = new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER);
	public static final FindOneAndUpdateOptions foauoReturnBefore = new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.BEFORE);
	public static final UpdateOptions upsertOptions = new UpdateOptions().upsert(true);
	private MongoClient mongodbClient = null;
	private MongoDatabase mongodbDatabase = null;
	private Map<String, MongoCollection<Document>> mapObject2Collection;

	public static MongoDBConnector getInstance() {
		if (instance == null) {
			instance = new MongoDBConnector();
			instance.initMongoClient();
		}
		return instance;
	}

	private List<ServerAddress> getListServerAddress() {
		String str = ConfigHelper.getParamString("mongodb", "host_port", "");
		String[] listHostPorts = str.split(",");
		List<ServerAddress> listServers = new ArrayList<>();
		for (String hostport : listHostPorts) {
			String[] params = hostport.split(":");
			listServers.add(new ServerAddress(params[0], Integer.valueOf(params[1])));
		}
		return listServers;
	}

	private void initMongoClient() {
		if (mongodbClient == null) {
			//For mongodb's standalone
//			ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);

			//For mongodb's cluster
			List<ServerAddress> listServers = getListServerAddress();

			MongoClientOptions.Builder optionBuilder = new MongoClientOptions.Builder()
					.connectTimeout(ConfigHelper.getParamInt("mongodb", "connect_timeout"))
					.socketTimeout(ConfigHelper.getParamInt("mongodb", "socket_timeout"))
					.maxWaitTime(ConfigHelper.getParamInt("mongodb", "max_waittime"))
					.serverSelectionTimeout(ConfigHelper.getParamInt("mongodb", "server_selection_timeout"));
			mongodbClient = new MongoClient(listServers, optionBuilder.build());
			mongodbDatabase = mongodbClient.getDatabase(ConfigHelper.getParamString("mongodb", "database_name", ""));
			mapObject2Collection = new ConcurrentHashMap<>();
		}
	}

	public MongoDatabase getMongoDatabase() {
		return mongodbDatabase;
	}
	
	public DBCollection getCollect(String object) {
		DB db = mongodbClient.getDB(ConfigHelper.getParamString("mongodb", "database_name", ""));
		DBCollection collection = db.getCollection(object);
		return collection;
	}

	public MongoCollection<Document> getCollection(String object) {
		if (!mapObject2Collection.containsKey(object)) {
			mapObject2Collection.put(object, mongodbDatabase.getCollection(object));
		}
		return mapObject2Collection.get(object);
	}
}

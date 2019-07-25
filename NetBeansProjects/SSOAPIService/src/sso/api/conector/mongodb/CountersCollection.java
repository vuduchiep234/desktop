/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.conector.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.Updates;
import sso.api.config.mongodb.MongoDBConnector;
import sso.api.configuration.Configuration;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author longmd
 */
public class CountersCollection {
	private static CountersCollection instance = null;
	MongoCollection<Document> collection;
	
	public synchronized static CountersCollection getInstance() {
		if (instance == null){
			instance = new CountersCollection();
			instance.collection = MongoDBConnector.getInstance().getCollection("Counters");
		}
		return instance;
	}
	
	public synchronized Long getNextValue(String key){
		try{
			Bson filter = Filters.eq("_id", key);
			Bson update = Updates.inc("value", 1L);
			Document doc = collection.findOneAndUpdate(filter, update, new FindOneAndUpdateOptions().upsert(true));
                        if(doc == null){
                            return (long)1;
                        }
			return doc.getLong("value") + 1;
		}
		catch (Exception ex){
			ExceptionsCollection.getInstance().addException(
					Configuration.SERVICE_NAME,
					ex.getStackTrace()[0].toString(),
					ex.toString());
			return -1L;
		}
	}
}

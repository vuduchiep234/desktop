/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.conector.mongodb;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import sso.api.config.mongodb.MongoDBConnector;
import sso.api.configuration.Configuration;
import sso.api.model.DataResponse;
import sso.api.model.SessionUser;
import sso.api.model.User;

/**
 *
 * @author anbq
 */
public class UserMongoDBConllection {

    private static UserMongoDBConllection instance = null;
    private static final String objectName = "User";
    private static final String objectNameSession = "SessionUser";
    private final JsonWriterSettings settings = JsonWriterSettings.builder()
            .outputMode(JsonMode.RELAXED)
            .objectIdConverter((value, writer) -> writer.writeString(value.toHexString()))
            .build();

//	private final UpdateOptions upsertOption = new UpdateOptions().upsert(true);
    public synchronized static UserMongoDBConllection getInstance() {
        if (instance == null) {
            instance = new UserMongoDBConllection();
        }
        return instance;
    }

    public User getUserByField(String fileName,String userName) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectName);
            Document document = collection.find(Filters.eq(fileName, userName)).first();
            Gson gson = new Gson();
            User user = gson.fromJson(document.toJson(), User.class);
            return user;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
        }
        return null;
    }
    public User getUserByField(String fileName,int userName) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectName);
            Document document = collection.find(Filters.eq(fileName, userName)).first();
            Gson gson = new Gson();
            User user = gson.fromJson(document.toJson(), User.class);
            return user;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
        }
        return null;
    }

    public int addSession(SessionUser session) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(session);
            Document document = Document.parse(json);
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectName);
            collection.insertOne(document);
            return document.getInteger("id");
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
        }

        return -1;
    }
    public SessionUser getSessionUserBySessionKey(String sessionKey) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(objectNameSession);
            Document document = collection.find(Filters.eq("sessionKey", sessionKey)).first();
            Gson gson = new Gson();
            SessionUser sessionUser = gson.fromJson(document.toJson(), SessionUser.class);
            return sessionUser;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
        }
        return null;
    }
}

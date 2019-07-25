/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.conector.mongodb;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.util.JSON;
import sso.api.config.mongodb.MongoDBConnector;
import sso.api.configuration.Configuration;
import sso.api.model.DataResponse;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

/**
 *
 * @author longmd
 */
public class ObjectMongoDBCollections {

    private static ObjectMongoDBCollections instance = null;
    private final JsonWriterSettings settings = JsonWriterSettings.builder()
            .outputMode(JsonMode.RELAXED)
            .objectIdConverter((value, writer) -> writer.writeString(value.toHexString()))
            .build();

//	private final UpdateOptions upsertOption = new UpdateOptions().upsert(true);
    public synchronized static ObjectMongoDBCollections getInstance() {
        if (instance == null) {
            instance = new ObjectMongoDBCollections();
        }
        return instance;
    }

    public DataResponse create(String object, Document document) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            collection.insertOne(document);
            return new DataResponse(
                    new Document("id", document.get("_id")).toJson(settings),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse get(String object, Long _id) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            return new DataResponse(
                    collection.find(Filters.eq("_id", _id)).first().toJson(settings),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getByCondition(String object, Document document) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            BasicDBList list = new BasicDBList();

            return new DataResponse(
                    collection.find(document).first().toJson(settings),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getField(String object, Long _id, String fieldname) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            Document document = collection
                    .find(new BasicDBObject("_id", _id))
                    .projection(Projections.fields(Projections.include(fieldname), Projections.excludeId())).first();
            return new DataResponse(
                    document.toJson(),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse update(String object, Long _id, Document document) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            collection.findOneAndUpdate(Filters.eq("_id", _id), new Document("$set", document));
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse delete(String object, Long _id) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            collection.deleteOne(Filters.eq("_id", _id));
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse deleteByCondition(String object, Document document) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            collection.deleteMany(Filters.and(document));
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getList(String object, int numberOfSkip, int numberOfLimit) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            BasicDBList list = new BasicDBList();

            MongoCursor<Document> cursor = collection.find().skip(numberOfSkip).limit(numberOfLimit).iterator();
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }

            return new DataResponse(
                    JSON.serialize(list),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getListByField(String object, String fieldname, int numberOfSkip, int numberOfLimit) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            BasicDBList list = new BasicDBList();

            MongoCursor<Document> cursor = collection.find().skip(numberOfSkip).limit(numberOfLimit).projection(Projections.fields(Projections.include(fieldname), Projections.excludeId())).iterator();
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }

            return new DataResponse(
                    JSON.serialize(list),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getListByCondition(String object, Document document, int numberOfSkip, int numberOfLimit) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            BasicDBList list = new BasicDBList();

            MongoCursor<Document> cursor = collection.find(document).skip(numberOfSkip).limit(numberOfLimit).iterator();
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }

            return new DataResponse(
                    JSON.serialize(list),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getListField(String object, Document fieldNames, int numberOfSkip, int numberOfLimit) {
        try {
            MongoCollection<Document> collection = MongoDBConnector.getInstance().getCollection(object);
            BasicDBList list = new BasicDBList();

            MongoCursor<Document> cursor = collection.find().skip(numberOfSkip).limit(numberOfLimit).projection(fieldNames).iterator();
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }

            return new DataResponse(
                    JSON.serialize(list),
                    DataResponse.DataType.JSON_STR,
                    false
            );
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
}

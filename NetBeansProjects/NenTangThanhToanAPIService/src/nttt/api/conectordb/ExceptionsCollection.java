/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.conectordb;

import nttt.api.configuration.Configuration;
import nttt.api.model.DataResponse;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author anbq
 */
public class ExceptionsCollection {

    private static ExceptionsCollection instance = null;
    private final JsonWriterSettings settings = JsonWriterSettings.builder()
            .outputMode(JsonMode.RELAXED)
            .objectIdConverter((value, writer) -> writer.writeString(value.toHexString()))
            .build();
    private static SessionFactory factory;
    private Transaction tx = null;
    private Session session = null;

    public synchronized static ExceptionsCollection getInstance() {
        if (instance == null) {
            instance = new ExceptionsCollection();
        }
        return instance;
    }

    public DataResponse addException(String objectName, String objectClass,String message) {
        try {
           
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    
}

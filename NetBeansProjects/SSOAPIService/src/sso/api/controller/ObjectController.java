/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.controller;

import sso.api.conector.mongodb.ExceptionsCollection;
import sso.api.configuration.Configuration;
import sso.api.model.DataResponse;
import sso.api.server.ServletUtil;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import sso.api.service.ObjectService;

/**
 *
 * @author anbq
 */
public class ObjectController extends Controller {

    public ObjectController() {
        rootPath = "/";
    }

    @RouteInfo(method = "post", path = "/:object")
    public Route insert() {
        return (Request request, Response response) -> {

            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                String json = request.body();
                return ObjectService.getInstance().create(object, json);

            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "put", path = "/:object/:id")
    public Route update() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                Long id = Long.valueOf(request.params(":id"));
                String json = request.body();

                return ObjectService.getInstance().update(object, id, json);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "delete", path = "/:object/:id")
    public Route delete() {
        return (Request request, Response response) -> {
            Transaction tx = null;
            Session session = null;
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                String sID = String.valueOf(request.params(":id"));

                return ObjectService.getInstance().delete(object, sID);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        };
    }

    @RouteInfo(method = "delete", path = "/:object")
    public Route deleteObjectByCondition() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));

                String json = request.body();

                return ObjectService.getInstance().deleteByCondition(object, json);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "get", path = "/:object/:id")
    public Route getObject() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                Long id = Long.valueOf(request.params(":id"));

                return ObjectService.getInstance().get(object, id);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/:object/getObjectByCondition")
    public Route getObjectByCondition() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));

                String json = request.body();

                return ObjectService.getInstance().getByCondition(object, json);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/:object/getListObject")
    public Route getListObject() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");

                return ObjectService.getInstance().getList(object, numberOfSkip, numberOfLimit);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/:object/getListObjectByCondition")
    public Route getListObjectByCondition() {
        return (Request request, Response response) -> {
            Transaction tx = null;
            Session session = null;
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");

                String json = request.body();
                return ObjectService.getInstance().getListByCondition(object, json, numberOfSkip, numberOfLimit);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        };
    }

}


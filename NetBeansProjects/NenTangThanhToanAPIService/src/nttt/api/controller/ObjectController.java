/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.controller;

import nttt.api.conectordb.ExceptionsCollection;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import nttt.api.configuration.Configuration;
import nttt.api.model.DataResponse;
import nttt.api.server.ServletUtil;
import nttt.api.service.ObjectService;
import org.apache.log4j.Logger;

/**
 *
 * @author lannt54
 */
public class ObjectController extends Controller {
    Logger logger = Logger.getLogger(NapasController.class.getName());
    @RouteInfo(method = "get", path = "/:object")
    public Route getList() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");

                return ObjectService.getInstance().getList(object, numberOfSkip, numberOfLimit);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
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
                int id = Integer.valueOf(request.params(":id"));

                return ObjectService.getInstance().get(object, id);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
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
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    @RouteInfo(method = "post", path = "/:object/createList")
    public Route insertList() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                String json = request.body();
                return ObjectService.getInstance().createList(object, json);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
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
                int id = Integer.valueOf(request.params(":id"));
                String json = request.body();
                return ObjectService.getInstance().update(object, id, json);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
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
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int id = Integer.valueOf(request.params(":id"));

                return ObjectService.getInstance().delete(object, id);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "delete", path = "/:object")
    public Route deleteByCondition() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));

                String json = request.body();

                return ObjectService.getInstance().deleteByCondition(object, json);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/:object/get")
    public Route getByCondition() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));

                String json = request.body();

                return ObjectService.getInstance().getByCondition(object, json);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/:object/getList")
    public Route getListByCondition() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");
                String json = request.body();
                return ObjectService.getInstance().getListByCondition(object, json, numberOfSkip, numberOfLimit);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    
    @RouteInfo(method = "post", path = "/:object/getSuggest")
    public Route getSuggestionByCondition() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");
                String json = request.body();
                return ObjectService.getInstance().getSuggestionByCondition(object, json, numberOfSkip, numberOfLimit);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    
    @RouteInfo(method = "post", path = "/:object/getSearch")
    public Route getListBySearch() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String object = String.valueOf(request.params(":object"));
                int numberOfSkip = ServletUtil.getIntParameter(request, "skip");
                int numberOfLimit = ServletUtil.getIntParameter(request, "limit");
                String name = ServletUtil.getStringParameter(request, "name");
                long begin = ServletUtil.getLongParameter(request, "begin");
                long end = ServletUtil.getLongParameter(request, "end");
                String json = request.body();
                return ObjectService.getInstance().getSearchWithOneKeyBetween(object, json, numberOfSkip, numberOfLimit, name, begin, end);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    
}

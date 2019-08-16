package nttt.api.configuration;

import firo.utils.config.Config;

public class Configuration {

    public static final String SERVICE_NAME;
    public static final String FILE_ID_COUNTER_KEY;
    public static final String PREFIX;
    public static final int _dbTimeout;
    public static int _mongodbConnectTimeout;
    public static int _mongodbSocketTimeout;
    public static int _mongodbMaxWaitTime;
    public static int _mongodbServerSelectionTimeout;

    public static String databaseType;

    static {
        SERVICE_NAME = ConfigHelper.getParamString("service", "name", "");
        FILE_ID_COUNTER_KEY = ConfigHelper.getParamString("counter", "fileid_counter_key", "");
        _dbTimeout = Config.getParamInt("param", "dbconnector_timeout");
        _mongodbConnectTimeout = Config.getParamInt("mongodb", "connect_timeout");
        _mongodbSocketTimeout = Config.getParamInt("mongodb", "socket_timeout");
        _mongodbMaxWaitTime = Config.getParamInt("mongodb", "max_waittime");
        _mongodbServerSelectionTimeout = Config.getParamInt("mongodb", "server_selection_timeout");

        databaseType = Config.getParamString("database", "type", "");
        PREFIX = Config.getParamString("config", "api_prefix", "/api");
    }

}

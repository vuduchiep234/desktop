package ctt.response;

import com.vng.wmb.lib.json.JacksonHelper;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author longmd
 */
@JsonPropertyOrder({"error_code", "error_message", "data"})
public class DataResponse {

    @JsonProperty("error_code")
    private final String errorCode;
    @JsonProperty("error_message")
    private final String errorMessage;
    @JsonProperty("data")
    private Object data;
    private DataResponse.DataType dataType = DataResponse.DataType.NORMAL;
    private boolean isEscape = true;

    public DataResponse(String error, String message) {
        this.errorCode = error;
        this.errorMessage = message;
    }

    public DataResponse(String error, String message, String data) {
        this.errorCode = error;
        this.errorMessage = message;
        this.data = data;
    }

    public DataResponse(Object data) {
        this.errorCode = ResponseCode.SUCCESSFUL;
        this.errorMessage = "";
        this.data = data;
    }

    public DataResponse(Object data, DataResponse.DataType d, boolean isEscape) {
        this.errorCode = ResponseCode.SUCCESSFUL;
        this.errorMessage = "";
        this.data = data;
        this.dataType = d;
        this.isEscape = isEscape;
    }

    @JsonIgnore
    public String getError() {
        return this.errorCode;
    }

    @JsonIgnore
    public String getMessage() {
        return this.errorMessage;
    }

    @JsonIgnore
    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.setData(data, DataResponse.DataType.NORMAL);
    }

    public void setData(Object data, DataResponse.DataType dataType) {
        this.data = data;
        this.dataType = dataType;
    }

    @JsonIgnore
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @JsonIgnore
    public boolean isEscape() {
        return isEscape;
    }

    public void setEscape(boolean isEscape) {
        this.isEscape = isEscape;
    }

    @Override
    public String toString() {
        return DataResponse.toJsonString(this);
    }

    /**
     * ********************* STATIC ***************************************
     */
    public enum DataType {
        NORMAL, JSON_STR, UNSURE
    };

    // dongvd3 start
    public static final DataResponse SUCCESSFUL = new DataResponse(ResponseCode.SUCCESSFUL, "SUCCESSFUL");
    public static final DataResponse PARAMS_ERROR = new DataResponse(ResponseCode.PARAM_ERROR, "PARAM_ERROR");
    public static final DataResponse MISSING_PARAMS = new DataResponse(ResponseCode.MISSING_PARAMS, "MISSING_PARAMS");
    public static final DataResponse ACCOUNT_EXIST = new DataResponse(ResponseCode.ACCOUNT_EXIST, "Account is exist");
    public static final DataResponse ACCOUNT_NOT_EXIST = new DataResponse(ResponseCode.ACCOUNT_NOT_EXIST, "Account is not exist");
    public static final DataResponse DATASTORE_EXIST = new DataResponse(ResponseCode.DATASTORE_EXIST, "Datastore is exist");
    public static final DataResponse DATASTORE_NOT_EXIST = new DataResponse(ResponseCode.DATASTORE_NOT_EXIST, "Datastore is not exist");
    public static final DataResponse TAG_EXIST = new DataResponse(ResponseCode.TAG_EXIST, "Tag is exist");
    public static final DataResponse TAG_NOT_EXIST = new DataResponse(ResponseCode.TAG_NOT_EXIST, "Tag is not exist");
    public static final DataResponse DATASET_EXIST = new DataResponse(ResponseCode.DATASET_EXIST, "Dataset is exist");
    public static final DataResponse DATASET_NOT_EXIST = new DataResponse(ResponseCode.DATASET_NOT_EXIST, "Dataset is not exist");
    public static final DataResponse RELATIONSHIP_EXIST = new DataResponse(ResponseCode.RELATIONSHIP_EXIST, "Relationship is exist");
    public static final DataResponse RELATIONSHIP_NOT_EXIST = new DataResponse(ResponseCode.RELATIONSHIP_NOT_EXIST, "Relationship is not exist");
    public static final DataResponse STATISTIC_EXIST = new DataResponse(ResponseCode.STATISTIC_EXIST, "Statistic is exist");
    public static final DataResponse STATISTIC_NOT_EXIST = new DataResponse(ResponseCode.STATISTIC_NOT_EXIST, "Statistic is not exist");
    public static final DataResponse OBJECT_EXIST = new DataResponse(ResponseCode.OBJECT_EXIST, "Processing object is exist");
    public static final DataResponse OBJECT_NOT_EXIST = new DataResponse(ResponseCode.OBJECT_NOT_EXIST, "Processing object is not exist");
    public static final DataResponse ACCESS_DENIED = new DataResponse(ResponseCode.ACCESS_DENIED, "You have not permission!");
    public static final DataResponse NOT_EXIST = new DataResponse(ResponseCode.NOT_EXIST, "Not exist");
    public static final DataResponse EXIST = new DataResponse(ResponseCode.EXIST, "Exist");
    
    // dongvd3 end
    public static DataResponse getSuccessMsg() {
        return SUCCESSFUL;
    }

    public static String toJsonString(DataResponse dataResponse) {
        try {
            ObjectMapper mapper = null;

            if (dataResponse.isEscape()) {
                mapper = JacksonHelper.getEscapedInstance();
            } else {
                mapper = JacksonHelper.getUnescapedInstance();
            }

            if (mapper != null) {
                if (dataResponse.getDataType() == DataResponse.DataType.JSON_STR) {
                    return "{\"error_code\":\"" + dataResponse.getError() + "\",\"error_message\":\"" + dataResponse.getMessage() + "\", \"data\":" + dataResponse.getData() + "}";
                } else if (dataResponse.getDataType() == DataResponse.DataType.UNSURE && dataResponse.getData() instanceof String && isJsonString((String) dataResponse.getData())) {
                    return "{\"error_code\":\"" + dataResponse.getError() + "\",\"error_message\":\"" + dataResponse.getMessage() + "\", \"data\":" + dataResponse.getData() + "}";
                }

                // DATA_TYPE_NORMAL
                return mapper.writeValueAsString(dataResponse);
            }
        } catch (Exception e) {
        }

        return "";
    }

    public static boolean isJsonString(String str) {
        try {
            ObjectMapper mapper = JacksonHelper.getUnescapedInstance();
            mapper.readTree(str);

            return true;
        } catch (Exception e) {
        }

        return false;
    }
}

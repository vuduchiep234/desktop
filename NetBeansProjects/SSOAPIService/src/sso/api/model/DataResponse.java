package sso.api.model;

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
    public static final DataResponse SUCCESSFUL = new DataResponse(ResponseCode.SUCCESSFUL, "SUCCESSFUL");
	public static final DataResponse FAILED = new DataResponse(ResponseCode.FAILED, "FAILED");
        public static final DataResponse NO_ACTIVE = new DataResponse(ResponseCode.NO_ACTIVE, "NO_ACTIVE");
	public static final DataResponse PARAM_ERROR = new DataResponse(ResponseCode.PARAM_ERROR, "PARAM_ERROR");
	
    public static final DataResponse COUNTERS_ERROR = new DataResponse(ResponseCode.COUNTERS_ERROR, "COUNTERS_ERROR");
    public static final DataResponse NO_REQUEST = new DataResponse(ResponseCode.NO_REQUEST, "NO_REQUEST");
	
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
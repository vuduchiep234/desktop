package nttt.api.model;

/**
 *
 * @author dongvd3
 */
public class DichVu {

    private int id;
    private String name;
    private String icon;
    private int orderTypeId;
    private String apiGetInfo; // lấy thông tin hóa đơn.
    private String apiUpdateInfo; // cập nhật thông tin hóa đơn.
    private int status;

    public DichVu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(int orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getApiGetInfo() {
        return apiGetInfo;
    }

    public void setApiGetInfo(String apiGetInfo) {
        this.apiGetInfo = apiGetInfo;
    }

    public String getApiUpdateInfo() {
        return apiUpdateInfo;
    }

    public void setApiUpdateInfo(String apiUpdateInfo) {
        this.apiUpdateInfo = apiUpdateInfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

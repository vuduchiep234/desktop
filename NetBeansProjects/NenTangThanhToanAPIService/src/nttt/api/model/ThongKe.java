package nttt.api.model;

/**
 *
 * @author dongvd3
 */
public class ThongKe {

    private long id;
    private long tongSoGiaoDich;
    private long tongSoTienGiaoDich;
    private long time;

    public ThongKe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTongSoGiaoDich() {
        return tongSoGiaoDich;
    }

    public void setTongSoGiaoDich(long tongSoGiaoDich) {
        this.tongSoGiaoDich = tongSoGiaoDich;
    }

    public long getTongSoTienGiaoDich() {
        return tongSoTienGiaoDich;
    }

    public void setTongSoTienGiaoDich(long tongSoTienGiaoDich) {
        this.tongSoTienGiaoDich = tongSoTienGiaoDich;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}

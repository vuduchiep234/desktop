package nttt.api.model;

public class RefundATMMoMoRequest extends PayATMRequest {
	
	String transId;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}
}

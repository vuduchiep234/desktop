package nttt.api.model;

public class PayATMResponse extends CaptureMoMoResponse {
	
	private String bankCode;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
}

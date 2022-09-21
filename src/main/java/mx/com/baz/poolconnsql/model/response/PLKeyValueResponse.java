package mx.com.baz.poolconnsql.model.response;

public class PLKeyValueResponse {
	
	private String KEY_R;
	private String VALUE_R;
	
	
	
	public String getKEY_R() {
		return KEY_R;
	}
	public void setKEY_R(String kEY_R) {
		KEY_R = kEY_R;
	}
	public String getVALUE_R() {
		return VALUE_R;
	}
	public void setVALUE_R(String vALUE_R) {
		VALUE_R = vALUE_R;
	}
	
	public PLKeyValueResponse(String kEY_R, String vALUE_R) {
		KEY_R = kEY_R;
		VALUE_R = vALUE_R;
	}
	
	public PLKeyValueResponse() {
	}
	
	

}

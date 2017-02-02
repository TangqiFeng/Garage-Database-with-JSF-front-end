import javax.faces.bean.ManagedBean;

@ManagedBean
public class Manufacturer {
	private String manuCode;
	private String manuName;
	private String manuDetail;
	
	public Manufacturer() {
		super();
	}

	public Manufacturer(String manuCode, String manuName, String manuDetail) {
		super();
		this.manuCode = manuCode;
		this.manuName = manuName;
		this.manuDetail = manuDetail;
	}

	public String getManuCode() {
		return manuCode;
	}

	public void setManuCode(String manuCode) {
		this.manuCode = manuCode;
	}

	public String getManuName() {
		return manuName;
	}

	public void setManuName(String manuName) {
		this.manuName = manuName;
	}

	public String getManuDetail() {
		return manuDetail;
	}

	public void setManuDetail(String manuDetail) {
		this.manuDetail = manuDetail;
	}
	
	
}

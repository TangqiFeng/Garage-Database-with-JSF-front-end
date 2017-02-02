import javax.faces.bean.ManagedBean;

@ManagedBean
public class FullDetail {
	private String reg;
	private String manuCode;
	private String manuName;
	private String manuDetail;
	private String modelCode;
	private String modelName;
	private String modelDesc;
	private int mileage;
	private float price;
	private String colour;
	private String fuel;
	
	public FullDetail() {
		super();
	}

	public FullDetail(String reg, String manuCode, String manuName, String manuDetail, String modelCode,
			String modelName, String modelDesc, int mileage, float price, String colour, String fuel) {
		super();
		this.reg = reg;
		this.manuCode = manuCode;
		this.manuName = manuName;
		this.manuDetail = manuDetail;
		this.modelCode = modelCode;
		this.modelName = modelName;
		this.modelDesc = modelDesc;
		this.mileage = mileage;
		this.price = price;
		this.colour = colour;
		this.fuel = fuel;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
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

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelDesc() {
		return modelDesc;
	}

	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	
}

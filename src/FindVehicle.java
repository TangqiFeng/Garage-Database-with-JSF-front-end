import javax.faces.bean.ManagedBean;

@ManagedBean
public class FindVehicle {

	private String flag;
	private float price;
	private String colour;
	private String fuel;
	
	public FindVehicle() {
		super();
		this.colour=null;
	}

	public FindVehicle(String flag, float price, String colour, String fuel) {
		super();
		this.flag = flag;
		this.price = price;
		this.colour = colour;
		this.fuel = fuel;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/garage";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	// manufacturers
	public ArrayList<Manufacturer> getManufacturers() throws SQLException {
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String sql = "select * from manufacturer";
		ResultSet rs = myStmt.executeQuery(sql);

		ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>();

		while (rs.next()) {
			manufacturers.add(new Manufacturer(rs.getString("manu_code"), rs.getString("manu_name"),
					rs.getString("manu_details")));
		}
		System.out.println(manufacturers);
		return manufacturers;
	}

	public void addManufacturers(Manufacturer m) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO manufacturer values (?,?,?)");
		myStmt.setString(1, m.getManuCode());
		myStmt.setString(2, m.getManuName());
		myStmt.setString(3, m.getManuDetail());
		System.out.println(myStmt);
		myStmt.executeUpdate();
	}
	
	public void renewManufacturers(Manufacturer m) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("UPDATE manufacturer SET manu_name = ? , manu_details = ? WHERE manu_code = ? ");
		myStmt.setString(1, m.getManuName());
		myStmt.setString(2, m.getManuDetail());
		myStmt.setString(3, m.getManuCode());
		System.out.println(myStmt);
		myStmt.executeUpdate();
	}
	
	public void deleteManufacturer(Manufacturer m) throws SQLException{
		Connection conn;
		
		conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("DELETE FROM manufacturer WHERE manu_code = ?  ");
		myStmt.setString(1, m.getManuCode());
		System.out.println(myStmt);
		myStmt.executeUpdate();
		
		
	}

	// models
	public ArrayList<Model> getModels() throws SQLException {
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String sql = "select * from model";
		ResultSet rs = myStmt.executeQuery(sql);

		ArrayList<Model> models = new ArrayList<Model>();

		while (rs.next()) {
			models.add(new Model(rs.getString("manu_code"), rs.getString("model_code"), rs.getString("model_name"),
					rs.getString("model_desc")));
		}
		System.out.println(models);
		return models;
	}

	public void addModels(Model m) throws SQLException {
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("INSERT INTO model values (?,?,?,?)");
			myStmt.setString(1, m.getManuCode());
			myStmt.setString(2, m.getModelCode());
			myStmt.setString(3, m.getModelName());
			myStmt.setString(4, m.getModelDesc());
			System.out.println(myStmt);
			myStmt.executeUpdate();
		
	}

	// vehicles
	public ArrayList<Vehicle> getVehicles() throws SQLException {
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String sql = "select * from vehicle";
		ResultSet rs = myStmt.executeQuery(sql);

		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

		while (rs.next()) {
			vehicles.add(new Vehicle(rs.getString("reg"), rs.getString("manu_code"), rs.getString("model_code"),
					rs.getInt("mileage"), rs.getFloat("price"), rs.getString("colour"), rs.getString("fuel")));
		}
		System.out.println(vehicles);
		return vehicles;
	}
	
	public ArrayList<FullDetail> getFullDetails(String v) throws SQLException {
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement( "select v.reg , ma.manu_code , ma.manu_name , ma.manu_details "+
														" , mo.model_code , mo.model_name , mo.model_desc , "+
														" v.mileage , v.price , v.colour , v.fuel "+
														" from vehicle v "+
														" inner join manufacturer ma "+
														" on v.manu_code = ma.manu_code "+
														" inner join model mo "+
														" on v.model_code = mo.model_code "+
														" where reg = ?");
		
		myStmt.setString(1, v);
		System.out.println(myStmt);
		ResultSet rs = myStmt.executeQuery();
		ArrayList<FullDetail> fullDetails = new ArrayList<FullDetail>();

		while (rs.next()) {
			fullDetails.add(new FullDetail(rs.getString("reg"), rs.getString("manu_code"), rs.getString("manu_name"),
							rs.getString("manu_details"), rs.getString("model_code"), rs.getString("model_name"),
							rs.getString("model_desc"), rs.getInt("mileage"), rs.getFloat("price"), rs.getString("colour"),
							rs.getString("fuel")));
		}
		System.out.println(fullDetails);
		return fullDetails;
	}

	public void addVehicles(Vehicle m) throws SQLException {
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("INSERT INTO vehicle values (?,?,?,?,?,?,?)");
			myStmt.setString(1, m.getReg());
			myStmt.setString(2, m.getManuCode());
			myStmt.setString(3, m.getModelCode());
			myStmt.setInt(4, m.getMileage());
			myStmt.setFloat(5, m.getPrice());
			myStmt.setString(6, m.getColour());
			myStmt.setString(7, m.getFuel());
			System.out.println(myStmt);
			myStmt.executeUpdate();
		
	}

	// Search vehicle
	public ArrayList<FindCar> SearchVehicles(FindVehicle m) throws SQLException {
		ArrayList<FindCar> findVehicles = new ArrayList<FindCar>();
		String a = null;
		
		//check if color specified
		// if so myStmt += " and color = ?
		
		if(m.getColour() == ""){
			if (m.getFlag().equals("L")) {
				a = "<";
			} else if (m.getFlag().equals("G")) {
				a = ">";
			} else if (m.getFlag().equals("E")) {
				a = "=";
			}
			
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("select v.reg , v.manu_code , ma.manu_name , v.model_code "+
									       					 " , mo.model_name , v.mileage , v.price , v.colour , v.fuel "+
									       					 " from vehicle v "+
									       					 " inner join manufacturer ma "+
									       					 " on v.manu_code = ma.manu_code "+
									       					 " inner join model mo "+
									       					 " on v.model_code = mo.model_code "+
									       					 " where price " + a + " ?  and fuel=?");
			
			myStmt.setFloat(1, m.getPrice());
			myStmt.setString(2, m.getFuel());
			System.out.println(myStmt);
			ResultSet rs = myStmt.executeQuery();
			
			while (rs.next()) {
				findVehicles.add(new FindCar(rs.getString("reg"), rs.getString("manu_code"), rs.getString("model_name"),
						rs.getString("model_code"), rs.getString("model_name"), rs.getInt("mileage"), rs.getFloat("price"), rs.getString("colour"), rs.getString("fuel")));
			}
			System.out.println(findVehicles);
			
		}else{
			if (m.getFlag().equals("L")) {
				a = "<";
			} else if (m.getFlag().equals("G")) {
				a = ">";
			} else if (m.getFlag().equals("E")) {
				a = "=";
			}
			Connection conn = mysqlDS.getConnection();
			PreparedStatement myStmt = conn.prepareStatement("select v.reg , v.manu_code , ma.manu_name , v.model_code "+
									       					 " , mo.model_name , v.mileage , v.price , v.colour , v.fuel "+
									       					 " from vehicle v "+
									       					 " inner join manufacturer ma "+
									       					 " on v.manu_code = ma.manu_code "+
									       					 " inner join model mo "+
									       					 " on v.model_code = mo.model_code "+
									       					 " where price " + a + " ? and  colour=? and fuel=?");
			
			myStmt.setFloat(1, m.getPrice());
			myStmt.setString(2, m.getColour());
			myStmt.setString(3, m.getFuel());
			System.out.println(myStmt);
			ResultSet rs = myStmt.executeQuery();
			
			while (rs.next()) {
				findVehicles.add(new FindCar(rs.getString("reg"), rs.getString("manu_code"), rs.getString("model_name"),
						rs.getString("model_code"), rs.getString("model_name"), rs.getInt("mileage"), rs.getFloat("price"), rs.getString("colour"), rs.getString("fuel")));
			}
			System.out.println(findVehicles);
		}
		

		return findVehicles;
	}
}

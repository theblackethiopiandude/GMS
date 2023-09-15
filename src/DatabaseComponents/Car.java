package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Car extends Connection implements IConnection{
    private  String plateNumber;
    private  String make;
    private  String model;
    private  String vin;
    private  int year;
    private  int customerID;

    public Car(String plateNumber, String make, String model, String vin, int year, int customerID) {
        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.year = year;
        this.customerID = customerID;
    }
    public Car(){

    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public void insertIntoDatabase() {
        try (PreparedStatement pst = getConnection().prepareStatement("insert into Cars values(?,?,?,?,?,?)")){
            pst.setString(1, this.getPlateNumber());
            pst.setString(2, this.getMake());
            pst.setString(3, this.getModel());
            pst.setString(4, this.getVin());
            pst.setInt(5, this.getYear());
            pst.setInt(6, this.getCustomerID());
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Object[][] addToTable(int customerID){
        int rowSize = 25;
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT COUNT(PlateNo) AS [size] FROM Cars")){
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                rowSize = rs.getInt("size");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        Object [][] objects = new Object[rowSize][5];
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM Cars WHERE CustomerID = ?")){
            pst.setInt(1, customerID);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while(rs.next()) {
                objects[i][0] = rs.getString("PlateNo");
                objects[i][1] = rs.getString("Make");
                objects[i][2] = rs.getString("Model");
                objects[i++][3] = rs.getString("VIN");

            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return objects;
    }
}

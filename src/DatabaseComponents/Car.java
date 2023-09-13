package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Car extends Connection implements IConnection{
    private final String plateNumber;
    private final String make;
    private final String model;
    private final String vin;
    private final int year;
    private final int customerID;

    public Car(String plateNumber, String make, String model, String vin, int year, int customerID) {
        this.plateNumber = plateNumber;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.year = year;
        this.customerID = customerID;
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
}

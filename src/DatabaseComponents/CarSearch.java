package DatabaseComponents;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarSearch extends Connection{
    private String name, plateNumber, phoneNumber, make, model;

    public void setName(String name) {
        this.name = name;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Object[] getRow(String plateNumber){
        Object [] object = new Object[5];
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM CarSearch WHERE PlateNo = ?")){
            pst.setString(1, plateNumber);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                object [0] = rs.getString("Name");
                object [1] = rs.getString("PlateNo");
                object [2] = rs.getString("PhoneNumber");
                object [3] = rs.getString("Make");
                object [4] = rs.getString("Model");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return object;
    }

    public Object[][] addToTable(){
        int rowSize = 0;
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT COUNT(PlateNo) AS [size] FROM CarSearch")){
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                rowSize = rs.getInt("size");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        Object [][] objects = new Object[rowSize][5];
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM CarSearch")){
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while(rs.next()) {
                this.setName(rs.getString("Name"));
                this.setPlateNumber(rs.getString("PlateNo"));
                this.setPhoneNumber(rs.getString("PhoneNumber"));
                this.setMake(rs.getString("Make"));
                this.setModel(rs.getString("Model"));
                objects[i][0] = this.getName();
                objects[i][1] = this.getPlateNumber();
                objects[i][2] = this.getPhoneNumber();
                objects[i][3] = this.getMake();
                objects[i++][4] = this.getModel();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return objects;
    }
}

package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends Connection implements IConnection{
    private  int ID;
    private String name;
    private String phoneNumber;
    private String address;

    public Customer(){

    }
    public Customer(int ID, String name, String phoneNumber, String address) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void insertIntoDatabase() {
        try (PreparedStatement pst = getConnection().prepareStatement("insert into Customers values(?,?,?)")){
            pst.setString(1, this.getName());
            pst.setString(2, this.getPhoneNumber());
            pst.setString(3, this.getAddress());
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Object[][] addToTable(){
        int rowSize = 25;
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT COUNT(ID) AS [size] FROM Customers")){
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                rowSize = rs.getInt("size");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        Object [][] objects = new Object[rowSize][3];
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM Customers")){
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while(rs.next()) {
                this.setName(rs.getString("Name"));
                this.setPhoneNumber(rs.getString("PhoneNumber"));
                this.setAddress(rs.getString("Address"));
                objects[i][0] = this.getName();
                objects[i][1] = this.getPhoneNumber();
                objects[i++][2] = this.getAddress();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return objects;
    }
}

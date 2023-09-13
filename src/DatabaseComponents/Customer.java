package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customer extends Connection implements IConnection{
    private final int ID;
    private String name;
    private String phoneNumber;
    private String address;

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
}

package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class History extends Connection implements IConnection{
    private final String plateNumber;
    private final String Date;
    private final String Notes;
    private final int ServiceID;
    private final int Cost;
    private final int id;

    public History(String plateNumber, String date, String notes, int serviceID, int cost, int id) {
        this.plateNumber = plateNumber;
        Date = date;
        Notes = notes;
        ServiceID = serviceID;
        Cost = cost;
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getDate() {
        return Date;
    }

    public String getNotes() {
        return Notes;
    }

    public int getServiceID() {
        return ServiceID;
    }

    public int getCost() {
        return Cost;
    }

    public int getId() {
        return id;
    }

    @Override
    public void insertIntoDatabase() {
        try (PreparedStatement pst = getConnection().prepareStatement("insert into Cars values(?,?,?,?,?)")){
            pst.setString(1, this.getPlateNumber());
            pst.setString(2, this.getDate());
            pst.setInt(3, this.getServiceID());
            pst.setInt(4, this.getCost());
            pst.setString(5, this.getNotes());
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

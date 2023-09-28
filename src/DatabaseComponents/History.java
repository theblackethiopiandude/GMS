package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class History extends Connection implements IConnection{
    /*private final String plateNumber;
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
    }*/

    @Override
    public void insertIntoDatabase() {
        try (PreparedStatement pst = getConnection().prepareStatement("insert into Cars values(?,?,?,?,?)")){
//            pst.setString(1, this.getPlateNumber());
//            pst.setString(2, this.getDate());
//            pst.setInt(3, this.getServiceID());
//            pst.setInt(4, this.getCost());
//            pst.setString(5, this.getNotes());
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void insertIntoDatabase(String plateNumber, int serviceID, String notes) {
        try (PreparedStatement pst = getConnection().prepareStatement("INSERT INTO History(PlateNo, ServiceID, Notes) VALUES (?,?,?)")){
            pst.setString(1, plateNumber);
            pst.setInt(2, serviceID);
            pst.setString(3, notes);
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Object[] getLastRow(){
        Object [] object = new Object[2];
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM CarHistory ORDER BY ID DESC LIMIT 1")){
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                object[0] = rs.getString("Service");
                object[1] = rs.getString("Notes");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return object;
    }
    public Object[][] addToTable(String plateNumber){
        int rowSize = 0;
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT COUNT(ID) AS [size] FROM CarHistory WHERE PlateNo = ?")){
            pst.setString(1, plateNumber);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                rowSize = rs.getInt("size");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        Object [][] objects = new Object[rowSize][2];
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM CarHistory WHERE PlateNo = ?")){
            pst.setString(1, plateNumber);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while(rs.next()) {
                objects[i][0] = rs.getString("Service");
                objects[i++][1] = rs.getString("Notes");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return objects;
    }
}

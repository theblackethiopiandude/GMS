package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service extends Connection implements IConnection{
   /* private final int id;
    private final String description;

    public Service(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }*/

    @Override
    public void insertIntoDatabase() {
        try (PreparedStatement pst = getConnection().prepareStatement("INSERT INTO Services values(?)")){
//            pst.setString(1, this.getDescription());
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void insertIntoDatabase(String serviceDescription) {
        try (PreparedStatement pst = getConnection().prepareStatement("INSERT INTO Services(Description) values(?)")){
            pst.setString(1, serviceDescription);
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Object[] getServices(){
        int rowSize = 0;
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT COUNT(ID) AS [size] FROM Services")){
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                rowSize = rs.getInt("size");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        Object [] object = new Object[rowSize];
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT Description FROM Services")){
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while(rs.next()) {
                object[i++] = rs.getString("Description");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return object;
    }

    public int getServiceID(String description){
        int ID = -1;
        try (PreparedStatement pst = getConnection().prepareStatement("SELECT ID FROM Services WHERE Description = ?")){
            pst.setString(1, description);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                ID = rs.getInt("ID");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return ID;
    }
}

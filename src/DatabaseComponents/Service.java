package DatabaseComponents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Service extends Connection implements IConnection{
    private final int id;
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
    }

    @Override
    public void insertIntoDatabase() {
        try (PreparedStatement pst = getConnection().prepareStatement("insert into Services values(?)")){
            pst.setString(1, this.getDescription());
            pst.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

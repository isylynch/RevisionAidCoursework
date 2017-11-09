package Model;

import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FolderService {
    public static void selectAll(List<Folder> targetList, DatabaseConnection database) {

        PreparedStatement statement = database.newStatement("SELECT FolderID, FolderName, FolderIcon, ParentID FROM Folder");

        try {
            if (statement != null) {
                ResultSet results = database.executeQuery(statement);

                if (results != null) {
                    while (results.next()) {
                        targetList.add(new Folder(results.getInt("FolderID"),results.getString("FolderName"), results.getString("FolderIcon"), results.getInt("ParentID")));
                    }
                }
            }
        } catch (SQLException resultsException) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Database Error");
            error.setHeaderText("There seems to have been an error with the database:");
            error.setContentText("Select all error: " + resultsException.getMessage());
        }
    }

    public static void selectByID() {

    }

    public static void save() {

    }

    public static void delete() {

    }

    public static ArrayList<String> getChildren() {
        ArrayList<String> children = new ArrayList<>();
        return children;
    }
}

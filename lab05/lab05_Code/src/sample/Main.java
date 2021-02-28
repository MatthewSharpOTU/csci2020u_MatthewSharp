package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Sets stage
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Lab 05 Solution");
        primaryStage.setWidth(615);
        primaryStage.setHeight(350);

        // Creates Table
        TableView myTable = new TableView();
        myTable.setEditable(true);

        // Sets Items to the Table
        ObservableList<StudentRecord> students = DataSource.getAllMarks();
        myTable.setItems(students);

        // Sets first column
        TableColumn firstNameCol = new TableColumn("SID");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory("studentID"));

        // Sets second column
        TableColumn secondNameCol = new TableColumn("Assignments");
        secondNameCol.setMinWidth(100);
        secondNameCol.setCellValueFactory(new PropertyValueFactory("assignmentGrade"));

        // Set third column
        TableColumn thirdNameCol = new TableColumn("Midterm");
        thirdNameCol.setMinWidth(100);
        thirdNameCol.setCellValueFactory(new PropertyValueFactory("midtermGrade"));

        // Set fourth column
        TableColumn fourthNameCol = new TableColumn("Final Exam");
        fourthNameCol.setMinWidth(100);
        fourthNameCol.setCellValueFactory(new PropertyValueFactory("examGrade"));

        // Set fifth column
        TableColumn fifthNameCol = new TableColumn("Final Mark");
        fifthNameCol.setMinWidth(100);
        fifthNameCol.setCellValueFactory(new PropertyValueFactory("finalGrade"));

        // Set sixth column
        TableColumn sixthNameCol = new TableColumn("Letter Grade");
        sixthNameCol.setMinWidth(100);
        sixthNameCol.setCellValueFactory(new PropertyValueFactory("letterGrade"));

        // Inputs the column data into the table
        myTable.getColumns().addAll(firstNameCol,secondNameCol,thirdNameCol,fourthNameCol,fifthNameCol,sixthNameCol);

        // Set the ScrollPane to the table
        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(600, 315);
        sp.setContent(myTable);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(0,0,0,0));
        vbox.getChildren().addAll(sp);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main Function
    public static void main(String[] args) {
        launch(args);
    }
}

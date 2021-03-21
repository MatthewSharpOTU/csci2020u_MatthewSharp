package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private String currentFilename; // Variable used to store the current file name
    private File selectedFile; // Variable used to store the current file
    private ObservableList<StudentRecord> marks = FXCollections.observableArrayList(); // Creates the list of student data

    /**
     * This function runs the stage and will display the menu options and resulting data
     *
     * @param primaryStage - Stage that will display the menu and all table data
     * @throws Exception - Thrown if an error occurs
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Sets stage
        Scene scene = new Scene(new VBox());
        primaryStage.setTitle("Lab 08 Solution");
        primaryStage.setWidth(615);
        primaryStage.setHeight(500);

        // Sets Menu and "File" option
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");

        // Sets the "New" action
        MenuItem newFile = new MenuItem("New");
        newFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close(); // Closes current stage
                try {
                    start(primaryStage); // Calls the start function
                } catch (Exception e) { // Throws exception if there is an error
                    e.printStackTrace();
                }
            }
        });

        // Sets the "Open" action
        MenuItem open = new MenuItem("Open");
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // Gets the file to open from the user
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open - Resource File");
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv*"));
                    selectedFile = fileChooser.showOpenDialog(primaryStage); // Saves the file
                    currentFilename = selectedFile.getName(); // Saves the file's name
                    currentFilename = currentFilename.replaceFirst("[.][^.]+$", ""); // Regex to remove file's extension
                    VBox vbox = load(); // Calls the load method, returns the table data to be displayed on the scene
                    ((VBox) scene.getRoot()).getChildren().addAll(vbox);

                    // Sets up "Add Student" Feature
                    GridPane calculatedData = new GridPane();
                    calculatedData.setAlignment(Pos.BOTTOM_CENTER);
                    calculatedData.setHgap(10);
                    calculatedData.setVgap(10);
                    calculatedData.setPadding(new Insets(25, 25, 25, 25));
                    Label idText = new Label("SID: ");
                    Label midtermText = new Label("Midterm: ");
                    Label assignmentText = new Label("Assignments: ");
                    Label finalText = new Label("Final Exam: ");
                    TextField idData = new TextField();
                    TextField midtermData = new TextField();
                    TextField assignmentData = new TextField();
                    TextField finalData = new TextField();

                    // Button to handle the new student
                    Button btn = new Button("Add");
                    HBox hbBtn = new HBox(10);
                    hbBtn.setAlignment(Pos.BOTTOM_LEFT);
                    hbBtn.getChildren().add(btn);
                    final Text actionTarget = new Text();

                    // Construct new data table
                    calculatedData.add(idText, 0, 0);
                    calculatedData.add(idData, 1, 0);
                    calculatedData.add(midtermText, 0, 1);
                    calculatedData.add(midtermData, 1, 1);
                    calculatedData.add(assignmentText, 2, 0);
                    calculatedData.add(assignmentData, 3, 0);
                    calculatedData.add(finalText, 2, 1);
                    calculatedData.add(finalData, 3, 1);
                    calculatedData.add(hbBtn, 0, 2);
                    calculatedData.add(actionTarget, 1, 2);

                    ((VBox) scene.getRoot()).getChildren().addAll(calculatedData); // Adds the new GridPane to the scene

                    // Event to handle the "add" button
                    btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (midtermData.getText().equals("") || assignmentData.getText().equals("")
                                    || idData.getText().equals("") || finalData.getText().equals("")){
                                actionTarget.setFill(Color.BLACK);
                                actionTarget.setText("Please fill in all fields");
                            }
                            else{
                                marks.add(new StudentRecord(idData.getText(), Float.parseFloat(assignmentData.getText()),
                                        Float.parseFloat(midtermData.getText()), Float.parseFloat(finalData.getText())));
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Sets the "Save" action
        MenuItem save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    save(); // Calls save function to save the data
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        MenuItem saveAs = new MenuItem("Save As");
        saveAs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Gets the file to save from the user
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save As - Resource File");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv*"));
                selectedFile = fileChooser.showSaveDialog(primaryStage); // Saves the csv file
                currentFilename = selectedFile.getName(); // Saves the CSV file name
                currentFilename = currentFilename.replaceFirst("[.][^.]+$", ""); // regex that removes the csv extension

                try {
                    save(); //Calls the save function to save data
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Sets up "Exit" action
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0); // Exits system
            }
        });

        // Adds all menu options and adds to the scene
        menuFile.getItems().addAll(newFile, open, save, saveAs, exit);
        menuBar.getMenus().addAll(menuFile);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This function will take the currentFilename variable and save the student data in the intended directory
     * @throws IOException - If there is an issue writing the data into the csv file
     */
    public void save() throws IOException {
        File savedFile = new File(currentFilename+".csv");
        savedFile.createNewFile();
        PrintWriter fileOutput = new PrintWriter(savedFile);
        fileOutput.println("SID, Assignments, Midterm, Final Exam"); // Writes first line of csv

        // Loop that writes each of the students data
        for (StudentRecord student: marks) {
            fileOutput.println(student.getStudentID() + "," + student.getAssignmentGrade() + "," + student.getMidtermGrade() + "," + student.getFinalGrade());
        }
        fileOutput.close(); // Closes written file
    }

    /**
     * This function will load in the currentFilename file, returning the table to be displayed to the scene
     * @return - VBox which contains the scroll option and table data
     * @throws IOException - If an error reading data occurs
     */
    public VBox load() throws IOException {
        FileReader fileInput = new FileReader(currentFilename + ".csv");
        BufferedReader input = new BufferedReader(fileInput);

        String line = input.readLine(); // Reads the first line, which skips the headers

        // Loop which reads all data per student in the csv file
        while((line = input.readLine())!= null){
            String[] data = line.split(",");
            marks.add(new StudentRecord(data[0], Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3])));
        }

        // Creates Table
        TableView myTable = new TableView();
        myTable.setEditable(true);
        myTable.setItems(marks);

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

        return vbox;
    }

    // Main Function
    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Text buttonAction; // Used for button pressed output
    @FXML
    private TextField fullName; // Used for Full Name output
    @FXML
    private TextField emailAddress; // Used for E-Mail output
    @FXML
    private TextField phoneNumber; // Used for Phone Number output
    @FXML
    private DatePicker birthDay; // Used for Date of Birth output

    // When the register button is pressed with the proper text fields
    // inputted, it will print out the entered data on the console
    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        System.out.println("Full Name: " + fullName.getText());
        System.out.println("EMail: " + emailAddress.getText());
        System.out.println("Phone #: " + phoneNumber.getText());
        System.out.println("Date of Birth: " + birthDay.getValue());
        buttonAction.setText("Account has been registered");
    }
}


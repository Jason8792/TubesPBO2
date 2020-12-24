package Controller;

import DAO.UserDAO;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class controllerDaftar {
    public TextField addusername;
    public PasswordField addpassword;
    public Button sign;
    public Button cancel;

    public void initialize() {
        addpassword.setText("");
        addusername.setText("");
    }

    //untuk mendaftakan akun baru
    public void createacc(ActionEvent actionEvent) {
        UserDAO udao = new UserDAO();
        User data = new User();
        if (addusername.getText().equals("") || addpassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("username or password is empty");
            alert.showAndWait();
        } else {
            try {
                udao.showData(addusername.getText());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("username already used");
                alert.showAndWait();
            } catch (Exception e) {
                data.setUsername(addusername.getText());
                data.setPassword(addpassword.getText());
                udao.addData(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Create account is successful");
                alert.setContentText("back to login menu");
                alert.showAndWait();
            }
        }
    }

    //jika ingin dibatalkan
    public void cancelcreate(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) cancel.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}

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

public class controllerLogin {
    public PasswordField password;
    public TextField username;
    public Button btn;

    UserDAO userDAO = new UserDAO();

    public void login(ActionEvent actionEvent) throws IOException {
        User user = userDAO.showData(username.getText());
        if (user != null && user.getPassword().equals(password.getText())){
            Stage stage = (Stage) btn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/beranda.fxml"));
            Parent root = loader.load();
            controllerBeranda controllerBeranda = loader.getController();
            controllerBeranda.initialize(user);
            stage.setTitle("Beranda");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }

    public void daftar(ActionEvent actionEvent) {

    }
}

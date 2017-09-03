package application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.sql.*;


public class Main extends Application {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Benniesdb";
	static final String USER = "root";
	static final String PASS = "Lolipop123";


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//name the tab
		primaryStage.setTitle("Welcome");

		//create and set the grid for alignment
		GridPane grid = new GridPane();
		createGrid(grid);

		//adding details to the grid
		Text scenetitle = new Text("Bennies");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0,2,1);

		Label userName = new Label("User Name:");
		grid.add(userName,  0,1);

		TextField userTextField  = new TextField();
		grid.add(userTextField, 1, 1);

		Label password =  new Label("Password:");
		grid.add(password, 0, 2);

		PasswordField passwordBox = new PasswordField();
		grid.add(passwordBox, 1, 2);

		Button signbtn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(signbtn);
		grid.add(hbBtn, 1, 4);
		signbtn.setOnAction(new EventHandler<ActionEvent>() {

		    @Override//SIGN IN BUTTON
		    public void handle(ActionEvent e) {
		    	int a ;
		    	a= checkDetails(userTextField.getText(),passwordBox.getText());


				if(a ==0){
					Stage shopStage = new Stage();
					primaryStage.close();
					shop(shopStage);
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Sorry!");
					alert.setHeaderText("Error");
					alert.setContentText("Incorrect username and password");
					alert.showAndWait();
				}






		    }
		});

		Button registerbtn = new Button("Register");
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn2.getChildren().add(registerbtn);
		grid.add(hbBtn2, 0, 4);

registerbtn.setOnAction(new EventHandler<ActionEvent>() {

		    @Override//REGISTER BUTTON
		    public void handle(ActionEvent e) {
		    	Stage registerStage = new Stage();
		    	primaryStage.close();
		    	register(registerStage);

		    }
		});

		//create scene and add grid and form dimensions
		Scene scene = new Scene(grid,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public void createGrid(GridPane grid){

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(30,30,30,30));

	}
	public void shop(Stage shopStage){
		StackPane root = new StackPane();

		shopStage.setTitle("Bennies");

		TabPane tabPane = new TabPane();

		Tab tab1 = new Tab();
		tab1.setText("Groceries");

		Tab tab2 = new Tab();
		tab2.setText("Special Offers");

		Tab tab3 = new Tab();
		tab3.setText("My Lists");

		Tab tab4 = new Tab();
		tab4.setText("My Basket");


		tabPane.getTabs().addAll(tab1,tab2,tab3);
		  root.getChildren().add(tabPane);


	    Scene shopScene = new Scene(root,950,700);
	    shopStage.setScene(shopScene);
	         shopStage.show();

	}
	public void register(Stage registerStage){



		GridPane grid = new GridPane();
		createGrid(grid);

		Text scenetitle = new Text("Registration");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0,2,1);

		Label userName = new Label("User Name:");
		grid.add(userName,  0,1);

		TextField userTextField  = new TextField();
		grid.add(userTextField, 1, 1);

		Label password =  new Label("Password:");
		grid.add(password, 0, 2);

		PasswordField passwordBox = new PasswordField();
		grid.add(passwordBox, 1, 2);


		Label Name = new Label("Name:");
		grid.add(Name,  0,3);

		TextField nameTextField  = new TextField();
		grid.add(nameTextField, 1, 3);


		Label zipCode = new Label("Post Code:");
		grid.add(zipCode,  0,4);

		TextField zipTextField  = new TextField();
		grid.add(zipTextField, 1, 4);

		Button submitbtn = new Button("Submit");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(submitbtn);
		grid.add(hbBtn, 1, 5);
		submitbtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {

			insertRecord(userTextField.getText(),passwordBox.getText(),nameTextField.getText(),zipTextField.getText());




		    	registerStage.close();
		    	Stage stagey = new Stage();
		    	start(stagey);




		    }
		});


         registerStage.setTitle("Registration");
         Scene registrationScene = new Scene(grid,400,400);
         registerStage.setScene(registrationScene);
         registerStage.show();

	}
	public void insertRecord(String a, String b ,String c, String d){
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Benniesdb", "root", "Lolipop123");
			System.out.println("Connected database successfully...");
			System.out.println("Inserting record.....");
			stmt = conn.createStatement();
			String sql = "INSERT INTO userdetails VALUES('"+ a +"','"+b+"','"+c+"','"+d+"')";
			stmt.executeUpdate(sql);
			System.out.println("Inserted");
		} catch (SQLException var19) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sorry!");
			alert.setHeaderText("Conflict");
			alert.setContentText("Username already in use!");
			alert.showAndWait();

			var19.printStackTrace();
		} catch (Exception var20) {
			var20.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					conn.close();
				}
			} catch (SQLException var18) {
				;
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException var17) {
				var17.printStackTrace();
			}

		}


	}
	public int checkDetails(String a, String b){
		Connection conn = null;
		Statement stmt = null;


		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Benniesdb", "root", "Lolipop123");
			System.out.println("Connected database successfully...");
			stmt = conn.createStatement();
			String sql = "SELECT userid,password FROM userdetails WHERE userid ='"+a+"' AND password ='"+b+"'";
			ResultSet rs = stmt.executeQuery(sql);
			boolean test =rs.absolute(1);
			if (test){
				return 0;
			} else {
				return 1;
			}



		} catch (SQLException var19) {

			var19.printStackTrace();
		} catch (Exception var20) {
			var20.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					conn.close();
				}
			} catch (SQLException var18) {
				;
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException var17) {
				var17.printStackTrace();
			}

		}

		return 1;

	}
}

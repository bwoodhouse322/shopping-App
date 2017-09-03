package application;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Main extends Application {


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
		    	Stage shopStage = new Stage();
		    	primaryStage.close();
		    	shop(shopStage);


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
		    	 Connection con = null;
			      Statement stmt = null;
			      int result = 0;

		    	 try {
		             Class.forName("org.hsqldb.jdbc.JDBCDriver");
		             con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/Benniesdb", "SA", "");
		             stmt = con.createStatement();

		             result = stmt.executeUpdate("CREATE TABLE tutorials_tbl"
		             		+ " (id INT NOT NULL, title VARCHAR(50) NOT NULL, "
		             		+ "author VARCHAR(20) NOT NULL, submission_date DATE, "
		             		+ "PRIMARY KEY (id));");

		          }  catch (Exception ex) {
		             ex.printStackTrace(System.out);
		          }








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

}

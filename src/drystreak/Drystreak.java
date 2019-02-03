import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.geometry.Insets;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Drystreak extends Application {

    Button buttonCalc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OSRS Probability Calculator");
        buttonCalc = new Button();
        buttonCalc.setText("Calculate");

        
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(10);
      
   
        Label titleLogo = new Label("OSRS Widgets");
        GridPane.setConstraints(titleLogo, 3, 0);
        
        Label titleProg = new Label("Probability calculator");
        GridPane.setConstraints(titleProg, 3, 2);
        
        Label droprate = new Label("Droprate:");
        GridPane.setConstraints(droprate, 1, 8, 2,1);
        TextField droprateXTXT = new TextField();
        TextField droprateYTXT = new TextField();
        GridPane.setConstraints(droprateXTXT, 3, 8);
        droprateXTXT.setMaxWidth(60);
        GridPane.setConstraints(droprateYTXT, 5, 8);
        droprateYTXT.setMaxWidth(60);
        Label slash = new Label("/");
        GridPane.setConstraints(slash, 4,8);
        
        Label monsters = new Label("Monsters \nslain:");
        GridPane.setConstraints(monsters, 1, 12,2,1);
        TextField monsterTXT = new TextField();
        monsterTXT.setMaxWidth(60);
        GridPane.setConstraints(monsterTXT, 3, 12);
        
        Label drops = new Label("Amount of \ndrops:");
        GridPane.setConstraints(drops, 1, 16,2,1);
        TextField dropsTXT = new TextField();
        dropsTXT.setMaxWidth(60);
        GridPane.setConstraints(dropsTXT, 3, 16);
        
        GridPane.setConstraints(buttonCalc, 3, 30);
      
        layout.getChildren().addAll(titleLogo, titleProg, droprate, monsters, drops, buttonCalc, droprateXTXT, droprateYTXT,monsterTXT, dropsTXT, slash);
        Scene scene = new Scene(layout, 320, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        buttonCalc.setOnAction(e -> {
        	calculate(droprateXTXT.getText(),droprateYTXT.getText(), monsterTXT.getText(), dropsTXT.getText(), layout);
        
        });
    }
	public void calculate(String dropX, String dropyY, String m, String d, GridPane layout) {
		double x = Double.parseDouble(dropX);
		double y = Double.parseDouble(dropyY);
		int monsters = Integer.parseInt(m);
		int drops = Integer.parseInt(d);
		double rate = x/y;
		String result = result(rate, monsters, drops);
		
		Label calculated = new Label(result);
        GridPane.setConstraints(calculated, 3, 32);
        layout.getChildren().remove(10);
        layout.getChildren().add(calculated);
	}
	
	public String result(double rate, int monsters, int drops) {
		 BigInteger ntF = factorial(monsters);
	     BigInteger denom = factorial(drops).multiply(factorial(monsters - drops));

	     BigDecimal ntFBD = new BigDecimal(ntF);
	     BigDecimal denomBD = new BigDecimal(denom);
	     BigDecimal quotient = ntFBD.divide(denomBD, 40, RoundingMode.HALF_UP);

	     BigDecimal restBD = BigDecimal.valueOf(Math.pow(rate, drops) * Math.pow((1d - rate), monsters - drops));
	     double TBT = quotient.multiply(restBD).doubleValue()*100;
	     double result = BigDecimal.valueOf(TBT).setScale(3, RoundingMode.HALF_UP).doubleValue();
	     return String.valueOf(result);
	}
	
	public BigInteger factorial(int monsters) {
        BigInteger res = BigInteger.ONE;
        for (int i = monsters; i>1; i--)
        {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return(res);
    }
}



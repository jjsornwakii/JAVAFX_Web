// Create by JJSORNWAKII //

// Member
// 1.พิตรพิบูล พงษ์พจนธรรม 64010591
// 2.พชรไพริน พุฒซ้อน     64010627
// 3.ศิวกร สุริยะ           64010850
// 4.สิรภพ แสงมี          64010893

import java.io.Serializable;
import org.w3c.dom.Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class Web extends Application implements Serializable {

    String str = "http://google.com";
    Node node;
    double prefW, prefH;

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        WebView webView = new WebView();

        WebEngine webengine = webView.getEngine();
        prefH = primaryStage.getHeight();
        prefW = primaryStage.getWidth();

        webView.setPrefHeight(prefH);
        webView.setPrefWidth(prefW);

        webengine.load("http://www.google.com/");
        
        WebHistory webHistory = webengine.getHistory();

        Pane pane = new Pane();
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        pane.setPrefHeight(prefH);
        pane.setPrefWidth(prefW);

        TextField urlField = new TextField();
        urlField.setStyle("-fx-font-size: 16;");
        urlField.setPrefHeight(30);

        TextArea scrField = new TextArea();
        scrField.setPrefHeight(prefH - 100);
        scrField.setPrefWidth(prefW);

        Button searchBtn = new Button("Search");
        searchBtn.setPrefSize(90, 35);

        Button inspectBtn = new Button("Inspect");
        inspectBtn.setPrefSize(70, 35);

        Button reBtn = new Button("Refresh");
        reBtn.setPrefSize(70, 35);

        Button backBtn = new Button("<");
        backBtn.setPrefSize(50, 35);

        Button nextBtn = new Button(">");
        nextBtn.setPrefSize(50, 35);

        /////// ------ Resize ------ ///////

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            prefW = primaryStage.getWidth();
            webView.setPrefWidth(prefW);
            pane.setPrefWidth(prefW);
            scrField.setPrefWidth(prefW);
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            prefH = primaryStage.getHeight();
            webView.setPrefHeight(prefH);
            scrField.setPrefHeight(prefH - 100);
        });

        /////// ------ Layout ------ ///////

        AnchorPane.setTopAnchor(urlField, 7.0);
        AnchorPane.setLeftAnchor(urlField, 200.0);
        AnchorPane.setRightAnchor(urlField, 190.0);

        AnchorPane.setTopAnchor(searchBtn, 7.0);
        AnchorPane.setRightAnchor(searchBtn, 90.0);


        AnchorPane.setTopAnchor(inspectBtn, 7.0);
        AnchorPane.setRightAnchor(inspectBtn, 10.0);


        AnchorPane.setTopAnchor(reBtn, 7.0);
        AnchorPane.setLeftAnchor(reBtn, 120.0);
        

        AnchorPane.setTopAnchor(backBtn, 7.0);
        AnchorPane.setLeftAnchor(backBtn, 10.0);
        

        AnchorPane.setTopAnchor(nextBtn, 7.0);
        AnchorPane.setLeftAnchor(nextBtn, 65.0);
        

        AnchorPane.setTopAnchor(pane, 55.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        
        pane.getChildren().setAll(webView);

        /////// ------ Action ------ ///////

        searchBtn.setOnAction(e -> {

            String url = urlField.getText();

            webView.getEngine().load(url);
            pane.getChildren().setAll(webView);
            getURL(url);

        });

        inspectBtn.setOnAction(e -> {

            try {

                pane.getChildren().setAll(scrField);
                String html = (String) webView.getEngine().executeScript("document.documentElement.outerHTML");
                scrField.setText(html);
            } catch (Exception ex) {
                System.out.println(e.toString());
            }

        });

        reBtn.setOnAction(e -> {
            webView.getEngine().reload();
            pane.getChildren().setAll(webView);

        });

        backBtn.setOnAction(e -> {
            try {
                webHistory.go(-1);
                // System.out.println(webengine.getLocation());
                urlField.setText(webengine.getLocation());
                pane.getChildren().setAll(webView);
            } catch (Exception ex) {

            }

        });

        nextBtn.setOnAction(e -> {
            try {
                webHistory.go(1);
                urlField.setText(webengine.getLocation());
                pane.getChildren().setAll(webView);
                // System.out.println(webengine.getLocation());
            } catch (Exception ex) {

            }
        });

        /////// ------ Display ------ ///////

        anchorPane.getChildren().addAll(urlField, pane, searchBtn, inspectBtn, reBtn, backBtn, nextBtn);

        primaryStage.setTitle("Javafx Web Browser");

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void getURL(String t) {
        str = t;
    }

    public void getNode(Node n) {
        node = n;
    }

    public void getPrefW(double w) {
        prefW = w;
    }
}
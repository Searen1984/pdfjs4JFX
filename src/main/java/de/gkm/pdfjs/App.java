package de.gkm.pdfjs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;

/**
 * Hello world!
 *
 */
public class App extends Application {
  
  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    WebView webView = new WebView();
    WebEngine engine = webView.getEngine();
    // Change the path according to yours.
    String url = getClass().getResource("/web/viewer.html").toURI().toString();
    // We add our stylesheet.
    engine.setUserStyleSheetLocation(getClass().getResource("/web/viewer.css").toURI().toString());
    engine.setJavaScriptEnabled(true);
    engine.load(url);
    
    engine.getLoadWorker()
    .stateProperty()
    .addListener((obs, oldV, newV) -> {
      if (Worker.State.SUCCEEDED == newV) {
        InputStream stream = null;
        try {
          stream = getClass().getResourceAsStream("/pdfjs_example.pdf");
          byte[] bytes = IOUtils.toByteArray(is);
          // Base64 from java.util
          String base64 = Base64.getEncoder().encodeToString(bytes);
          // This must be ran on FXApplicationThread
          engine.executeScript("openFileFromBase64('" + base64 + "')");
          
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
          if (stream != null) {
            try {
              stream.close();
            } catch (IOException ex) {
              ex.printStackTrace();
            }
          }
        }
      }
    });
    
    primaryStage.setScene(new Scene(webView));
    
    primaryStage.show();
  }
}

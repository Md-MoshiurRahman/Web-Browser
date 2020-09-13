package finalbrowser;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class FinalBrowser extends Application {
    Stage stage;
    Pane root,htmlroot,webroot;
    Button web,html,clear,clearweb;
    Scene scene,htmlscene,webscene;
    TextField textfield,textfieldweb;
    Button go,goweb;
    Button back1,backweb,bckwb,frwrdwb;
    Line line,lineweb;
    Group group1,group2;
    Button btn,reloadweb;
    String str1 = "";
    String s;
    int pathtest=0;
    int key=1;
    ImageView iv1;
    Image image1;
    WebEngine engine;
    WebView webview;
    
    public String goBack()
  {    
    final WebHistory history=engine.getHistory();
    ObservableList<WebHistory.Entry> entryList=history.getEntries();
    int currentIndex=history.getCurrentIndex();

    Platform.runLater(new Runnable() { public void run() { history.go(-1); } });
    return entryList.get(currentIndex>0?currentIndex-1:currentIndex).getUrl();
  }
    
    public String goForward()
  {    
    final WebHistory history=engine.getHistory();
    ObservableList<WebHistory.Entry> entryList=history.getEntries();
    int currentIndex=history.getCurrentIndex();

    Platform.runLater(new Runnable() { public void run() { history.go(1); } });
    return entryList.get(currentIndex<entryList.size()-1?currentIndex+1:currentIndex).getUrl();
  }
    @Override
    public void start(Stage primaryStage) {
        stage=primaryStage;
        root=new Pane();
        web=new Button("Web Browser");
        web.setTranslateX(500);
        web.setTranslateY(350);
        web.setMinHeight(50);
        web.setMinWidth(200);
        html=new Button("HTML Browser");
        html.setTranslateX(500);
        html.setTranslateY(450);
        html.setMinHeight(50);
        html.setMinWidth(200);
        
        
        javafx.scene.image.Image image = new javafx.scene.image.Image("HD-Wallpapers-Ultra-HD.jpg");
 
         
        iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setTranslateX(0);
        iv1.setTranslateY(0);
        iv1.setFitHeight(720);
        iv1.setFitWidth(1500);
         
        web.setOnAction((ActionEvent e)->
                {
            try { 
                ButtonClicked(e);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        html.setOnAction((ActionEvent e)->
                {
            try { 
                ButtonClicked(e);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        root.getChildren().addAll(iv1,web,html);
        
        
        htmlroot=new Pane();
        htmlscene=new Scene(htmlroot,1200,720);
        
        group1= new Group();
        
        textfield=new TextField();
        textfield.setTranslateX(100);
        textfield.setTranslateY(15);
        textfield.setPromptText("Path://");
        textfield.setPrefWidth(700);
        
        go=new Button("Go");
        go.setTranslateX(800);
        go.setTranslateY(15);
        back1=new Button("Back");
        back1.setTranslateX(20);
        back1.setTranslateY(15);
        clear=new Button("Clear");
        clear.setTranslateX(850);
        clear.setTranslateY(15);
        
        
        line=new Line();
        line.setStartX(0);
        line.setStartY(50);
        line.setEndX(1500);
        line.setEndY(50);
        
        group1.getChildren().addAll(textfield,go,line,back1,clear);
        
        
        webroot=new Pane();
        webscene=new Scene(webroot,1200,720);
        
        group2= new Group();
        
        textfieldweb=new TextField();
        textfieldweb.setTranslateX(120);
        textfieldweb.setTranslateY(15);
        textfieldweb.setPromptText("Url-http://");
        textfieldweb.setPrefWidth(700);
        
        goweb=new Button("Go");
        goweb.setTranslateX(820);
        goweb.setTranslateY(15);
        backweb=new Button("Back");
        backweb.setTranslateX(10);
        backweb.setTranslateY(15);
        clearweb=new Button("Clear");
        clearweb.setTranslateX(900);
        clearweb.setTranslateY(15);
        reloadweb=new Button("Reload");
        reloadweb.setTranslateX(950);
        reloadweb.setTranslateY(15);
        bckwb=new Button("<-");
        bckwb.setTranslateX(60);
        bckwb.setTranslateY(15);
        frwrdwb=new Button("->");
        frwrdwb.setTranslateX(90);
        frwrdwb.setTranslateY(15);
        
        
        lineweb=new Line();
        lineweb.setStartX(0);
        lineweb.setStartY(50);
        lineweb.setEndX(1500);
        lineweb.setEndY(50);
        
        webview=new WebView();
        webview.setTranslateX(0);
        webview.setTranslateY(52);
        webview.minWidth(1500);
        webview.prefWidth(1500);
        webview.minHeight(700);
        webview.prefHeight(700);
        
        
        
        group2.getChildren().addAll(textfieldweb,goweb,lineweb,backweb,clearweb,webview,reloadweb,bckwb,frwrdwb);
        
        
        go.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        back1.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        clear.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        textfield.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        goweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        backweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        clearweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        reloadweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        
        textfieldweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        bckwb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        frwrdwb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        htmlscene=new Scene(group1,1200,720);
        webscene=new Scene(group2,1200,720);
        
        
        scene=new Scene(root,1200,720);
        stage.setScene(scene);
        stage.show();
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }

    private void ButtonClicked(ActionEvent e) throws FileNotFoundException, MalformedURLException {
        
            if(e.getSource()==html)
        {
            stage.setScene(htmlscene);
        }
            
            
            if(e.getSource()==web)
        {
            stage.setScene(webscene);
        }
            
            
            
            if(e.getSource()==clear)
        {
            textfield.clear();
            group1.getChildren().clear();
            group1.getChildren().addAll(textfield,go,line,back1,clear);
            stage.setScene(htmlscene);
        }
            
            
            if(e.getSource()==clearweb)
        {
            textfieldweb.clear();
            group2.getChildren().clear();
            group2.getChildren().addAll(textfieldweb,goweb,lineweb,backweb,clearweb,webview,reloadweb,bckwb,frwrdwb);
            stage.setScene(webscene);
        }
            
            
            if(e.getSource()==back1)
        {
            
        htmlroot=new Pane();
        htmlscene=new Scene(htmlroot,1200,720);
        
        group1= new Group();
        
        
        textfield=new TextField();
        textfield.setTranslateX(100);
        textfield.setTranslateY(15);
        textfield.setPromptText("Path://");
        textfield.setPrefWidth(700);
        
        go=new Button("Go");
        go.setTranslateX(800);
        go.setTranslateY(15);
        back1=new Button("Back");
        back1.setTranslateX(20);
        back1.setTranslateY(15);
        clear=new Button("Clear");
        clear.setTranslateX(850);
        clear.setTranslateY(15);
        
        
        line=new Line();
        line.setStartX(0);
        line.setStartY(50);
        line.setEndX(1500);
        line.setEndY(50);
        
        group1.getChildren().addAll(textfield,go,line,back1,clear);
        htmlscene =new Scene(group1,1200,720);
       
        go.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        back1.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        clear.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        textfield.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        stage.setScene(scene);
        
        }
            
            if(e.getSource()==backweb)
        {
            
        webroot=new Pane();
        webscene=new Scene(webroot,1200,720);
        
        group2= new Group();
        
        
        textfieldweb=new TextField();
        textfieldweb.setTranslateX(120);
        textfieldweb.setTranslateY(15);
        textfieldweb.setPromptText("Url-http://");
        textfieldweb.setPrefWidth(700);
        
        goweb=new Button("Go");
        goweb.setTranslateX(820);
        goweb.setTranslateY(15);
        backweb=new Button("Back");
        backweb.setTranslateX(10);
        backweb.setTranslateY(15);
        clearweb=new Button("Clear");
        clearweb.setTranslateX(900);
        clearweb.setTranslateY(15);
        reloadweb=new Button("Reload");
        reloadweb.setTranslateX(950);
        reloadweb.setTranslateY(15);
        bckwb=new Button("<-");
        bckwb.setTranslateX(60);
        bckwb.setTranslateY(15);
        frwrdwb=new Button("->");
        frwrdwb.setTranslateX(90);
        frwrdwb.setTranslateY(15);
        
        
        lineweb=new Line();
        lineweb.setStartX(0);
        lineweb.setStartY(50);
        lineweb.setEndX(1500);
        lineweb.setEndY(50);
        
        webview=new WebView();
        webview.setTranslateX(0);
        webview.setTranslateY(52);
        webview.minWidth(1500);
        webview.prefWidth(1500);
        webview.minHeight(700);
        webview.prefHeight(700);
        
        group2.getChildren().addAll(textfieldweb,goweb,lineweb,backweb,clearweb,webview,reloadweb,bckwb,frwrdwb);
        webscene =new Scene(group2,1200,720);
       
        goweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        backweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        clearweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        reloadweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        
        textfieldweb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        bckwb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        frwrdwb.setOnAction((ActionEvent e1)->
                {
            try { 
                ButtonClicked(e1);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        
        stage.setScene(scene);
        
        }
            
            
        
        if(e.getSource()==go)
        {
 
            String path=textfield.getText();
            File file1;
            if(path.endsWith(".html"))
            {
                s="";
                file1 = new File(path);
                Scanner reader1 = new Scanner(file1);
                
                
           
        while (reader1.hasNextLine()) {
            s = s + reader1.nextLine() + '\n';
        }
        
        
            }
             
            else
            {
                s="Wrong Path";
            }
             
            group1.getChildren().clear();
            group1.getChildren().addAll(textfield,go,line,back1,clear);
            
            Label newlabel;
            
            if(path.endsWith(".html")==false)
            {
                newlabel = new Label(s);
                newlabel.setLayoutX(100);
                newlabel.setLayoutY(200);       
                newlabel.setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 100));   
                newlabel.setMinSize(100, 50);
                    
                group1.getChildren().add(newlabel);
                
                
            }
            if((s.startsWith("<html") && s.endsWith("</html>\n"))==false)
            {
                s=null;
            }
            
            if(s.startsWith("<html>"))
            {
                if(s.endsWith("</html>\n"))
                {
                    s=s.substring(s.indexOf("<body>") + 6, s.indexOf("</body>"));
                }
                
            }
            
        int a = 0, b = 0, c=0 ,d=0 ,f=0,g=0;
        String s1;
        
        int i, ind = 0,ind2=0,ind3=0,ind4=0,ind5=0,ind6=0,ind7=0;
        int col = 4, row = 70;
        for (int j = 0; j < s.length(); j++) {
            if (s.substring(j).startsWith("<p>") || s.substring(j).startsWith("<b>")||  s.substring(j).startsWith("<br>")) {
                a++;
                
            }
            if (s.substring(j).startsWith("<textarea")) {
                b++;
                
            }
            if (s.substring(j).startsWith("<td>") || s.substring(j).startsWith("</a>")) {
                c++;
                
            }
            if(s.substring(j).startsWith("<input type=\"radio\""))
            {
                d++;
            }
            if(s.substring(j).startsWith("<li>"))
            {
                f++;
            }
            if(s.substring(j).startsWith("<img"))
            {
                g++;
            }
        }
        
        Text[] text = new Text[a+f];
        TextArea[] textarea = new TextArea[b];
        Label[] label=new Label[c];
        RadioButton [] rb=new RadioButton[d];
        Circle [] circle=new Circle[f];
        ImageView [] imageview=new ImageView[g];
        ToggleGroup group = new ToggleGroup();
        btn=new Button();
        
        for (i = 0; i < s.length();) {
            
            if (s.substring(i).startsWith("<br>")) {
               
                i = s.indexOf("<br>", i) + 1;
                col=4;
                
                row+=15;
                i++;
                
            } 
            

            else if (s.substring(i).startsWith("<p>")) {
                i++;
                col=4;
                s1="";
                if(s.indexOf("</p>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</p>", i));
                }
                
                
                text[ind] = new Text(s1);
                  
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                text[ind].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                group1.getChildren().add(text[ind]);
                if(s.indexOf("</p>", i)==s.indexOf("<", i))
                {
                    col=4;
                    row+=30;
                }
                i = s.indexOf("<", i);

                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<b>")) {
                i++;
                s1="";
                if(s.indexOf("</b>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</b>", i));
                }
                
                
                text[ind] = new Text(s1);
                text[ind].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                
                group1.getChildren().add(text[ind]);
                
                if(s.indexOf("</b>", i)==s.indexOf("<", i))
                {
                    col+=s1.length()*7.2;
                }
                i = s.indexOf("</b>", i) + 1;
                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<i>")) {
                
                i++;
                s1="";
                if(s.indexOf("</i>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</i>", i));
                }
                text[ind] = new Text(s1);
                text[ind].setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                
                
                javafx.scene.layout.GridPane.setConstraints(text[ind], col, row);
                
                group1.getChildren().add(text[ind]);
                if(s.indexOf("</i>", i)==s.indexOf("<", i))
                {
                    col+=s1.length()*7.2;
                }
                i = s.indexOf("</i>", i) + 1;
                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<img")) {
                s1 = s.substring(s.indexOf("src", i) + 5, s.indexOf("style", i)-2);
                
                int x1=Integer.parseInt(s.substring(s.indexOf("width:", i) + 6, s.indexOf("px", i)));
                i = s.indexOf("px", i)+1;
                int x2=Integer.parseInt(s.substring(s.indexOf("height:", i) + 7, s.indexOf("px", i)));
                
                File file = new File(s1);
 

                String localUrl = file.toURI().toURL().toString();
 
                Image image = new Image(localUrl);
                
                imageview[ind7] = new ImageView();
                imageview[ind7].setImage(image);
                imageview[ind7].setTranslateX(col);
                imageview[ind7].setTranslateY(row);
                imageview[ind7].setFitHeight(x1);
                imageview[ind7].setFitWidth(x2);
                
                group1.getChildren().add(imageview[ind7]);
                col+=x2;
                
                i = s.indexOf(">", i) + 1;
                if(s.substring(i).startsWith("<br>"))
                {
                    row+=x1;
                }
                ind7++;
                
            }
            
            else if (s.substring(i).startsWith("<li>")) {
                i++;
                s1="";
                if(s.indexOf("</li>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</li>", i));
                }
                
                
                circle[ind6]=new Circle(2.5,Color.BLACK);
                circle[ind6].setCenterX(col+5);
                circle[ind6].setCenterY(row-5);
                group1.getChildren().add(circle[ind6]);
                ind6++;
                
                col+=12;
                text[ind] = new Text(s1);
                text[ind].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                
                group1.getChildren().add(text[ind]);
                
                if(s.indexOf("</li>", i)==s.indexOf("<", i))
                {
                    col=4;
                    row+=15;
                }
                i = s.indexOf("</li>", i) + 1;
                
                

                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<textarea")) {
                
                i++;
                s1="";
                if(s.indexOf("</textarea>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) +1, s.indexOf("</textarea>", i));
                }
                
                String s1w=s.substring(s.indexOf("cols", i) +6,s.indexOf("rows", i) -2);
                String s1h=s.substring(s.indexOf("rows", i) +6,s.indexOf(">", i) -1);
                textarea[ind2] = new TextArea(s1);
                textarea[ind2].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                textarea[ind2].setMinSize(Integer.parseInt(s1w)*7.2, Integer.parseInt(s1h)*10.0);
                textarea[ind2].setLayoutX(col);
                textarea[ind2].setLayoutY(row);
                
                group1.getChildren().add(textarea[ind2]);
                ind2++;
                
                if(s.indexOf("</textarea>", i)==s.indexOf("<", i))
                {
                    col+=Integer.parseInt(s1w)*7.2;
                    row+=Integer.parseInt(s1h)*10;
                }
                i = s.indexOf("</textarea>", i) + 11;
            }
            
            else if (s.substring(i).startsWith("<form")) {
                
                s1 = s.substring(s.indexOf(">", i) + 3, s.indexOf("/form>", i));
             
                
                for(int j=0;j<s1.length();)
                {
                    
                    if(s1.substring(j).startsWith("<input type=\"radio\""))
                    {
                        String stmp=s1.substring(s1.indexOf("> ",j)+ 2, s1.indexOf("<br>", j));
                        
                        j=s1.indexOf("<", j) + 1;
                        rb[ind4] = new RadioButton(stmp);
                        rb[ind4].setToggleGroup(group);
                        if(ind4==0)
                        {
                            rb[ind4].setSelected(true);
                        }
                        
                        rb[ind4].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                        rb[ind4].setLayoutX(col);
                        rb[ind4].setLayoutY(row);
                        
                        group1.getChildren().add(rb[ind4]);
                        ind4++;
                        
                        col+=stmp.length()*15;
                        
                    }
                    else if(s1.substring(j).startsWith("<br>"))
                            {
                                col=4;
                                row+=15;
                                j=s1.indexOf("<br>", j) + 4;
                            }
                    else
                    {
                       j++; 
                    }
                    
                }
                i = s.indexOf("</form>", i) + 1;
                row+=20;
             
            }
            
            
            
            
            else if (s.substring(i).startsWith("<table>")) {
                
                
                
                String s2=s.substring(s.indexOf("<tr>", i) + 3, s.indexOf("</table>", i)-3);
                
                
                int count=0;
                for (int j = 0; j < s2.length(); j++)
                {
                    if (s2.substring(j).startsWith("<td>")) {
                    count++;
                }
                
                }
                
                int minind3=ind3;
                String tmps1=new String("");
                double maxstring=tmps1.length();
                for (int k = 0; k < s2.length(); )
                {
                    if (s2.substring(k).startsWith("<td>")) {
                        
                        s1 = s2.substring(s2.indexOf("<td>", k) + 4, s2.indexOf("</td>", k));
                        if(s1.length()>maxstring)
                        {
                            maxstring=s1.length();
                        }
                        
                        k = s2.indexOf("</td>", k) + 1;
                       
                    }
                    else
                    {
                        k++;
                    }
                }
            
                for (int k = 0; k < s2.length(); )
                {
                    if (s2.substring(k).startsWith("<td>")) {
                        
                        s1 = s2.substring(s2.indexOf("<td>", k) + 4, s2.indexOf("</td>", k));
                        
                        label[ind3] = new Label(s1);
                       
                        
                        
                        label[ind3].setStyle("-fx-border-color: black; ");
                        label[ind3].setMinSize(maxstring*10, 19);
                        
                        label[ind3].setLayoutX(col);
                        label[ind3].setLayoutY(row);
                        
                        
                        group1.getChildren().add(label[ind3]);
                        
                        ind3++;
                        
                        col+=maxstring*10;
                        k = s2.indexOf("</td>", k) + 1;
                    }
                    else if(s2.substring(k).startsWith("<tr>"))
                    {
                        row+=19;
                        col=4;
                        k++;
                    }
                    
                    else
                    {
                        k++;
                    }
                }
                
                i = s.indexOf("</tr>", i) + 1;
                row+=19;
                col=4;
            }
            
            else if (s.substring(i).startsWith("</table>"))
            {
                col=4;
                row+=15;
                i++;
            }
            
            else if (s.substring(i).startsWith("<a href"))
            {
                s1 = s.substring(s.indexOf("href", i) + 6, s.indexOf(">", i)-1);
                

                File file = new File(s1);
                
                Scanner reader = new Scanner(file);
                
                
                while (reader.hasNextLine()) {
                str1 = str1 + reader.nextLine() + '\n';
                }
                
                String s2=s.substring(s.indexOf(">", i) + 1, s.indexOf("</a>", i));
                
                btn=new Button(s2);
                btn.setStyle("-fx-base: white;");
                btn.setFont(Font.font("Verdana", FontWeight.THIN, 12));
                btn.setLayoutX(col);
                btn.setLayoutY(row);
                btn.setMinSize(s2.length()*7.2, 19);
                group1.getChildren().add(btn);
                
                btn.setOnAction((ActionEvent e1)->
                {
                   try { 
                   ButtonClicked(e1);
                }  catch (FileNotFoundException ex) {
                   Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (MalformedURLException ex) {
                        Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                i = s.indexOf("</a>", i) + 1;
                col+=s2.length()*7.2;
                ind5++;
             
            }
            
            else
            {
                i++;
            }

          }
        stage.setScene(htmlscene);
        
        }
        
        
        if(e.getSource()==textfield)
        {
            
            String path=textfield.getText();
            File file1;
            if(path.endsWith(".html"))
            {
                s="";
                file1 = new File(path);
                Scanner reader1 = new Scanner(file1);
                
                
           
        while (reader1.hasNextLine()) {
            s = s + reader1.nextLine() + '\n';
        }
        
        
            }
             
            else
            {
                s="Wrong Path";
            }
             
            group1.getChildren().clear();
            group1.getChildren().addAll(textfield,go,line,back1,clear);
            
            Label newlabel;
            
            if(path.endsWith(".html")==false)
            {
                newlabel = new Label(s);
                newlabel.setLayoutX(100);
                newlabel.setLayoutY(200);       
                newlabel.setFont(Font.font("Verdana", FontWeight.BOLD,FontPosture.ITALIC, 100));   
                newlabel.setMinSize(100, 50);
                    
                group1.getChildren().add(newlabel);
                
                
            }
            if((s.startsWith("<html") && s.endsWith("</html>\n"))==false)
            {
                s=null;
            }
            
            if(s.startsWith("<html>"))
            {
                if(s.endsWith("</html>\n"))
                {
                    s=s.substring(s.indexOf("<body>") + 6, s.indexOf("</body>"));
                }
                
            }
            
        int a = 0, b = 0, c=0 ,d=0 ,f=0,g=0;
        String s1;
        
        int i, ind = 0,ind2=0,ind3=0,ind4=0,ind5=0,ind6=0,ind7=0;
        int col = 4, row = 70;
        for (int j = 0; j < s.length(); j++) {
            if (s.substring(j).startsWith("<p>") || s.substring(j).startsWith("<b>")||  s.substring(j).startsWith("<br>")) {
                a++;
                
            }
            if (s.substring(j).startsWith("<textarea")) {
                b++;
                
            }
            if (s.substring(j).startsWith("<td>") || s.substring(j).startsWith("</a>")) {
                c++;
                
            }
            if(s.substring(j).startsWith("<input type=\"radio\""))
            {
                d++;
            }
            if(s.substring(j).startsWith("<li>"))
            {
                f++;
            }
            if(s.substring(j).startsWith("<img"))
            {
                g++;
            }
        }
        
        Text[] text = new Text[a+f];
        TextArea[] textarea = new TextArea[b];
        Label[] label=new Label[c];
        RadioButton [] rb=new RadioButton[d];
        Circle [] circle=new Circle[f];
        ImageView [] imageview=new ImageView[g];
        ToggleGroup group = new ToggleGroup();
        btn=new Button();
        
        for (i = 0; i < s.length();) {
            
            if (s.substring(i).startsWith("<br>")) {
               
                i = s.indexOf("<br>", i) + 1;
                col=4;
                
                row+=15;
                i++;
                
            } 
            

            else if (s.substring(i).startsWith("<p>")) {
                i++;
                col=4;
                s1="";
                if(s.indexOf("</p>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</p>", i));
                }
                
                
                text[ind] = new Text(s1);
                  
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                text[ind].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                group1.getChildren().add(text[ind]);
                if(s.indexOf("</p>", i)==s.indexOf("<", i))
                {
                    col=4;
                    row+=30;
                }
                i = s.indexOf("<", i);

                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<b>")) {
                i++;
                s1="";
                if(s.indexOf("</b>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</b>", i));
                }
                
                
                text[ind] = new Text(s1);
                text[ind].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                
                group1.getChildren().add(text[ind]);
                
                if(s.indexOf("</b>", i)==s.indexOf("<", i))
                {
                    col+=s1.length()*7.2;
                }
                i = s.indexOf("</b>", i) + 1;
                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<i>")) {
                
                i++;
                s1="";
                if(s.indexOf("</i>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</i>", i));
                }
                text[ind] = new Text(s1);
                text[ind].setFont(Font.font("Verdana", FontPosture.ITALIC, 12));
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                
                
                javafx.scene.layout.GridPane.setConstraints(text[ind], col, row);
                
                group1.getChildren().add(text[ind]);
                if(s.indexOf("</i>", i)==s.indexOf("<", i))
                {
                    col+=s1.length()*7.2;
                }
                i = s.indexOf("</i>", i) + 1;
                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<img")) {
                s1 = s.substring(s.indexOf("src", i) + 5, s.indexOf("style", i)-2);
                
                int x1=Integer.parseInt(s.substring(s.indexOf("width:", i) + 6, s.indexOf("px", i)));
                i = s.indexOf("px", i)+1;
                int x2=Integer.parseInt(s.substring(s.indexOf("height:", i) + 7, s.indexOf("px", i)));
                
                File file = new File(s1);
 

                String localUrl = file.toURI().toURL().toString();
 
                Image image = new Image(localUrl);
                
                imageview[ind7] = new ImageView();
                imageview[ind7].setImage(image);
                imageview[ind7].setTranslateX(col);
                imageview[ind7].setTranslateY(row);
                imageview[ind7].setFitHeight(x1);
                imageview[ind7].setFitWidth(x2);
                
                group1.getChildren().add(imageview[ind7]);
                col+=x2;
                
                i = s.indexOf(">", i) + 1;
                if(s.substring(i).startsWith("<br>"))
                {
                    row+=x1;
                }
                ind7++;
                
            }
            
            else if (s.substring(i).startsWith("<li>")) {
                i++;
                s1="";
                if(s.indexOf("</li>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) + 1, s.indexOf("</li>", i));
                }
                
                
                circle[ind6]=new Circle(2.5,Color.BLACK);
                circle[ind6].setCenterX(col+5);
                circle[ind6].setCenterY(row-5);
                group1.getChildren().add(circle[ind6]);
                ind6++;
                col+=12;
                text[ind] = new Text(s1);
                text[ind].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                text[ind].setLayoutX(col);
                text[ind].setLayoutY(row);
                
                group1.getChildren().add(text[ind]);
                
                if(s.indexOf("</li>", i)==s.indexOf("<", i))
                {
                    col=4;
                    row+=15;
                }
                i = s.indexOf("</li>", i) + 1;
                
                

                ind++;
                
            }
            
            else if (s.substring(i).startsWith("<textarea")) {
                
                i++;
                s1="";
                if(s.indexOf("</textarea>", i)==s.indexOf("<", i))
                {
                    s1 = s.substring(s.indexOf(">", i) +1, s.indexOf("</textarea>", i));
                }
                
                String s1w=s.substring(s.indexOf("cols", i) +6,s.indexOf("rows", i) -2);
                String s1h=s.substring(s.indexOf("rows", i) +6,s.indexOf(">", i) -1);
                textarea[ind2] = new TextArea(s1);
                textarea[ind2].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                textarea[ind2].setMinSize(Integer.parseInt(s1w)*7.2, Integer.parseInt(s1h)*10.0);
                textarea[ind2].setLayoutX(col);
                textarea[ind2].setLayoutY(row);
                
                group1.getChildren().add(textarea[ind2]);
                ind2++;
                
                if(s.indexOf("</textarea>", i)==s.indexOf("<", i))
                {
                    col+=Integer.parseInt(s1w)*7.2;
                    row+=Integer.parseInt(s1h)*10;
                }
                i = s.indexOf("</textarea>", i) + 11;
            }
            
            else if (s.substring(i).startsWith("<form")) {
                
                s1 = s.substring(s.indexOf(">", i) + 3, s.indexOf("/form>", i));
             
                
                for(int j=0;j<s1.length();)
                {
                    
                    if(s1.substring(j).startsWith("<input type=\"radio\""))
                    {
                        String stmp=s1.substring(s1.indexOf("> ",j)+ 2, s1.indexOf("<br>", j));
                        
                        j=s1.indexOf("<", j) + 1;
                        rb[ind4] = new RadioButton(stmp);
                        rb[ind4].setToggleGroup(group);
                        if(ind4==0)
                        {
                            rb[ind4].setSelected(true);
                        }
                        
                        rb[ind4].setFont(Font.font("Verdana", FontWeight.THIN, 12));
                        rb[ind4].setLayoutX(col);
                        rb[ind4].setLayoutY(row);
                        
                        group1.getChildren().add(rb[ind4]);
                        ind4++;
                        
                        col+=stmp.length()*15;
                        
                    }
                    else if(s1.substring(j).startsWith("<br>"))
                            {
                                col=4;
                                row+=15;
                                j=s1.indexOf("<br>", j) + 4;
                            }
                    else
                    {
                       j++; 
                    }
                    
                }
                i = s.indexOf("</form>", i) + 1;
                row+=20;
             
            }
            
            
            
            
            else if (s.substring(i).startsWith("<table>")) {
                
                
                
                String s2=s.substring(s.indexOf("<tr>", i) + 3, s.indexOf("</table>", i)-3);
                
                
                int count=0;
                for (int j = 0; j < s2.length(); j++)
                {
                    if (s2.substring(j).startsWith("<td>")) {
                    count++;
                }
                
                }
                
                int minind3=ind3;
                String tmps1=new String("");
                double maxstring=tmps1.length();
                for (int k = 0; k < s2.length(); )
                {
                    if (s2.substring(k).startsWith("<td>")) {
                        
                        s1 = s2.substring(s2.indexOf("<td>", k) + 4, s2.indexOf("</td>", k));
                        if(s1.length()>maxstring)
                        {
                            maxstring=s1.length();
                        }
                        
                        k = s2.indexOf("</td>", k) + 1;
                       
                    }
                    else
                    {
                        k++;
                    }
                }
            
                for (int k = 0; k < s2.length(); )
                {
                    if (s2.substring(k).startsWith("<td>")) {
                        
                        s1 = s2.substring(s2.indexOf("<td>", k) + 4, s2.indexOf("</td>", k));
                        
                        label[ind3] = new Label(s1);
                       
                        
                        
                        label[ind3].setStyle("-fx-border-color: black; ");
                        label[ind3].setMinSize(maxstring*10, 19);
                        
                        label[ind3].setLayoutX(col);
                        label[ind3].setLayoutY(row);
                        
                        
                        group1.getChildren().add(label[ind3]);
                        
                        ind3++;
                        
                        col+=maxstring*10;
                        k = s2.indexOf("</td>", k) + 1;
                    }
                    else if(s2.substring(k).startsWith("<tr>"))
                    {
                        row+=19;
                        col=4;
                        k++;
                    }
                    
                    else
                    {
                        k++;
                    }
                }
                
                i = s.indexOf("</tr>", i) + 1;
                row+=19;
                col=4;
            }
            
            else if (s.substring(i).startsWith("</table>"))
            {
                col=4;
                row+=15;
                i++;
            }
            
            else if (s.substring(i).startsWith("<a href"))
            {
                s1 = s.substring(s.indexOf("href", i) + 6, s.indexOf(">", i)-1);
                

                File file = new File(s1);
                
                Scanner reader = new Scanner(file);
                
                
                while (reader.hasNextLine()) {
                str1 = str1 + reader.nextLine() + '\n';
                }
                
                String s2=s.substring(s.indexOf(">", i) + 1, s.indexOf("</a>", i));
                
                btn=new Button(s2);
                btn.setStyle("-fx-base: white;");
                btn.setFont(Font.font("Verdana", FontWeight.THIN, 12));
                btn.setLayoutX(col);
                btn.setLayoutY(row);
                btn.setMinSize(s2.length()*7.2, 19);
                group1.getChildren().add(btn);
                
                btn.setOnAction((ActionEvent e1)->
                {
                   try { 
                   ButtonClicked(e1);
                }  catch (FileNotFoundException ex) {
                   Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (MalformedURLException ex) {
                        Logger.getLogger(FinalBrowser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                i = s.indexOf("</a>", i) + 1;
                col+=s2.length()*7.2;
                ind5++;
             
            }
            
            else
            {
                i++;
            }

          }
        stage.setScene(htmlscene);
        
        }
        
        
        if(e.getSource()==goweb)
        {
            
            String path=textfieldweb.getText();
            group2.getChildren().clear();
            group2.getChildren().addAll(textfieldweb,goweb,lineweb,backweb,clearweb,webview,reloadweb,bckwb,frwrdwb);
            
            engine= webview.getEngine();
            
            if(path.startsWith("http://"))
            {
                engine.load(path);
            }
            else
            {
            
                engine.load("http://"+path);
            } 
            
            
            stage.setScene(webscene);
        
        }
        
        if(e.getSource()==textfieldweb)
        {
            
            String path=textfieldweb.getText();
            group2.getChildren().clear();
            group2.getChildren().addAll(textfieldweb,goweb,lineweb,backweb,clearweb,webview,reloadweb,bckwb,frwrdwb);
            
            engine= webview.getEngine();
            
            if(path.startsWith("http://"))
            {
                engine.load(path);
            }
            else
            {
            
                engine.load("http://"+path);
            } 
            
            
            stage.setScene(webscene);
        
        }
        
        if(e.getSource()==reloadweb)
        {
            engine.reload();
            stage.setScene(webscene);
        }
        if(e.getSource()==bckwb)
        {
            goBack();
            stage.setScene(webscene);
        }
        if(e.getSource()==frwrdwb)
        {
            goForward();
            stage.setScene(webscene);
        }
        
       
        
        if(e.getSource()==btn)
                {
                s=new String(str1);
                System.out.println(s);
                key=0;
                
                }
        
    }
    
}


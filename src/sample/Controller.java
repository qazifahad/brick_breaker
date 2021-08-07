package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Circle circle;
    public Rectangle rectangle;
    public GridPane gridpane;
    public Pane pane;
    public int score=0;
    public Label text;
    boolean[][] b;
    double dx=2;
    double dy=2;
    Timeline t;
    Rectangle rect;
    ObservableList<Node> noOfElements;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rectangle.requestFocus();
        rectangle.setFocusTraversable(true);
        b=new boolean[3][3];
       KeyFrame k=new KeyFrame(Duration.millis(10),
               event -> {
                   circle.setLayoutX(circle.getLayoutX()+dx);
                   circle.setLayoutY(circle.getLayoutY()+dy);
                   if(circle.getLayoutX()< 30| circle.getLayoutX()>600-30)
                   {
                       dx=-dx;
                   }
                   if(circle.getLayoutY()<30)
                   {
                       dy=-dy;
                   }
                   if(circle.getLayoutY()>575-30)
                   {
                       t.stop();
                       gameOver();
                   }

                   Shape intersect=Shape.intersect(circle,rectangle);
                   if(intersect.getBoundsInParent().getWidth()!=-1)
                   { dy=-dy;}
                  for(Node child:gridpane.getChildren())
                  {

                      Shape intersect2=Shape.intersect(circle, (Shape) child);

                      if(intersect2.getBoundsInParent().getWidth()!=-1)
                      {
                          rect= (Rectangle) child;
                          dy=-dy;
                          score=score+10;
                          text.setText(String.valueOf(score));
                          child.setVisible(false);

                      }
                  }

                    gridpane.getChildren().remove(rect);
                    noOfElements=gridpane.getChildren();
                    if(noOfElements.isEmpty())
                    {
                        t.stop();
                        Win();
                    }

       });
       t=new Timeline(k);
      t.setCycleCount(Timeline.INDEFINITE);
      t.play();


    }
    private void Win() {

        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("YOU WIN");
        al.setContentText("Your score is  "+score);
        al.setHeaderText(null);
        al.setOnHidden(event-> Platform.exit());
        al.show();
    }

    private void gameOver() {

        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("GAME OVER");
        al.setContentText("Your score is  "+score);
        al.setHeaderText(null);
        al.setOnHidden(event-> Platform.exit());
        al.show();
    }

    public void move(KeyEvent keyEvent) {
        rectangle.requestFocus();
        rectangle.setFocusTraversable(true);
        switch (keyEvent.getCode()){
            case LEFT:rectangle.setX(rectangle.getX()-20);
            break;
            case RIGHT:rectangle.setX(rectangle.getX()+20);
            break;
            case A:rectangle.setX(rectangle.getX()-20);
                break;
            case D:rectangle.setX(rectangle.getX()+20);
                break;
        }
    }
}

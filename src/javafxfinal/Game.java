//File Name : Game.java 
//Author:  LiuLin  Student Number : 3013218111
//Class Day TuesDay     Class Year : 2014
//Assignment number : final
//Description: combine the main game togther with the little controls to make it cofortable
//                  to the player.use the button to cotact with the palyer to set the game
//Known Bugs:
//Fulture Improvements:
//  --improvements idea#1 : use more beautiful controls items to make the game attractive
//  -- impovements idea#2 : use the seft-defined progressbar to do some more flexible operations 
 
package javafxfinal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafxfinal.Tile;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.StackPane;
import javafxfinal.Grid;
import java.util.concurrent.TimeUnit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


// Class Description : combine the main game together with the progressbar and 
//                             layout the button to control the game , show the text to prompt
//                             the player to make the game more comfortable to play
public class Game extends Application {

    public static void main(String[] args) {
        Game.launch(args);
    }

    public void start(Stage stg) {
        stg.setTitle("Final Project");
        Group group = new Group();
        Grid[] grid = new Grid[3];
        ATile aa = new ATile ( Color.RED );
        MTile mm = new MTile ( Color.GREEN );
        CubeTile cu = new CubeTile ( Color.BLUE );
        CircleTile rr = new CircleTile ( Color.GOLD );
        XTile xx = new XTile ( Color.PINK );
        CrossTile ll = new CrossTile ( Color.BROWN );
        CombineTile bb = new CombineTile ( Color.DARKVIOLET );
        HBox vv = new HBox ();
        vv.getChildren().add ( aa );
        vv.getChildren().add ( mm);
        vv.getChildren().add ( cu );
        vv.getChildren().add ( xx );
        vv.getChildren().add ( rr );
        vv.getChildren().add ( ll );
        vv.getChildren().add ( bb );
        group.getChildren().add ( vv );
        Button [] btn = new Button[3];
        String [] str = {"easy" , "normal" , "hard"}; 
        for ( int i = 0 ; i < 3 ; i++ )
        {
            btn[i]=new Button ( str[i] );
        }
         btn[0].setOnAction( new EventHandler<ActionEvent> ()  
            {
                public void handle ( ActionEvent e )
                {
                    group.getChildren().clear();
                    createGrid( grid , 0 , group );
                }
            });
          btn[1].setOnAction( new EventHandler<ActionEvent> ()  
            {
                public void handle ( ActionEvent e )
                {
                    group.getChildren().clear();
                    createGrid( grid , 1 , group );
                }
            });
           btn[2].setOnAction( new EventHandler<ActionEvent> ()  
            {
                public void handle ( ActionEvent e )
                {
                    group.getChildren().clear();
                    createGrid( grid , 2 , group );
                }
            });
           Text title = new Text ( "Choose the level of the game.");
           title.setFont ( new Font ( 36 ));
           AnchorPane a = new AnchorPane ();
           AnchorPane.setTopAnchor ( title , 100.0 );
           AnchorPane.setTopAnchor ( btn[0] , 150.0 );
           AnchorPane.setLeftAnchor ( btn[0] , 100.0 );
           AnchorPane.setTopAnchor ( btn[1] , 200.0 );
           AnchorPane.setLeftAnchor ( btn[1] , 100.0 );
           AnchorPane.setLeftAnchor ( btn[2] , 100.0 );
           AnchorPane.setTopAnchor ( btn[2] , 250.0 );
           for ( int i = 0 ; i < 3 ; i++ ) a.getChildren().add ( btn[i]  );
           a.getChildren().add ( title );
           group.getChildren().add ( a );
        //createGrid(grid, 0, group);
        stg.setScene(new Scene(new StackPane(group), 800, 700));
        stg.show();
    }

    public void createGrid(Grid[] grid, int level, Group group) {
        // the condition to stop the procedure
        if (level == 3) {
            group.getChildren().clear();
            Text t = new Text("Happy Ending!");
            t.setFont(new Font(80));
            group.getChildren().add(t);
            return;
        }

        // init
        int[] row = {7, 8, 10};
        int[] col = {6, 7, 7};
        int[] kind = {12, 13, 14};

        // create the grid 
        grid[level] = new Grid(row[level], col[level], kind[level], group);

        // create the bar
        ProgressBar bar = new ProgressBar();
        bar.setPrefWidth(200);
        bar.setLayoutX(500);
        bar.setLayoutY(150);
        Task task1 = createTask();
        bar.progressProperty().bind(task1.progressProperty().multiply(100));
        Thread thread = new Thread(task1);
        thread.setDaemon(true);
        group.getChildren().add(bar);

        // create the button
        Button btn = new Button(" Start");
        btn.setLayoutX(550);
        btn.setLayoutY(550);
        group.getChildren().add(btn);

        //create fail words 
        Text fail = new Text("Game Over");
        fail.setFont(new Font(80));

        // create the rate
        Text rate = new Text("0%");
        rate.setLayoutX(580);
        rate.setLayoutY(150);
        group.getChildren().add(rate);

        // create the title        
        Text title = new Text("level" + new Integer(level + 1).toString());
        title.setFont(new Font(70));
        title.setLayoutX(500);
        title.setLayoutY(50);
        group.getChildren().add(title);

            // set the listener of cnt
        grid[level].cnt.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("cnt changed ! ");
                bar.progressProperty().bind(task1.progressProperty().subtract((row[level] * col[level] - grid[level].cnt.get()) * 0.00005).multiply(100));
                if (grid[level].cnt.get() == 0) {
                    group.getChildren().clear();
                    thread.stop();
                    createGrid(grid, level + 1, group);
                }
            }
        });

        //set the listener of Button
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                group.getChildren().add(grid[level]);
                thread.start();
            }
        });

        //set the listener of Bar 
        bar.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newvalue) {
                System.out.println(" progress changed ! ");
                Integer i = new Integer((int) (bar.progressProperty().add(0.005).multiply(100).get()));
                rate.setText(i.toString() + "%");
                System.out.println(bar.progressProperty().get());
                if (bar.progressProperty().get() > 0.99) {
                    group.getChildren().clear();
                    createGrid(grid, level, group);
                    thread.stop();
                    //thread.destroy();
                }
            }
        });

    }

    private Task createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int iterations = 0; iterations <= 10000; iterations++) {
                    if (isCancelled()) {
                        break;
                    }
                    TimeUnit.SECONDS.sleep(1);
                    updateProgress(iterations, 10000);
                }
                return null;
            }
        };
    }
}
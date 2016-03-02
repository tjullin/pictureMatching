//File Name : Grid.java
//Author : LiuLin  Student Number: 3013218111
//Class Day: Thursday    Class Year : 2014
//Assignment Number : final
//Description : this file contians the main body of the game,contains the class that make
//                   both the layout and action of the game except the little items
//Known Bugs:1.The num of the kinds is not true
//Fulture improvements:
//  --improvement idea#1  use more useful algorithm to make the game more random
//  --improvement idea#2 make the connection beautiful
//  --improvement idea#3 find some new rules to expands the game


package javafxfinal;

import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafxfinal.Tile;
import java.lang.Math;
import javafx.animation.FadeTransition;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

// Class Description: the class owns the main body of the game,contians the layout 
//                            the layout of the TIle , the handler of the event ,can be regarded
//                            as a completed part of a single game
public class Grid extends GridPane
{
        private Tile [] t;
        private  boolean[][] exits;
        private int [][] order;
        public  int flag,c_x1,c_x2 , c_y1 , c_y2;
        private int row , col;
        public IntegerProperty  cnt = new SimpleIntegerProperty ();
        public static int click_cnt;
       Grid ( int row , int col , int kind  , Group g )
       { 
           click_cnt = 0;
           int count = 0;
           row += 2 ;
           col += 2;
           this.flag = 0;
           this.row = row;
           this.col = col;
           t = new Tile[(row-2 )* ( col - 2 ) ];
           exits = new boolean [row][col];
           order = new int[row][col];
            for ( int i = 0 ; i < row ; i++ )
                for ( int j = 0 ; j < col ; j++ )
                {
                    if ( i == 0 || j == 0 || i == row-1 || j == col -1  ) 
                    {
                        exits[i][j] = false;
                        order[i][j] = -1;
                    }
                    else 
                        exits[i][j] = true;
                }
           //init
            int numOfEach =(int) (row-2 )*( col - 2 )/kind;
            if ( (numOfEach &1) == 1  ) numOfEach++;
            int start = 0;
            int end = numOfEach;
            Color []c = new Color[4];
            c[0] = Color.GOLD;
            c[1] = Color.RED;
            c[2] = Color.BLUE;
            for ( int i = 0 ; i < 3 ; i++ )
            {
                for ( int j = start ; j <  end && j < ( row- 2 )*( col -2 ) ; j++ )
                {
                    t[j] = new CircleTile ( c[i] );
                    t[j].setIndex ( j );
                }
                start += numOfEach;
                end+= numOfEach;
                for ( int j = start ; j < end && j <  (row -2 )* ( col - 2 ); j++ )
                {
                    t[j] = new CrossTile ( c[i] );
                    t[j].setIndex ( j );
                }
                start += numOfEach;
                end += numOfEach;
            }
            for ( int i = 0 ; i < 3 ; i++ )
            {
                for ( int j = start ; j < end && j < ( row -2 )* ( col -2 ) ; j++ )
                {
                    t[j] = new XTile ( c[i] );
                    t[j].setIndex ( j );
                }
                start += numOfEach;
                end += numOfEach;
                for ( int j = start ; j < end && j < (row-2)*( col - 2 ); j++ )
                {
                    t[j] = new CubeTile ( c[i] );
                    t[j].setIndex ( j );
                }
                start += numOfEach;
                end += numOfEach;
                for ( int j = start ; j < end && j < (row-2)*(col -2 ) ; j++ )
                {
                    t[j] = new CombineTile ( c[i] );
                    t[j].setIndex ( j );
                }
                start += numOfEach;
                end += numOfEach;
                for ( int j = start ; j < end && j < ( row - 2 ) * ( col - 2 ) ; j++ )
                {
                    t[j] = new MTile ( c[i] );
                    t[j].setIndex ( j );
                }
                start += numOfEach;
                end += numOfEach;
                for ( int j = start ; j < end && j < ( row-2) * ( col -2 ) ; j++ )
                {
                    t[j] = new ATile ( c[i] );
                    t[j].setIndex ( j );
                }
                start += numOfEach;
                end += numOfEach;
            }
         // fill the grid
           while ( count != ( row -2 )*( col - 2 ) )
           {
               while ( true )
               {
                   Random r1 = new Random ( );
                   Random r2 = new Random ( );
                   int x = r1.hashCode()%row;
                   int y =  r2.hashCode()%col;
                   if ( exits[x][y] )
                   {
                       this.add ( t[count] , x , y );
                       t[count].setIndexX ( x );
                       t[count].setIndexY ( y );
                       exits[x][y] = false;
                       order[x][y] = count;
                       count++;
                       break;
                   }
               }
           }
           //keep the border
           for ( int i = 0 ; i < row ; i++ )
           {
               exits[i][0] = true;
               exits[i][col-1] = true;
           }
           for ( int i = 0 ; i < col ; i++  )
           {
               exits[0][i] = true;
               exits[row-1][i] = true;
           }
           // react to the MouseClicked
         int [] click_index = new int [2];
         
           for ( int  i = 0 ;  i < count ; i++ )
           {
               final int temp = i;
               t[i].addEventHandler (  MouseEvent.MOUSE_CLICKED , new  EventHandler<MouseEvent> ()
                       {
                           public void handle  (  MouseEvent e  )
                           {
                               click_index[click_cnt]=  temp;
                               if ( exits[t[temp].getIndexX ()][t[temp].getIndexY ()] == false  ) click_cnt++;
                               if ( click_cnt == 2 )
                               {
                                   click_cnt = 0;
                                   if ( click_index[0] == click_index[1] )
                                   {
                                        t[click_index[0]].r.setStroke ( Color.BLACK );
                                         t[click_index[0]].r.setStrokeWidth ( 3  );
                                   }
                                   else if ( connected ( click_index[0] , click_index[1] ))
                                   {
                                       setGrid ( click_index[0] , click_index[1] , g );
                                   }
                                   else 
                                   {
                                       t[click_index[0]].r.setStroke ( Color.BLACK );
                                       t[click_index[1]].r.setStroke ( Color.BLACK );
                                       t[click_index[0]].r.setStrokeWidth ( 3  );
                                       t[click_index[1]].r.setStrokeWidth ( 3 );
                                   }
                               }
                           }
                       });
           }
           cnt.set ( count );
       }
       
       //update the grid;
       public void setGrid ( int id1 , int id2 , Group g  )
       {
            cnt.set ( cnt.add ( -2 ).get() );
            int x1 = t[id1].getIndexX()  , y1 = t[id1].getIndexY(); 
            int x2 = t[id2].getIndexX() , y2 = t[id2].getIndexY();
            exits[x1][y1] = true;
            exits[x2][y2] = true;
            
            double xx1 = t[id1].getLayoutX () , yy1 = t[id1].getLayoutY();
            double xx2 = t[id2].getLayoutX () , yy2 = t[id2].getLayoutY();
            double xx3 , yy3 , xx4 , yy4;
            if ( flag == 1 )
            {
                Line l1 = new Line() , l2 = new Line ();
                
                xx3 = t[ order[c_x1][c_y1] ].getLayoutX ();
                yy3 = t[ order[c_x1][c_y1] ].getLayoutY ();
                l1.setStartX ( xx1+21 );
                l1.setStartY ( yy1+28 );
                l1.setEndX ( xx3 + 21 );
                l1.setEndY ( yy3 + 28 );
                l2.setStartX ( xx3 + 21 );
                l2.setStartY ( yy3 + 28 );
                l2.setEndX ( xx2 + 21 );
                l2.setEndY ( yy2 + 28 );
                l1.setOpacity ( 0 );
                l2.setOpacity ( 0 );
                l1.setFill ( Color.GOLD );
                l2.setFill ( Color.GOLD );
                g.getChildren().add ( l1 );
                g.getChildren().add ( l2 );
                FadeTransition f3 = new FadeTransition ( Duration.seconds(0.25) , l1 );
                FadeTransition f4 = new FadeTransition ( Duration.seconds(0.25) , l2 );
                f3.setFromValue ( 0 );
                f4.setFromValue ( 0 );
                f3.setToValue ( 1 );
                f4.setToValue ( 1 );
                FadeTransition f5 = new FadeTransition ( Duration.seconds(0.25), l1 );
                FadeTransition f6 = new FadeTransition ( Duration.seconds(0.25),l2 );
                f5.setFromValue ( 1 );
                f6.setFromValue ( 1 );
                f5.setToValue ( 0 );
                f6.setToValue ( 0 );
                
                ParallelTransition p1 = new ParallelTransition ( f3 , f4 );
                ParallelTransition p2 = new ParallelTransition ( f5 , f6 );
                SequentialTransition s = new SequentialTransition ( p1 , p2 );
                s.play();
             }
            if ( flag == 3 )
            {
                Line l = new Line ( );
                l.setStartX ( xx1 + 21 );
                l.setStartY ( yy1 + 28 );
                l.setEndX  ( xx2 + 21 );
                l.setEndY ( yy2 + 28 );
                l.setOpacity ( 0 );
                l.setFill ( Color.GOLD );
                g.getChildren().add ( l );
                FadeTransition f3 = new FadeTransition ( Duration.seconds(0.25) , l  );
                FadeTransition f4 = new FadeTransition ( Duration.seconds(0.25) , l );
                f3.setFromValue ( 0 );
                f3.setToValue ( 1 );
                f4.setFromValue ( 1 );
                f4.setToValue ( 0 );
                SequentialTransition s = new SequentialTransition ( f3 , f4 );
                s.play ();
            }
            if ( flag == 2 )
            {
                Line l1 = new Line ( ) , l2 = new Line  ( ) , l3  = new Line ( );
                if ( order[c_x2][c_y2] == -1 )
                {
                    xx3 = xx1 + 50 * ( c_x2 - x1 ) ;
                    yy3 = yy1 + 63* ( c_y2 - y1 ) ;
                }
                else
                {
                    xx3 = t[ order[c_x2][c_y2]].getLayoutX();
                    yy3 = t[ order[c_x2][c_y2]].getLayoutY();
                }
                if ( order[c_x1][c_y1] == -1 )
                {
                    xx4 = xx1 + 50* ( c_x1 - x1 );
                    yy4 =  yy1 + 63 *( c_y1 - y1 );
                }
                else 
                {
                    xx4 = t[ order[c_x1][c_y1]].getLayoutX();
                    yy4 = t[ order[c_x1][c_y1]].getLayoutY();
                }
                l1.setFill  ( Color.GOLD );
                l2.setFill  ( Color.GOLD );
                l3.setFill ( Color.GOLD );
                l1.setOpacity ( 0 );
                l2.setOpacity ( 0 );
                l3.setOpacity ( 0 );
                l1.setStartX ( xx1 + 21 );
                l1.setStartY ( yy1 + 28 );
                l1.setEndX ( xx3 + 21 );
                l1.setEndY ( yy3 + 28 );
                l2.setStartX ( xx3 + 21 );
                l2.setStartY ( yy3 + 28 );
                l2.setEndX ( xx4 + 21 );
                l2.setEndY ( yy4 + 28 );
                l3.setStartX ( xx4 + 21 );
                l3.setStartY ( yy4 + 28 );
                l3.setEndX ( xx2 +21 );
                l3.setEndY ( yy2 + 28 );
                
                g.getChildren().add ( l1 );
                g.getChildren().add ( l2 );
                g.getChildren().add  ( l3 );
                
                FadeTransition f3 = new FadeTransition ( Duration.seconds ( 0.25 ) , l1 );
                FadeTransition f4 = new FadeTransition ( Duration.seconds ( 0.25 ) , l2 );
                FadeTransition f5 = new FadeTransition ( Duration.seconds(0.25) , l3 );
                f3.setFromValue ( 0 );
                f4.setFromValue ( 0 );
                f5.setFromValue ( 0 );
                f3.setToValue ( 1 );
                f4.setToValue ( 1 );
                f5.setToValue ( 1 );
                FadeTransition f6 = new FadeTransition ( Duration.seconds(0.25 ) , l1 );
                FadeTransition f7 = new FadeTransition ( Duration.seconds(0.25) ,  l2 );
                FadeTransition f8 = new FadeTransition ( Duration.seconds(0.25) ,  l3 );
                f6.setFromValue ( 1 );
                f7.setFromValue ( 1 );
                f8.setFromValue ( 1 );
                f6.setToValue ( 0 );
                f7.setToValue ( 0 );
                f8.setToValue ( 0 );
                
                ParallelTransition p1 = new ParallelTransition ( f3 , f4 , f5 );
                ParallelTransition p2 = new ParallelTransition ( f6 , f7 , f8 );
                SequentialTransition s = new SequentialTransition ( p1 , p2 );
                s.play( );
                
            }
            FadeTransition f1 = new FadeTransition ( Duration.seconds(0.5) , t[id1]  );
            f1.setFromValue ( 1 );
            f1.setToValue ( 0 );
            f1.play();
            
            FadeTransition f2 = new FadeTransition ( Duration.seconds(0.5) , t[id2]  );
            f2.setFromValue ( 1 );
            f2.setToValue ( 0 );
            f2.play();
            
       }
       // judge whether the two tiles connected
       public boolean connected ( int a , int b )
       {
           if ( t[a].variouty != t[b].variouty )
           {
               flag = 0;
               return false;
           }
           
           if ( t[a].cc != t[b].cc ) 
           {
               flag = 0;
               return false;
           }
           int x1 = t[a].getIndexX();
           int x2 = t[b].getIndexX();
           int y1 = t[a].getIndexY();
           int y2 = t[b].getIndexY();
          
           // adjacent
           if ( Math.abs( x2-x1) + Math.abs (y2-y1) == 1 )
           {
               flag = 3;
               return true;
           }
           // in the same row or in the same column
           if ( x1 == x2 || y1 == y2 )
               if ( judge3 ( x1 , y1 , x2 , y2 ))
               {
                   flag = 3;
                   return true;
               }
           
           // with one corner
           if ( judge ( x1 , y1 , x2 , y2 ) )
           {
               flag = 1;
               return true;
           } 
           
           //with two corners
           for ( int i = x1+1  ; i < row ; i++ )
           {
               if ( exits[i][y1] == false ) break;
               if ( judge( i , y1 , x2 , y2 ) ) 
               {
                   flag = 2;
                   c_x2 = i;
                   c_y2 = y1;
                   return true;
               }
           }
           for ( int i = x1-1 ; i >= 0 ; i-- )
           {
               if ( exits[i][y1] == false ) break;
               if ( judge ( i , y1 , x2 ,y2 ))
               {
                   flag = 2;
                   c_x2 = i ;
                   c_y2 = y1;
                   return true;
               }
           }
           for ( int i = y1+1 ; i < col ; i++  )
           {
               if ( exits[x1][i] == false ) break;
               if ( judge( x1 , i , x2 , y2 ) )
               {
                   flag = 2;
                   c_x2 = x1;
                   c_y2 = i;
                   return true;
               }
           }
           for ( int i = y1-1 ; i >= 0 ; i-- )
           {
               if ( exits[x1][i] == false ) break;
               if ( judge ( x1 , i , x2 , y2 ) )
               {
                   flag = 2;
                   c_x2 = x1;
                   c_y2 = i;
                   return true;
               }
           }
           flag = 0;
           return false;
       }
       
       public boolean judge ( int x1 , int y1 , int x2 , int y2 )
       {
           if (  (x1 - x2 < 0 && y1 -y2 > 0 ) || ( x1 - x2 > 0 && y1 - y2 < 0 )  )  return  judge2  ( x1 , y1 , x2 , y2  );
            if ( x1 > x2 )
           {
               int temp  = x1;
               x1 = x2;
               x2 = temp;
           }
           if ( y1 > y2 )
           {
               int temp = y1;
               y1 = y2;
               y2 = temp;
           }
           boolean  isOkay = true;
           for ( int i = x1+1 ; i <= x2 ; i++  )
               if ( exits[i][y1] == false && isOkay  ) isOkay = false;
           for ( int i = y1+1 ; i < y2 ; i++  )
               if ( exits[x2][i] == false  && isOkay ) isOkay = false;
           if ( isOkay ) 
           {
               c_x1 = x2 ;
               c_y1 = y1;
               return true;
           }
           isOkay = true;
           for ( int i = y1+1 ; i <= y2 ; i++ )
               if ( exits[x1][i] == false && isOkay ) isOkay = false;
           for ( int i = x1+1 ; i < x2 ; i++ )
               if ( exits[i][y2] == false && isOkay ) isOkay = false;
           if ( isOkay ) 
           {
               c_x1 = x1;
               c_y1 = y2;
               return true;
           }
           return false;
       }
       
       public boolean judge2  ( int x1 , int y1 , int x2  , int y2 )
       {
           if ( x1 > x2 )
           {
               int tx = x1 ,ty = y1;
               x1 = x2 ; y1 = y2;
               x2 = tx ; y2 = ty;
           }
           boolean  isOkay = true;
           for ( int i = x1+1 ; i <= x2 ; i++  )
                if ( exits[i][y1] == false && isOkay   ) isOkay = false;
           for ( int i = y1-1 ; i > y2 ; i--  )
               if ( exits[x2][i] == false  && isOkay ) isOkay = false;
           if ( isOkay== true ) 
           {
               c_x1 = x2 ;
               c_y1 = y1;
               return true;
           }
           isOkay = true;
           for ( int i = y1-1 ; i >= y2 ; i-- )
               if ( exits[x1][i] == false && isOkay ) isOkay = false;
           for ( int i = x1+1 ; i < x2 ; i++ )
               if ( exits[i][y2] == false && isOkay ) isOkay = false;
           if ( isOkay  == true  ) 
           {
               c_x1 = x1;
               c_y1 = y2;
               return true;
           }
           return false;
       }
       
       public boolean judge3 ( int x1 , int y1 , int x2 , int y2 )
       {
              if ( x1 > x2 )
           {
               int temp  = x1;
               x1 = x2;
               x2 = temp;
           }
           if ( y1 > y2 )
           {
               int temp = y1;
               y1 = y2;
               y2 = temp;
           }
           boolean isOkay = true;
           if ( x1 == x2 )
               for ( int i = y1+1 ; i < y2 ; i++  )
                   if ( exits[x1][i] == false && isOkay ) isOkay = false;
           if ( y1 == y2 )
               for ( int i = x1+1 ; i <x2 ; i++ )
                   if ( exits[i][y1] == false && isOkay ) isOkay = false;
           if ( isOkay ) return true;
           return false;
       }
}
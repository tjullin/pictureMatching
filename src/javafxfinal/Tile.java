//FIle Name : Tile.java
//Author : LiuLin Student Number : 3013218111
//Class Day : Tuesday  Class Year : 2014
//Anssignment Number : FInal
//Description: the file owns the various classes to show various TIle in the Grid
//                  the designs of the Tile are all locating there
//Known Bugs:
//Fulture Inmprovements : 
// --improvement idea1 : use more beautiful shapes to make the Tile cute
// --improvement idea1 : to adjust the size of the tile to be more suiltable to the grid
package javafxfinal;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//Class Description: the basic class of the Tile ,owns the basics function of all
//                          kinds of Tiles
public class Tile extends StackPane
{
    protected int indexX,indexY,index , variouty;
    public Color cc;
    public Rectangle  r;
    Tile (  )
    {
        r = new Rectangle ( );
        r.setHeight ( 56 );
        r.setWidth ( 42 );
        r.setStrokeWidth ( 3 );
        r.setArcWidth( 4.0 );
        r.setArcHeight ( 4.0 );
        r.setFill ( Color.WHITE  );
        r.setStroke ( Color.BLACK );
        this.addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent> ()
                {
                    public void handle ( MouseEvent e )
                    {
                            r.setStroke ( Color.GOLD );
                            r.setStrokeWidth ( 6 );
                    }
                });
        this.getChildren().add ( r );
    }
    
    public void setIndex ( int x )
    {
        index = x;
    }
    
    public int getIndex ( )
    {
        return index;
    }
    
    public int getIndexX ( )
    {
        return indexX;
    }
    
    public int getIndexY()
    {
        return indexY;
    }
    
    public void setIndexX ( int  x )
    {
        indexX = x;
    }
    
    public void setIndexY ( int y )
    {
        indexY = y;
    }
   
}
//Class Description: the specified Tile with the distinguished shape Circle
class CircleTile extends Tile
{ 
    CircleTile (  Color cc )
    {
        variouty = 1;
        this.cc = cc;
        this.setHeight ( 56 );
        this.setWidth ( 42 );
        Circle c = new Circle ( );
        c.setRadius ( 18 );
        c.setFill ( cc );
        Text t = new Text ( "- -");
        t.setFont ( new Font ( 36 ));
        this.getChildren().add ( c );
        this.getChildren().add ( t );
    }
}
//Class Description : the specified class with the certain shape cross
class CrossTile extends Tile
{
    CrossTile ( Color c )
    {
        variouty = 2;
        this.cc = c;
        Line l1 = new Line ( );
        Line l2 = new Line ( );
        l1.setFill ( c );
        l2.setFill ( c );
        l1.setStroke ( c );
        l1.setStrokeWidth ( 5 );
        l2.setStroke ( c );
        l2.setStrokeWidth ( 5 );
        l1.setStartX ( this.getLayoutX()+ 21 );
        l1.setStartY(  this.getLayoutY () );
        l1.setEndY ( this.getLayoutY() + 56 );
        l1.setEndX ( this.getLayoutX()+ 21 );
        l2.setStartX ( this.getLayoutX() );
        l2.setStartY ( this.getLayoutY() + 28 );
        l2.setEndX ( this.getLayoutX() + 42 );
        l2.setEndY ( this.getLayoutY() + 28 );
        Text t = new Text ( "(oo)");
        t.setFont ( new Font ( 20));
        this.getChildren().add ( l1 );
        this.getChildren().add ( l2 );
        this.getChildren().add ( t );
    }
}
//Class Description : the Tile with the shape X 
class XTile extends Tile 
{
    XTile (  Color c )
    {
        variouty = 3;
        cc = c;
        Line l1 = new Line ( );
        Line l2 = new Line ( );
        l1.setFill ( c );
        l2.setFill ( c );
        l1.setStroke ( c );
        l1.setStrokeWidth ( 5 );
        l2.setStroke ( c );
        l2.setStrokeWidth ( 5 );
        l1.setStartX ( this.getLayoutX () );
        l1.setStartY(  this.getLayoutY () );
        l1.setEndY ( this.getLayoutY() + 56 );
        l1.setEndX ( this.getLayoutX()+ 42 );
        l2.setStartX ( this.getLayoutX() );
        l2.setStartY ( this.getLayoutY() + 56 );
        l2.setEndX ( this.getLayoutX() + 42 );
        l2.setEndY ( this.getLayoutY() );
        Text t = new Text ( "~~");
        t.setFont ( new Font ( 30 ));
        this.getChildren().add ( l1 );
        this.getChildren().add ( l2 );
        this.getChildren().add ( t );
    }
}
//Class Description: the Tile with the shape Cube
class CubeTile extends Tile
{
    CubeTile (  Color c )
    {
        variouty = 4;
        cc = c;
        Rectangle r = new Rectangle ( );
        r.setHeight ( 24 );
        r.setWidth ( 24 );
        r.setFill ( Color.WHITE );
        r.setStroke ( c );
        r.setStrokeWidth ( 8 );
        Text t = new Text ( "+_+" );
        t.setFont ( new Font ( 30 ));
        this.getChildren().add ( r );
        this.getChildren().add ( t );
    }   
}
//Class Description: the tile with varios shapes
class  CombineTile extends Tile
{
    CombineTile ( Color c )
    {
        variouty = 5;
        this.cc = c;
        this.setHeight ( 56 );
        this.setWidth ( 42 );
        Circle p = new Circle ( );
        p.setRadius ( 18 );
        p.setFill ( cc );
        this.getChildren().add ( p );
        Rectangle r = new Rectangle ( );
        r.setHeight ( 24 );
        r.setWidth ( 24 );
        r.setFill ( Color.PURPLE );
        r.setStroke ( c );
        r.setStrokeWidth ( 8 );
        this.getChildren().add ( r );
        Text t = new Text ( "^^");
        t.setFont ( new Font ( 34 ));
        this.getChildren().add ( t );
    }
}
// Class Description : the Tile with the shape like Chinense character mi
class MTile extends Tile
{
        MTile ( Color c )
        {
            variouty = 6;
            cc = c;
            Line l3 = new Line ( );
            Line l4 = new Line ( );
            l3.setFill ( c );
            l4.setFill ( c );
            l3.setStroke ( c );
            l3.setStrokeWidth ( 5 );
            l4.setStroke ( c );
            l4.setStrokeWidth ( 5 );
            l3.setStartX ( this.getLayoutX () );
            l3.setStartY(  this.getLayoutY () );
            l3.setEndY ( this.getLayoutY() + 56 );
            l3.setEndX ( this.getLayoutX()+ 42 );
            l4.setStartX ( this.getLayoutX() );
            l4.setStartY ( this.getLayoutY() + 56 );
            l4.setEndX ( this.getLayoutX() + 42 );
            l4.setEndY ( this.getLayoutY() );
            this.getChildren().add ( l3 );
            this.getChildren().add ( l4 );
            Line l1 = new Line ( );
            Line l2 = new Line ( );
            l1.setFill ( c );
            l2.setFill ( c );
            l1.setStroke ( c );
            l1.setStrokeWidth ( 5 );
            l2.setStroke ( c );
            l2.setStrokeWidth ( 5 );
            l1.setStartX ( this.getLayoutX()+ 21 );
            l1.setStartY(  this.getLayoutY () );
            l1.setEndY ( this.getLayoutY() + 56 );
            l1.setEndX ( this.getLayoutX()+ 21 );
            l2.setStartX ( this.getLayoutX() );
            l2.setStartY ( this.getLayoutY() + 28 );
            l2.setEndX ( this.getLayoutX() + 42 );
            l2.setEndY ( this.getLayoutY() + 28 );
            Text t = new Text ( "T_T");
            t.setFont ( new Font ( 30 ));
            this.getChildren().add ( l1 );
            this.getChildren().add ( l2 );
            this.getChildren().add ( t );
        }
}

//Class Description :the Tile with the shape like @
class ATile extends Tile
{
    ATile (  Color c )
    {
         variouty = 1;
         this.cc = c;
         this.setHeight ( 56 );
         this.setWidth ( 42 );
         Circle ccc = new Circle ();
         ccc.setRadius ( 18 );
         ccc.setFill ( c );
         ccc.setStroke ( Color.BLACK );
         ccc.setStrokeWidth ( 3 );
         Text t = new Text ( "a");
         t.setFont ( new Font ( 37 ));
         this.getChildren().add ( ccc );
         this.getChildren().add ( t );
    }
}
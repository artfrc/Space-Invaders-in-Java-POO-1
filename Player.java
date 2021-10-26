
import java.awt.Color;
import java.awt.Rectangle;

public class Player extends Rectangle {
    
    public Color color;
    
    public Player(int x, int y, int width, int hight){
        super(x,y,width,hight);
        
        color = new Color(255,255,255);
        
    }
    
    public void update(){
        
        if(Game.pressMovie){
            
            if(Game.keyPress == 'a'){
                if(x - 20 >= 0) x -= 20;
            }
            else if(Game.keyPress == 'd'){
                if(x + 20 < 620) x += 20;
            }
            
        }
        
    }
    
}

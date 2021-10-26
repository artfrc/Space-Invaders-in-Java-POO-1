
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class RectObj extends Rectangle{
    
    public Color color;
    public int route;
    
    public RectObj(int x, int y, int width, int height, int route){
        super(x,y,width,height);
        this.route = route;
        
        color = new Color(new Random().nextInt(255), new Random().nextInt(200), new Random().nextInt(200));
        
    }
    
    public void update(){
        
        if(route == 0) x += 4; // velocidade inimigos
        else           x -= 4;
        
        if(x >= 600){
            y += 80;
            route = 1;
        }
        if(x <= 0){
            y += 80;
            route = 0;
        }
        
        /*
        if(y >= 440) Game Over
        */
       
    }
    
}


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemieShot extends Rectangle{
    
    public Color color;
    public Shot s;
    
    
    public EnemieShot(int x, int y, int width, int height){
        super(x,y,width,height);
        
        color = new Color(255,255,0);
    }
    
    public void update(){
        
        y += 10;
       
    }
    
    public void render(Graphics g){
        
        g.setColor(this.color);
        g.fillRect(x, y, width, height);
        
    }
    
}

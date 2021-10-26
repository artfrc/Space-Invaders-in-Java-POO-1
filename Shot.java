
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shot extends Rectangle{
    
    public Color color;
    public Shot s;
    
    
    public Shot(int x, int y, int width, int height){
        super(x,y,width,height);
        
        color = new Color(255,0,255);
    }
    
    public void update(){
        
        y -= 10;
       
    }
    
    public void render(Graphics g){
        
        g.setColor(this.color);
        g.fillRect(x, y, width, height);
        
    }
    
}

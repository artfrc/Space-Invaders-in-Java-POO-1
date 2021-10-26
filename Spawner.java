
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Spawner {
    
    public int timer = 0, limitShot = 0;
    
    public ArrayList<RectObj> enemies = new ArrayList<RectObj>();
    public static ArrayList<Shot> shot = new ArrayList<Shot>();
    public static ArrayList<EnemieShot> enemieShot = new ArrayList<EnemieShot>();
    
    public Player player = new Player(Game.WIDTH/2-20,Game.HEIGHT-40,30,30);
    public static Shot s;
    public static EnemieShot es;
    
    public boolean visiblePlayer = false;
    
    public boolean destroyShip(int x, int y){
        
        for(int i = 0; i < enemies.size(); i++){
            
            RectObj current = enemies.get(i);
            if(x >= (int)current.getX() && x <= (int)current.getX()+30){
                if(y >= (int)current.getY() && y <= (int)current.getY()+30){
                    enemies.remove(current);
                    Game.counter += 10;
                    return true;
                }
            }
            
        }
        
        return false;

    }
    
    public boolean destroyPlayer(int x, int y){
        
        if(x >= (int)player.getX() && x <= (int)player.getX()+30){
            if(y >= (int)player.getY() && y <= (int)player.getY()+30){
                Game.life -= 33;
                return true;
            }
        }
            
        
        
        return false;

    }
    
    public void update(){        
        timer++;
        if(timer % 50 == 0 && enemies.size() < 10){ // a cada 1s vem novos retangulos
            //                      x ; y ; (larg alt ret) ; rota -> ou <- 
            enemies.add(new RectObj(0, 40, 30, 30, 0));
        }
        
        if(timer % 30 == 0){
            
            if(enemies.size() > 0){
                int random = new Random().nextInt(enemies.size());
                es = new EnemieShot(enemies.get(random).x+15, enemies.get(random).y+30, 5, 10);
                enemieShot.add(es);
            }
        }
        
        if(!visiblePlayer){
            
            player.add(player);
            visiblePlayer = true;
            
        }
        
        // Atualizo todos meus inimigos
        for(int i = 0; i < enemies.size(); i++){
            
            RectObj current = enemies.get(i);
            
            current.update();
        
            if(current.y >= 440){
                enemies.remove(current);
                Game.life -= 33;
            }
        }
        
       // Atualizo meu jogador
       if(Game.pressMovie){
           player.update();
           Game.pressMovie = false;
       }
       
       // Atualizo os tiros
       if(Game.pressShot && limitShot < 2){
           s = new Shot(player.x+15, player.y, 5, 10);
           Spawner.shot.add(s);
           limitShot++;
           Game.pressShot = false;
       }
       // tiros do player
        for(int i = 0; i < shot.size(); i++){
           
            Shot current = shot.get(i);
           
            current.update();
            if(destroyShip((int)current.getX(), (int)current.getY()) || current.y <= 30){
               shot.remove(current);
               limitShot--;
            }
        }
       // tiros dos bot
       for(int i = 0; i < enemieShot.size(); i++){
           
           EnemieShot current = enemieShot.get(i);
           
           current.update();
           
           if(current.y >= Game.HEIGHT || destroyPlayer(current.x, current.y)){
               enemieShot.remove(current);
           }
           
       }
       
       
       
       
    }
    
    public void render(Graphics g){
        
        for(int i = 0; i < enemies.size(); i++){
            RectObj current = enemies.get(i);
            g.setColor(current.color);
            g.fillRect(current.x, current.y, current.width, current.height);
            
        }
        
        for(int i = 0; i < shot.size(); i++){
           shot.get(i).render(g);
        }
        
        for(int i = 0; i < enemieShot.size(); i++){
           
           EnemieShot current = enemieShot.get(i);
           
           current.render(g);
           
        }
        
        Player current = player;
        g.setColor(current.color);
        g.fillRect(current.x, current.y, current.width, current.height);
    }
    
    
    
}

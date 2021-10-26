
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener {
    
    public static final int WIDTH = 640, HEIGHT = 480;
    
    public static int life = 99, counter = 0;
    
    public static boolean pressMovie, pressShot, gameOver = false;
    public static char keyPress;
    
    public  Spawner spawner;

    public Game(){
        Dimension dimension = new Dimension(WIDTH,HEIGHT); // tamanho da tela
        this.setPreferredSize(dimension); // todas as janelas terem o mesmo tamanho
        this.addKeyListener(this);
        
        spawner = new Spawner();
        
    }
    
    public void update(){ // atualizar o jogo
        
        if(!gameOver){
            spawner.update();
            if(life <= 0){
                // Game Over
                gameOver = true;
            }
        }
        
    }
    
    public void render(){ // renderizar o jogo
        BufferStrategy bs = this.getBufferStrategy(); // otimizar gráfico
        
        // conferir se existe o buffer
        if(bs == null){ // se não existir...
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black); // colocando a tela preta
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
            // Criando os pontos na tela
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD,23)); 

            // Colocando Pontos na tela...
            // WIDTH é o eixo X o outro é o eixo Y
            g.drawString("Pontos: "+ counter, WIDTH/2+100,30);
        
        if(!gameOver){    
            
            // Criando a barra de vida
            g.setColor(Color.green);
            g.fillRect(Game.WIDTH/2-300, 5, life*3, 20);
            g.setColor(Color.white);
            g.drawRect(Game.WIDTH/2-300, 5, 297, 20);

            spawner.render(g);
        
        }else{
            
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD,50));
            g.drawString("GAME OVER!", WIDTH/2-150, HEIGHT/2);
           
            
            
        }
            
        bs.show();
        
    }
    
    public static void main(String a[]){
        Game game = new Game();
        JFrame jframe = new JFrame("Space Invaders"); // criando a janela
        jframe.add(game); // colocando o tamanho da janela
        jframe.setLocationRelativeTo(null); // tela ficar no centro
        jframe.pack(); // evita bordas desncecessárias
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jframe.setVisible(true);
        
        new Thread(game).start();
        
    }
        
    @Override
    public void run() {
        
        while(true){ 
            update();
            render();
            try { 
                Thread.sleep(1000/60); // para manter o FPS em 60
            }catch (InterruptedException ex) {
                 ex.printStackTrace();
            }
        }
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd'){
            pressMovie = true;
            keyPress = e.getKeyChar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if(e.getKeyChar() == 32){
            
            pressShot = true;
            keyPress = e.getKeyChar();
            
        }
    }
    
}

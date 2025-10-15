package main;
import object.SuperObject;
import object.OBJ_Key;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import tile.TileManager;
import entity.Entity;
import main.CollisionChecker;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable{


	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;// 768 pixels
	public final int screenHeight = tileSize * maxScreenRow;//576 pixels
	//WORLD MAP PARAMETERS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	//FPS
	int FPS = 60;
	

	
	TileManager tileM = new TileManager(this);
	GameKeyHandler  keyH = new GameKeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	


	//Set player's default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	public GamePanel(){

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}
	public void setupGame(){
	
		aSetter.setObject();


	}
	public void startGameThread(){


	gameThread = new Thread(this);
	gameThread.start();

	}
	@Override 
	public void run(){

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		while(gameThread != null){

		currentTime = System.nanoTime();

		delta += (currentTime - lastTime) / drawInterval;
		timer += (currentTime - lastTime);
		lastTime = currentTime;

		if (delta >= 1){
		update();
		repaint();
		delta --;
		drawCount ++;
	
			}
		}


	}
	public void update(){
			
	player.update();
	
	}
	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g; 
		
		tileM.draw(g2);
		for (int i = 0; i < obj.length; i++){


		if (obj[i] != null) {

			obj[i].draw(g2, this);


			}




		}
		player.draw(g2);

		g2.dispose();

	}

}

package object;

import main.GamePanel;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    GamePanel gp;
    public OBJ_Door(GamePanel gp) {
        this.gp = gp;
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch(IOException e) {
            e.printStackTrace();
        }

        solidArea = new Rectangle(0, 0, 48, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true; // door blocks player until removed
    }
}

package object;

import main.GamePanel;

import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        this.gp = gp;
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch(IOException e) {
            e.printStackTrace();
        }

        solidArea = new Rectangle(8, 8, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = false; // key can be walked over
    }
}

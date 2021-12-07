package object;

import main.GamePanel;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {

        this.gp = gp;

        this.name = "Key";

        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/objects/key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
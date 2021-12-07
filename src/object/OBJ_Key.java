package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {

        this.name = "Key";

        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
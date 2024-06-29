package tilegame.gfx;

import java.awt.image.BufferedImage;

// usada para carrega romsente 1 vez nossos arquivos
public class Assets {
    // qualquer imagem ou musica no jogo

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/Player/Idle.png"));
        player = sheet2.crop(0, 0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 2, 0, width, height);
        tree = sheet.crop(0, height * 2, width, height);
    }
}

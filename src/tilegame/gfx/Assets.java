package tilegame.gfx;

import java.awt.image.BufferedImage;

// usada para carrega romsente 1 vez nossos arquivos
public class Assets {
    // qualquer imagem ou musica no jogo

    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree, water;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] player_idle_down, player_idle_up, player_idle_left, player_idle_right;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet walk = new SpriteSheet(ImageLoader.loadImage("/textures/Player/Walk.png"));
        SpriteSheet idle = new SpriteSheet(ImageLoader.loadImage("/textures/Player/Idle.png"));
        // player = sheet2.crop(0, 0, width, height);

        player_down = new BufferedImage[4];
        player_up = new BufferedImage[4];
        player_left = new BufferedImage[4];
        player_right = new BufferedImage[4];

        player_idle_left = new BufferedImage[2];
        player_idle_right = new BufferedImage[2];
        player_idle_up = new BufferedImage[2];
        player_idle_down = new BufferedImage[2];

        player_idle_down[0] = idle.crop(0, 0, width, height);
        player_idle_down[1] = idle.crop(width, 0, width, height);

        player_idle_up[0] = idle.crop(0, height, width, height);
        player_idle_up[1] = idle.crop(width, height, width, height);

        player_idle_left[0] = idle.crop(0, height * 2, width, height);
        player_idle_left[1] = idle.crop(width, height * 2, width, height);

        player_idle_right[0] = idle.crop(0, height * 3, width, height);
        player_idle_right[1] = idle.crop(width, height * 3, width, height);

        player_down[0] = walk.crop(0, 0, width, height);
        player_down[1] = walk.crop(width, 0, width, height);
        player_down[2] = walk.crop(width * 2, 0, width, height);
        player_down[3] = walk.crop(width * 3, 0, width, height);

        player_up[0] = walk.crop(0, height, width, height);
        player_up[1] = walk.crop(width, height, width, height);
        player_up[2] = walk.crop(width * 2, height, width, height);
        player_up[3] = walk.crop(width * 3, height, width, height);

        player_left[0] = walk.crop(0, height * 2, width, height);
        player_left[1] = walk.crop(width, height * 2, width, height);
        player_left[2] = walk.crop(width * 2, height * 2, width, height);
        player_left[3] = walk.crop(width * 3, height * 2, width, height);

        player_right[0] = walk.crop(0, height * 3, width, height);
        player_right[1] = walk.crop(width, height * 3, width, height);
        player_right[2] = walk.crop(width * 2, height * 3, width, height);
        player_right[3] = walk.crop(width * 3, height * 3, width, height);

        water = sheet.crop(0, 0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, height * 2, width, height);
    }
}

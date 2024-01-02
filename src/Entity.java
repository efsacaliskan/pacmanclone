import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public int size;
    public BufferedImage pacman_left, pacman_right, pacman_up, pacman_down,
            ghost_img, blue_ghost_left, blue_ghost_right, blue_ghost_up, blue_ghost_down,
    brown_ghost_left, brown_ghost_right, brown_ghost_up, brown_ghost_down,
    yellow_ghost_left, yellow_ghost_right, yellow_ghost_up, yellow_ghost_down,
    green_ghost_left, green_ghost_right, green_ghost_up, green_ghost_down;
    public String direction;
}

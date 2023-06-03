package main;

import input.KeyBoardInput;
import input.MouseInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInput mouseInput;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel() {
        mouseInput = new MouseInput(this);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyBoardInput(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
    }

    private void importImg() {
        try (InputStream is = getClass().getResourceAsStream("/player_sprites.png")) {
            img = ImageIO.read(Objects.requireNonNull(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;

            if (aniIndex >= GetSpriteAmount(playerAction))
                aniIndex = 0;
        }
    }

    public void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePos() {
        if (moving) {
            switch (playerDir) {
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        requestFocus(true);


        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 256, 160, null);
    }
}

package ui;

import main.Game;
import state.Gamestate;
import state.Playing;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.UI.UrmButtons.URM_SIZE;

public class GameOverOverlay {
    private Playing playing;
    private BufferedImage img;
    private int imgX, imgY, imgW, imgH;
    private UrmButton menu, replay;

    public GameOverOverlay(Playing playing) {
        this.playing = playing;
        createImg();
        createButtons();
    }

    private void createButtons() {
        int menuX = (int) (330 * Game.SCALE);
        int replayX = (int) (440 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        replay = new UrmButton(replayX, y, URM_SIZE, URM_SIZE, 1);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.DEATH_SCREEN);
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH / 2 - imgW / 2;
        imgY = (int) (100* Game.SCALE);
    }

    public void update() {
        menu.update();
        replay.update();
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        menu.draw(g);
        replay.draw(g);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }

    private boolean isIn(UrmButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
        replay.setMouseOver(false);
        menu.setMouseOver(false);

        if (isIn(menu, e))
            menu.setMouseOver(true);
        else if (isIn(replay, e))
            replay.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) {
                playing.resetAll();
                Gamestate.state = Gamestate.MENU;
            }
        }
        else if (isIn(replay, e))
            if (replay.isMousePressed())
                playing.resetAll();

        menu.resetBools();
        replay.resetBools();
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e))
            menu.setMousePressed(true);
        else if (isIn(replay, e))
            replay.setMousePressed(true);
    }
}

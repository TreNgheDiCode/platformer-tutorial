package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

import state.Gamestate;
import main.GamePanel;

public class MouseInput implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Gamestate.state) {
            case PLAYING -> gamePanel.getGame().getPlaying().mouseDragged(e);
            case OPTIONS -> gamePanel.getGame().getGameOptions().mouseDragged(e);
            default -> {}
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU -> gamePanel.getGame().getMenu().mouseMoved(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseMoved(e);
            case OPTIONS -> gamePanel.getGame().getGameOptions().mouseMoved(e);
            default -> {
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Objects.requireNonNull(Gamestate.state) == Gamestate.PLAYING) {
            gamePanel.getGame().getPlaying().mouseClicked(e);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU -> gamePanel.getGame().getMenu().mousePressed(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mousePressed(e);
            case OPTIONS -> gamePanel.getGame().getGameOptions().mousePressed(e);
            default -> {
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU -> gamePanel.getGame().getMenu().mouseReleased(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseReleased(e);
            case OPTIONS -> gamePanel.getGame().getGameOptions().mouseReleased(e);
            default -> {
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
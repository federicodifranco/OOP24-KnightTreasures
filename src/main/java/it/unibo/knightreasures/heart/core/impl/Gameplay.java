package it.unibo.knightreasures.heart.core.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;

import it.unibo.knightreasures.controller.impl.ApplicationImpl;
import it.unibo.knightreasures.model.impl.PlayerEntity;
import it.unibo.knightreasures.utilities.Gamestate;
import it.unibo.knightreasures.utilities.State;
import it.unibo.knightreasures.utilities.ViewConstants.Player;
import it.unibo.knightreasures.view.api.View;
import it.unibo.knightreasures.view.impl.LevelManager;

public class Gameplay extends State implements View {

    private PlayerEntity player;
    private LevelManager levelManager;

    public Gameplay(ApplicationImpl game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        player = new PlayerEntity(200, 200, Player.WIDTH, Player.HEIGHT);
        levelManager = new LevelManager(game);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }

    @Override
    public void update() {
        player.update();
        levelManager.update();
    }

    @Override
    public void draw(Graphics g){
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE:
                Gamestate.state = Gamestate.MENU;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void windowLostFocus() {
        player.resetDirBooleans();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

}

package edu.hitsz.application;

import edu.hitsz.Events.Events;
import edu.hitsz.UIPanel.AskingNamePanel;
import edu.hitsz.UIPanel.BeginPanel;
import edu.hitsz.UIPanel.EndPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");
        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel beginPanel = new BeginPanel().BeginPanel;
        frame.add(beginPanel);
        frame.setVisible(true);
        synchronized (Events.IS_GAME_BEGIN){
            try {
                Events.IS_GAME_BEGIN.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.remove(beginPanel);
        Game game;
        if (Events.gameType.equals(Events.GameType.EASY_GAME))
        {
            game = new EasyGame();
        }
        else if (Events.gameType.equals(Events.GameType.COMMON_GAME))
        {
            game = new CommonGame();
        }
        else{
            game = new HardGame();
        }
        frame.add(game);
        frame.setVisible(true);
        game.action();

        synchronized (Events.IS_GAME_STAYING)
        {
            try {
                Events.IS_GAME_STAYING.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JPanel askingNamePanel = new AskingNamePanel().AskingNamePanel;
        synchronized (Events.IS_GET_PLAYER_NAME){
            try {
                Thread.sleep(500);
                frame.remove(game);
                frame.add(askingNamePanel);
                frame.setVisible(true);
                Events.IS_GET_PLAYER_NAME.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JPanel endPanel = new EndPanel().EndPanel;
        synchronized (Events.IS_GAME_END){
            try {
                frame.remove(askingNamePanel);
                frame.add(endPanel);
                frame.setVisible(true);
                Events.IS_GAME_END.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return;

    }
}

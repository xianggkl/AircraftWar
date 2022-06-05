package edu.hitsz.UIPanel;

import edu.hitsz.DAO.PlayerDAO;
import edu.hitsz.DAO.PlayerDAOImpl;
import edu.hitsz.Events.Events;
import edu.hitsz.Player.Player;
import edu.hitsz.application.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AskingNamePanel {
    /**
     * 玩家
     */
    private static Player player;
    public JPanel AskingNamePanel;
    private JTextPane NameTextPanel;
    private JButton SubmitButton;
    private JFormattedTextField NameInstruction;

    public AskingNamePanel() {
        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName;
                if (NameTextPanel.getText().equals(""))
                {
                    playerName = "UserName";
                }
                else{
                    playerName = NameTextPanel.getText();
                }
                //结束游戏后储存数据
                player = new Player(playerName, Game.score);
                Path path = Paths.get("test.txt");
                PlayerDAO playerDAO = new PlayerDAOImpl();
                playerDAO.saveOnePlayerData(path,player);
                synchronized (Events.IS_GET_PLAYER_NAME){
                    Events.IS_GET_PLAYER_NAME.notify();
                }
                return;
            }
        });
    }
}

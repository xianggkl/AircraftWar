package edu.hitsz.UIPanel;

import edu.hitsz.Events.Events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BeginPanel {
    public JPanel BeginPanel;
    private JButton EasyButton;
    private JButton CommonButton;
    private JButton HardButton;
    private JComboBox MusiccomboBox;
    private JFormattedTextField MusicInstruction;

    public BeginPanel() {
        MusiccomboBox.addItem("开");
        MusiccomboBox.addItem("关");
        EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Events.IS_GAME_BEGIN){
                    Events.gameType = Events.GameType.EASY_GAME;
                    Events.IS_GAME_BEGIN.notify();
                }
            }
        });
        CommonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Events.IS_GAME_BEGIN){
                    Events.gameType = Events.GameType.COMMON_GAME;
                    Events.IS_GAME_BEGIN.notify();
                }
            }
        });
        HardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Events.IS_GAME_BEGIN){
                    Events.gameType = Events.GameType.HARD_GAME;
                    Events.IS_GAME_BEGIN.notify();
                }
            }
        });
        MusiccomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String musicTarget = MusiccomboBox.getSelectedItem().toString();
                if ("开".equals(musicTarget))
                {
                    Events.musicType = Events.MusicType.OPEN;
                }
                else{
                    Events.musicType = Events.MusicType.CLOSE;
                }
            }
        });
    }
}

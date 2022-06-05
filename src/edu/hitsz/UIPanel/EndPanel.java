package edu.hitsz.UIPanel;

import edu.hitsz.DAO.PlayerDAO;
import edu.hitsz.DAO.PlayerDAOImpl;
import edu.hitsz.Events.Events;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class EndPanel {
    public JPanel EndPanel;
    private JTable ScoreTable;
    private JScrollPane ScrollPanel;
    private JButton DeleteButton;
    private JButton ExitButton;
    private JComboBox GameTypeComboBox;
    private JFormattedTextField GameTypeInstruction;
    private String selectedName = "";

    public EndPanel() {
        initGameTypeCombobox();
        updataPlayerDataShow();
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (Events.IS_GAME_END){
                    System.out.println("游戏结束!");
                    Events.IS_GAME_END.notify();
                }
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path path = Paths.get("test.txt");
                if (selectedName.equals(""))
                {
                    return;
                }
                new PlayerDAOImpl().deleteOnePlayerData(path,selectedName);
                selectedName = "";
                updataPlayerDataShow();
            }
        });

        GameTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updataPlayerDataShow();
            }
        });
        ScoreTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedName = String.valueOf(ScoreTable.getValueAt(ScoreTable.getSelectedRow(),2));
            }
        });
    }
    private void initGameTypeCombobox()
    {
        GameTypeComboBox.addItem("简单");
        GameTypeComboBox.addItem("一般");
        GameTypeComboBox.addItem("困难");
    }
    private void updataPlayerDataShow()
    {
        ScoreTable.removeAll();
        Path path = Paths.get("test.txt");
        PlayerDAO playerDAO = new PlayerDAOImpl();
        List<String> des = playerDAO.getRankedPlayersData(path);
        String targetGameType = GameTypeComboBox.getSelectedItem().toString();
        List<String> desEasy = new LinkedList<>();
        List<String> desCommon = new LinkedList<>();
        List<String> desHard = new LinkedList<>();
        for (int i=0;i<des.size();i++)
        {
            String[] strs = des.get(i).split("~");
            String[] strss = strs[0].split(":");
            String currentGameType = strss[1];
            switch (currentGameType)
            {
                case ("简单"):
                    desEasy.add(des.get(i));
                    break;
                case ("一般"):
                    desCommon.add(des.get(i));
                    break;
                case ("困难"):
                    desHard.add(des.get(i));
                default:
                    break;
            }
        }
        switch (targetGameType)
        {
            case ("简单"):
                des = desEasy;
                break;
            case ("一般"):
                des = desCommon;
                break;
            case ("困难"):
                des = desHard;
            default:
                break;
        }
        String[][] data = new String[des.size()][5];
        for (int i=0;i< des.size();i++)
        {
            String str = des.get(i);
            String[] strSpilt = str.split("~");
            data[i][0] = String.valueOf(i+1);
            for (int j=1;j< strSpilt.length+1;j++)
            {
                String[] temp = strSpilt[j-1].split(":");
                data[i][j] = temp[1];
            }
        }
        String[] columnsName = {
                "玩家名次",
                "游戏难度",
                "玩家名称",
                "得分",
                "结束时间"
        };
        TableModel tbM = new DefaultTableModel(data,columnsName);
        ScoreTable.setModel(tbM);
        ScoreTable.setVisible(true);
    }
}

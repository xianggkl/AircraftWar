package edu.hitsz.DAO;

import edu.hitsz.Player.Player;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO{

    @Override
    public void saveOnePlayerData(Path path, Player player) {
        try{
            if (!Files.exists(path))
            {
                Files.createFile(path);
            }
            String des = player.getStringPlayerData();
            Files.writeString(path,des, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            Files.writeString(path,"\n",StandardOpenOption.APPEND);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOnePlayerData(Path path, String name) {
        try{
            if (Files.exists(path))
            {
                int index = 0;
                List<String> playersDataStrings = Files.readAllLines(path,StandardCharsets.UTF_8);
                for (int i=0;i<playersDataStrings.size();i++)
                {
                    String[] strs = playersDataStrings.get(i).split("~");
                    String[] strss = strs[1].split(":");
                    if (strss[1].equals(name))
                    {
                        playersDataStrings.remove(i);
                        break;
                    }
                }
                Files.writeString(path,"");
                for (int i=0;i<playersDataStrings.size();i++)
                {
                    Files.writeString(path,playersDataStrings.get(i),StandardCharsets.UTF_8,StandardOpenOption.APPEND);
                    Files.writeString(path,"\n",StandardOpenOption.APPEND);
                }
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getRankedPlayersData(Path path) {
        List<String> res = new LinkedList<String>();
        try{
            if (Files.exists(path))
            {
                List<String> playersDataStrings = Files.readAllLines(path,StandardCharsets.UTF_8);
                res = sortPlayersDataByScore(playersDataStrings);
            }
            else{
                System.out.println("Path is not existed!");
                res = new LinkedList<String>();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return res;
    }


    @Override
    public void printPlayersDataList(Path path) {
        List<String> des = getRankedPlayersData(path);
        System.out.println("\n");
        System.out.println("Here are the LIST !");
        for (int i=0;i<des.size();i++)
        {
            System.out.println(des.get(i));
        }
    }

    private List<String> sortPlayersDataByScore(List<String> playerDataStrings)
    {
        List<String> res = playerDataStrings;
        List<Integer> playerScore = new LinkedList<Integer>();
        for (int i=0;i<playerDataStrings.size();i++)
        {
            String temp = playerDataStrings.get(i);
            String[] dir = temp.split("~");
            //得到分数
            temp = dir[2];
            dir = temp.split(":");
            temp = dir[1];
            playerScore.add(Integer.parseInt(temp));
        }
        for (int i=0;i<playerDataStrings.size();i++)
        {
            for (int j=0;j<playerDataStrings.size();j++)
            {
                if (playerScore.get(i) > playerScore.get(j))
                {
                    Integer dTemp = playerScore.get(i);
                    playerScore.set(i,playerScore.get(j));
                    playerScore.set(j,dTemp);
                    String dStr = res.get(i);
                    res.set(i,res.get(j));
                    res.set(j,dStr);
                }
            }
        }
        for (int i=0;i<res.size();i++)
        {
            int j = i + 1;
            res.set(i,(res.get(i)));
        }
        return res;
    }
}

package edu.hitsz.Player;

import edu.hitsz.Events.Events;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class Player {
    private String gameType;
    private String playerName;
    private int playerScore;
    private Date playerEndingDate;
    private Dictionary<Events.GameType,String> dic;

    public Player()
    {
        dic = new Hashtable<>();
        dic.put(Events.GameType.EASY_GAME,"简单");
        dic.put(Events.GameType.COMMON_GAME,"一般");
        dic.put(Events.GameType.HARD_GAME,"困难");
        this.gameType = dic.get(Events.gameType);
        this.playerName = "UserName";
        this.playerScore = 0;
        this.playerEndingDate = new Date();
    }

    public Player(String playerName, int playerScore)
    {
        this();
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public String getStringPlayerData()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String dateString = dateFormat.format(this.playerEndingDate);
        String des = "GameType:" + this.gameType + "~" + "PlayerName:" + this.playerName + "~" + "PlayerScore:" + this.playerScore + "~" + "PlayerGameEndingDate:" + dateString;
        return des;
    }
}

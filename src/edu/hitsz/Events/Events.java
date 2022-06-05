package edu.hitsz.Events;

public class Events {
    //在这个类放置一些锁和游戏难度枚举
    public static final Object IS_GAME_BEGIN = new Object();
    public static final Object IS_GAME_STAYING = new Object();
    public static final Object IS_GET_PLAYER_NAME = new Object();
    public static final Object IS_GAME_END = new Object();

    public static GameType gameType;
    public static MusicType musicType = MusicType.OPEN;

    public enum GameType{
        EASY_GAME,
        COMMON_GAME,
        HARD_GAME
    }
    public enum MusicType{
        OPEN,
        CLOSE
    }
}

package edu.hitsz.application;

public class VideoManager {
    public static String BGM_BACKGROUND_WAV;
    public static String BGM_BOSS_WAV;
    public static String BGM_BOMB_EXPLOSION_WAV;
    public static String BGM_BULLET_SHOOT_WAV;
    public static String BGM_BULLET_HIT_WAV;
    public static String BGM_GAME_OVER_WAV;
    public static String BGM_SUPPLY_GET_WAV;

    static {
        BGM_BACKGROUND_WAV = "src/videos/bgm.wav";
        BGM_BOSS_WAV = "src/videos/bgm_boss.wav";
        BGM_BOMB_EXPLOSION_WAV = "src/videos/bomb_explosion.wav";
        BGM_BULLET_SHOOT_WAV = "src/videos/bullet.wav";
        BGM_BULLET_HIT_WAV = "src/videos/bullet_hit.wav";
        BGM_GAME_OVER_WAV = "src/videos/game_over.wav";
        BGM_SUPPLY_GET_WAV = "src/videos/get_supply.wav";
    }
}

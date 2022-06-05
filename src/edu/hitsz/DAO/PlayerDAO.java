package edu.hitsz.DAO;

import edu.hitsz.Player.Player;

import java.nio.file.Path;
import java.util.List;

public interface PlayerDAO {
    void saveOnePlayerData(Path path, Player player);
    void deleteOnePlayerData(Path path, String name);
    List<String> getRankedPlayersData(Path path);
    void printPlayersDataList(Path path);
}

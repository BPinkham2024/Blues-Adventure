package level;

import java.util.ArrayList;

import objects.Coin;
import objects.Gem;
import utils.Helpers;
import utils.LoadSave;

public class Level {

    private int[][] levelData;
    private ArrayList<Coin> coins;
    private ArrayList<Gem> gems;

    public Level(int[][] levelData) {
        this.levelData = levelData;
        createCoins();
        createGems();
    }

    private void createCoins() {
        coins = Helpers.GetCoins(LoadSave.GetMap(LoadSave.LEVEL_ONE_DATA));
    }
    private void createGems() {
        gems = Helpers.GetGems(LoadSave.GetMap(LoadSave.LEVEL_ONE_DATA));
    }

    public int getIndex(int x,  int y) {
        return levelData[y][x];
    }

    public int[][] getLevelData() {
        return levelData;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public ArrayList<Gem> getGems() {
        return gems;
    }
    
}

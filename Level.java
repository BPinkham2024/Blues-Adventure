public class Level {

    private int[][] levelData;

    public Level(int[][] levelData) {
        this.levelData = levelData;
    }

    public int getIndex(int x,  int y) {
        
        return levelData[y][x];
    }
    
}

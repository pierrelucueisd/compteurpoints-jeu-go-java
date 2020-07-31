package PointCalculator.PlayersStats;

import Player.Color;

public class PlayersScoreStats {
    private int blackPoints = 0;
    private int whitePoints = 0;

    public int getBlackPoints() {
        return blackPoints;
    }

    public int getWhitePoints() {
        return whitePoints;
    }

    public void addPlayerPoints(Color c, int points){
        if(c == Color.White) whitePoints += points;
        else if(c == Color.Black) blackPoints += points;
    }
}

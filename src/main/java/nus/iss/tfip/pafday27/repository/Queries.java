package nus.iss.tfip.pafday27.repository;

public interface Queries {

    public static final String SQLGetGames = "SELECT gid, name FROM game LIMIT ? OFFSET ?;";
    public static final String SQLGetGameComments = "SELECT * FROM comment WHERE gid=?;";
}

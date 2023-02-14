package nus.iss.tfip.pafday27.repository;

public interface Queries {

    public static final String SQLGetGames = "SELECT gid, name FROM game LIMIT ? OFFSET ?;";
    public static final String SQLGetOneGame = "SELECT gid, name, image, url FROM game WHERE gid=?;";
    public static final String SQLGetGameComments = "SELECT * FROM comment WHERE gid=? ORDER BY rating ASC LIMIT 3;";
    public static final String SQLAvgRating = "SELECT AVG(rating) FROM comment WHERE gid=?;";

    public static final String COLLECTION_COMMENT = "comment";

}

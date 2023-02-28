package nus.iss.tfip.pafday27;

public interface Constants {

    public static final String DB_NETFLIX = "netflix";

    public static final String SQLGetGames = "SELECT gid, name FROM game LIMIT ? OFFSET ?;";
    public static final String SQLGetOneGame = "SELECT gid, name, image, url FROM game WHERE gid=?;";
    public static final String SQLGetGameComments = "SELECT * FROM comment WHERE gid=? ORDER BY rating ASC LIMIT 3;";
    public static final String SQLAvgRating = "SELECT AVG(rating) FROM comment WHERE gid=?;";

    public static final String COLLECTION_COMMENT = "comment";

    public static final String DB_BGG = "netflix";
    public static final String COLLECTION_TV = "tv";
    public static final String FIELD_LANGUAGE = "language";
    public static final String FIELD_OBJ_ID = "_id";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_URL = "url";
    public static final String FIELD_GENRES = "genres";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_RATING = "rating";
    public static final String FIELD_AVG = "average";
    public static final String FIELD_RATING_AVG = "rating.average";
    public static final String FIELD_RUNTIME = "runtime";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_TOTAL = "total";
    public static final String MONGOEXP_TITLEANDRUNTIME = """
        $concat: [ "$name", " (", { $toString: "$runtime" }, ")"]
    """;
}

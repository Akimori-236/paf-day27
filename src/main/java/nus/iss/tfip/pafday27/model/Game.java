package nus.iss.tfip.pafday27.model;

import lombok.Data;

@Data
public class Game {
    private Integer gid;
    private String name;
    private String image;
    private String url;

    public Game create() {
        return null;
    }
}

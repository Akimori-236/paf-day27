package nus.iss.tfip.pafday27.model;

import lombok.Data;

@Data
public class Comment {
    private String c_id;
    private String user;
    private Integer rating;
    private String c_text;
    private Integer gid;

    
}

package nus.iss.tfip.pafday27.model;

import java.util.UUID;

import org.bson.Document;

import lombok.Data;

@Data
public class Comment {
    private String c_id;
    private String user;
    private Integer rating;
    private String c_text;
    private Integer gid;

    public Document toDocument() {
        Document doc = new Document();
        String c_id = UUID.randomUUID().toString().substring(0, 8);
        doc.put("c_id", c_id);
        doc.put("user", getUser());
        doc.put("rating", getRating());
        doc.put("c_text", getC_text());
        doc.put("gid", getGid());
        return doc;
    }
}

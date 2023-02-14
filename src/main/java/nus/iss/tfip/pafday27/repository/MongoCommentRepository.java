package nus.iss.tfip.pafday27.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoCommentRepository implements Queries{

    @Autowired
    private MongoTemplate template;

    // db.comment.insert({gid:"", user:"", rating:"", c_text:"",})
    public String insertComment(Document comment) {
        Document newDoc = template.insert(comment, COLLECTION_COMMENT);
        String c_id = newDoc.getString("c_id");
        return c_id;
    }
}

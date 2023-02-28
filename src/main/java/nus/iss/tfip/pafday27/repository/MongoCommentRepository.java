package nus.iss.tfip.pafday27.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.pafday27.Constants;

@Repository
public class MongoCommentRepository implements Constants {

    @Autowired @Qualifier(DB_BGG)
    private MongoTemplate template;

    // db.comment.insert({gid:"", user:"", rating:"", c_text:"",})
    public String insertComment(Document comment) {
        Document newDoc = template.insert(comment, COLLECTION_COMMENT);
        String c_id = newDoc.getString("c_id");
        return c_id;
    }

    public List<Document> searchCommentText(String... text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);

        TextQuery query = TextQuery.queryText(criteria)
                .sortByScore();
        query.setScoreFieldName("textScore");
        List<Document> docList = template.find(query.limit(5), Document.class, COLLECTION_COMMENT);
        // custom list processing
        return docList;
    }

    /*
        db.getCollection("game").aggregate([
            {
                $match: {
                    name: { $regex: "^lord", $options: "i" }
                }
            },
            {
                $lookup: {
                    from: "comment",
                    foreignField: "gid",
                    localField: "gid",
                    as: "comments"
                }
            },
            {
                $unwind: "$comments"
            },
            {
                $sort: {
                    "comment.rating": -1
                }
            },
            {
                $limit: 3
            }
        ])
     */
}

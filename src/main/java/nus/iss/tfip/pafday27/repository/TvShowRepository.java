package nus.iss.tfip.pafday27.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.BucketOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.pafday27.Constants;

@Repository
public class TvShowRepository implements Constants {
    @Autowired
    @Qualifier(DB_NETFLIX)
    private MongoTemplate template;

    /*
     * ddb.tv.aggregate([
     * {
     * $match : {
     * language: {$regex : "english", $options : 'i'}
     * }
     * }
     * ])
     */
    public List<Document> find() {
        Criteria criteria = Criteria.where(FIELD_LANGUAGE).regex("english", "i");
        // $match
        MatchOperation matchLang = Aggregation.match(criteria);
        // $project
        ProjectionOperation project = Aggregation.project()
                .andExclude(FIELD_OBJ_ID)
                .andInclude(FIELD_URL, FIELD_NAME, FIELD_GENRES);
        // $limit
        LimitOperation limit = Aggregation.limit(10);

        Aggregation pipeline = Aggregation.newAggregation(matchLang, project, limit);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
                .getMappedResults();
    }

    /*
     * db.tv.aggregate([
     * {
     * $group: {
     * _id: "$runtime",
     * shows: {
     * $push: {
     * title: "$name",
     * language: "$language"
     * }
     * },
     * total: {
     * $sum: 1},
     * angRating: {
     * $avg: "$rating.average"}
     * }},
     * ])
     */
    public List<Document> groupShowsByRuntime() {
        GroupOperation groupRuntime = Aggregation.group(FIELD_RUNTIME)
                .push(FIELD_NAME).as(FIELD_TITLE)
                .count().as(FIELD_TOTAL);
        Aggregation pipeline = Aggregation.newAggregation(groupRuntime);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
                .getMappedResults();
    }

    public List<Document> getTitleAndRating() {
        ProjectionOperation project = Aggregation.project(FIELD_RUNTIME)
                .andExclude(FIELD_OBJ_ID)
                .and(
                        AggregationExpression.from(
                                MongoExpression.create(MONGOEXP_TITLEANDRUNTIME)))
                .as(FIELD_TITLE)
                .and(FIELD_RATING_AVG).as(FIELD_RATING);
        Aggregation pipeline = Aggregation.newAggregation(project);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
                .getMappedResults();
    }

    /*
     * db.tv.aggregate([
        * {
            * $unwind: "$genres"
        * },
        * {
            * $group: {
            * _id: "$genres",
            * total: {$sum:1}
        * }
        * },
     * ])
     */
    public List<Document> countGenres() {
        // $unwind
        UnwindOperation unwindGenres = Aggregation.unwind(FIELD_GENRES);
        // $group
        GroupOperation groupShowsByGenres = Aggregation.group(FIELD_GENRES)
                .count().as(FIELD_TOTAL);
        // $sort
        SortOperation sortByGenre = Aggregation.sort(Direction.ASC, FIELD_OBJ_ID);

        Aggregation pipeline = Aggregation.newAggregation(unwindGenres, groupShowsByGenres, sortByGenre);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
                .getMappedResults();
    }

    /*
     * db.tv.aggregate([
        * {
            * $bucket: {
                * groupBy: "$rating.average",
                * boundaries: [3,6,9],
                * default: '>=9'
            * }
        * }
     * ])
     */
    public List<Document> histogramRatings() {
        // $bucket
        BucketOperation bucketRatings = Aggregation.bucket(FIELD_RATING_AVG)
                .withBoundaries(3, 6, 9)
                .withDefaultBucket(">=9")
                .andOutput(FIELD_NAME).push().as(FIELD_TITLE);
                
        Aggregation pipeline = Aggregation.newAggregation(bucketRatings);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
                .getMappedResults();
    }
}

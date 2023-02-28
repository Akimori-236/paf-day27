package nus.iss.tfip.pafday27;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nus.iss.tfip.pafday27.repository.MongoCommentRepository;
import nus.iss.tfip.pafday27.repository.TvShowRepository;

@SpringBootApplication
public class PafDay27Application implements CommandLineRunner {

	@Autowired
	private MongoCommentRepository commentRepo;

	@Autowired
	private TvShowRepository tvshowsRepo;

	public static void main(String[] args) {
		SpringApplication.run(PafDay27Application.class, args);
	}

	@Override
	public void run(String... args) {

		//List<Document> results = commentRepo.searchCommentText("enjoyed", "loved", "super");
		//List<Document> results = tvshowsRepo.find();
		//List<Document> results = tvshowsRepo.groupTvShowsByRuntime();
		List<Document> results = tvshowsRepo.getTitleAndRating();

		for (Document d: results)
			System.out.printf(">>> %s\n", d.toJson());
	}
}

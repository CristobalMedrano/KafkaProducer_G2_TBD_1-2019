package com.tbd.twitter;

import javax.annotation.PostConstruct;

import com.tbd.twitter.Analisis.Classifier;
import com.tbd.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

@Service
@Configurable
public class TwitterListener {

	@Autowired
	private Classifier classifier;
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private TwitterStream twitterStream;

	@Autowired
	private KafkaTemplate<String, Tweet> kafkaTemplate;

	@Value("${kafka.topicName}")
	private String jsonTopic;
	private HashMap<String, Double> stadistics = new HashMap<String, Double>();
	private Long positive;
	private Long negative;
	@PostConstruct
	public void run() {
		twitterStream.addListener(new StatusListener() {
			public void onStatus(Status status) {
				String userLocation = status.getUser().getLocation();
				if (2>0){
					stadistics = classifier.classify(status.getText());
					Tweet tweet = new Tweet(
							status.getId(),
							stadistics.get("positive"),
							stadistics.get("negative"),
							status.getUser().getName(),
							status.getText(),
							status.getFavoriteCount(),
							status.getUser().getFollowersCount(),
							status.getGeoLocation(),
							status.getUser().getLocation(),
							status.getRetweetCount(),
							status.getCreatedAt());
					System.out.println(tweet);
					kafkaTemplate.send(jsonTopic, tweet);
				}
			}

			@Override
			public void onException(Exception arg0) {
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
			}

			@Override
			public void onStallWarning(StallWarning arg0) {
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
			}
		});
		String[] bow=null;
		try {
			Resource resource=resourceLoader.getResource("classpath:bagofwords.txt");
			Scanner sc=new Scanner(resource.getFile());
			List<String> lines=new ArrayList<String>();
			while (sc.hasNextLine()) {
			  lines.add(sc.nextLine());
			}
			bow=lines.toArray(new String[0]);
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FilterQuery filter = new FilterQuery();
		filter.track(bow);
		filter.language(new String[]{"es"});
		twitterStream.filter(filter);
	}

	public TwitterStream getTwitterStream() {
		return twitterStream;
	}

	public void setTwitterStream(TwitterStream twitterStream) {
		this.twitterStream = twitterStream;
	}

	public HashMap<String,Double> classify(String text) {
		return this.classifier.classify(text);
	}

	public Classifier getClassifier() {
		return classifier;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}
}

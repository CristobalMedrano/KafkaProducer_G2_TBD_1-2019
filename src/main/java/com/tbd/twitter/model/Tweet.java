package com.tbd.twitter.model;

import twitter4j.GeoLocation;

import java.util.Date;

public class Tweet {

    private Long id;
    private Double positive;
    private Double negative;
    private String name;
    private String text;
    private Integer like;
    private Integer followers;
    private twitter4j.GeoLocation geoLocation;
    private String userLocation;
    private Integer retweet;
    private Date publicationDate;

    public Tweet() {
        super();
    }

    public Tweet(Long id, Double positive, Double negative, String name, String text, Integer like, Integer followers, GeoLocation geoLocation, String userLocation, Integer retweet, Date publicationDate) {
        this.id = id;
        this.positive = positive;
        this.negative = negative;
        this.name = name;
        this.text = text;
        this.like = like;
        this.followers = followers;
        this.geoLocation = geoLocation;
        this.userLocation = userLocation;
        this.retweet = retweet;
        this.publicationDate = publicationDate;
    }

    public Double getPositive() {
        return positive;
    }

    public void setPositive(Double positive) {
        this.positive = positive;
    }

    public Double getNegative() {
        return negative;
    }

    public void setNegative(Double negative) {
        this.negative = negative;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public twitter4j.GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(twitter4j.GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public Integer getRetweet() {
        return retweet;
    }

    public void setRetweet(Integer retweet) {
        this.retweet = retweet;
    }

    @Override
    public String toString() {
        return "{id="+ getId() + ", positivo: " + getPositive() + ", negativo: " + getNegative()+", name="+ getName() +", text="+ getText() +", like="+ getLike() +", followers="+ getFollowers() +", geoLocation="+ getGeoLocation() +", userLocation="+ getUserLocation() +", retweet="+ getRetweet() +", publicationDate="+ getPublicationDate()+"}";
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}

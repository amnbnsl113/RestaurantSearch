
package com.place.eat.resturantsearch.model.jsonmodel;

import com.google.gson.annotations.SerializedName;

public class AllReview {

    @SerializedName("rating")

    private String rating;
    @SerializedName("review_text")

    private String reviewText;
    @SerializedName("id")

    private String id;
    @SerializedName("rating_color")

    private String ratingColor;
    @SerializedName("review_time_friendly")

    private String reviewTimeFriendly;
    @SerializedName("rating_text")

    private String ratingText;
    @SerializedName("timestamp")

    private String timestamp;
    @SerializedName("likes")

    private String likes;
    @SerializedName("user")

    private User_ user;
    @SerializedName("comments_count")

    private String commentsCount;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getReviewTimeFriendly() {
        return reviewTimeFriendly;
    }

    public void setReviewTimeFriendly(String reviewTimeFriendly) {
        this.reviewTimeFriendly = reviewTimeFriendly;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

}


package com.place.eat.resturantsearch.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("id")

    private String id;
    @SerializedName("name")

    private String name;
    @SerializedName("url")

    private String url;
    @SerializedName("location")

    private Location location;
    @SerializedName("average_cost_for_two")

    private String averageCostForTwo;
    @SerializedName("price_range")

    private String priceRange;
    @SerializedName("currency")

    private String currency;
    @SerializedName("thumb")

    private String thumb;
    @SerializedName("featured_image")

    private String featuredImage;
    @SerializedName("photos_url")

    private String photosUrl;
    @SerializedName("menu_url")

    private String menuUrl;
    @SerializedName("events_url")

    private String eventsUrl;
    @SerializedName("user_rating")

    private UserRating userRating;
    @SerializedName("has_online_delivery")

    private String hasOnlineDelivery;
    @SerializedName("is_delivering_now")

    private String isDeliveringNow;
    @SerializedName("has_table_booking")

    private String hasTableBooking;
    @SerializedName("deeplink")

    private String deeplink;
    @SerializedName("cuisines")

    private String cuisines;
    @SerializedName("all_reviews_count")

    private String allReviewsCount;
    @SerializedName("photo_count")

    private String photoCount;
    @SerializedName("phone_numbers")

    private String phoneNumbers;
    @SerializedName("photos")

    private List<Photo> photos = null;
    @SerializedName("all_reviews")

    private List<AllReview> allReviews = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(String averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public String getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(String hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public String getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(String isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public String getHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(String hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getAllReviewsCount() {
        return allReviewsCount;
    }

    public void setAllReviewsCount(String allReviewsCount) {
        this.allReviewsCount = allReviewsCount;
    }

    public String getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(String photoCount) {
        this.photoCount = photoCount;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<AllReview> getAllReviews() {
        return allReviews;
    }

    public void setAllReviews(List<AllReview> allReviews) {
        this.allReviews = allReviews;
    }

}

package com.place.eat.resturantsearch.model.viewmodel;

/**
 * Created by aman on 12/9/17.
 */

public class RestaurantViewModel extends BaseViewModel {
    private String id;
    private String name;
    private String priceForTwo;
    private String thumpUrl;
    private String location;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPriceForTwo() {
        return priceForTwo;
    }

    public void setPriceForTwo(String priceForTwo) {
        this.priceForTwo = priceForTwo;
    }

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

    public String getThumpUrl() {
        return thumpUrl;
    }

    public void setThumpUrl(String thumpUrl) {
        this.thumpUrl = thumpUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantViewModel that = (RestaurantViewModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

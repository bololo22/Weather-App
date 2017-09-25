package model.channel;

import java.util.List;

import model.channel.item.Condition;
import model.channel.item.Forecast;
import model.channel.item.Guid;

/**
 * Created by manolofernandez on 9/25/17.
 */

public class Item {
    private String title;
    private double latitude;
    private double longitude;
    private String link;
    private String pubDate;
    private Condition condition;
    private List<Forecast> forecast;
    private String description;
    private Guid guid;

    public String getTitle() {
        return title;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public Condition getCondition() {
        return condition;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public String getDescription() {
        return description;
    }

    public Guid getGuid() {
        return guid;
    }
}

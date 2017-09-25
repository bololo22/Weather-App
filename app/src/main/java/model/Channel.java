package model;

import model.channel.Astronomy;
import model.channel.Athmosphere;
import model.channel.Image;
import model.channel.Item;
import model.channel.Location;
import model.channel.Units;
import model.channel.Wind;

/**
 * Created by manolofernandez on 9/25/17.
 */

public class Channel {
    private Units units;
    private String title;
    private String link;
    private String description;
    private String language;
    private String lastBuildDate;
    private int ttl;
    private Location location;
    private Wind wind;
    private Athmosphere athmosphere;
    private Astronomy astronomy;
    private Image image;
    private Item item;

    public Units getUnits() {
        return units;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public int getTtl() {
        return ttl;
    }

    public Location getLocation() {
        return location;
    }

    public Wind getWind() {
        return wind;
    }

    public Athmosphere getAthmosphere() {
        return athmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public Image getImage() {
        return image;
    }

    public Item getItem() {
        return item;
    }
}

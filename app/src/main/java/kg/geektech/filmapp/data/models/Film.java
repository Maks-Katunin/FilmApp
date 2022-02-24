package kg.geektech.filmapp.data.models;

import com.google.gson.annotations.SerializedName;

public class Film {
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("original_title")
    String originalTitle;
    @SerializedName("description")
    String description;
    @SerializedName("director")
    String director;
    @SerializedName("producer")
    String producer;
@SerializedName("release_date")
    String releaseDate;
@SerializedName("running_time")
    String runningTime;
@SerializedName("movie_banner")
String movieBanner;

    public String getMovieBanner() {
        return movieBanner;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public String getDirector() {
        return director;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }
}


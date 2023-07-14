package sg.edu.rp.c346.id22022612.ndpsongs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Songs implements Serializable {
    private int id;
    private String title;
    private String singer;
    private int year;
    private int rating;

    public Songs(int id, String title, String singer, int year, int rating) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "\n" + title + "\n" + singer + "\n" + year + "\n" + rating;
    }
}

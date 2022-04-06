package com.example.doan;

public class Entry {
    public String title;
    public String description;
    public String link;
    public String pubDate;
    public String image;

    public Entry() {
    }

    public Entry(String title, String description, String link, String pubDate, String image) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return title+description+link+pubDate+image;
    }

    public Entry entryCopy() {
        Entry copy = new Entry();
        copy.title = this.title;
        copy.description = this.description;
        copy.link = this.link;
        copy.pubDate = this.pubDate;
        copy.image = this.image;
        return copy;
    }
}


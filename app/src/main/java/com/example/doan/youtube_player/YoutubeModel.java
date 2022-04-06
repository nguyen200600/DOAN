package com.example.doan.youtube_player;

public class YoutubeModel {
    String videoId;

    public YoutubeModel() {

    }

    public YoutubeModel(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}

package com.yxx.recording;

public class RecordingText {
  private String title;
  private String content;
  private String username;

    public RecordingText(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RecordingText(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}

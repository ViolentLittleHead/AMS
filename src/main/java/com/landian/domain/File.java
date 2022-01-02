package com.landian.domain;

public class File {
    private int id;
    private String username;
    private String path;
    private String date;
    private String fileName;

    public File() {
    }

    public File(int id, String username, String path, String date, String fileName) {
        this.id = id;
        this.username = username;
        this.path = path;
        this.date = date;
        this.fileName = fileName;
    }

    public File(String username, String path, String date, String fileName) {
        this.username = username;
        this.path = path;
        this.date = date;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", path='" + path + '\'' +
                ", date='" + date + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}

package jesh.project.jeshproject.model;

public class Timeline {
    private int id;
    private String name;
    private int startTime;
    private int endTime;
    private int brightness;
    private int userID;

    public Timeline(int id, String name, int startTime, int endTime, int brightness, int userID) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.brightness = brightness;
        this.userID = userID;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getStartTime() {return startTime;}
    public void setStartTime(int startTime) {this.startTime = startTime;}
    public int getEndTime() {return endTime;}
    public void setEndTime(int endTime) {this.endTime = endTime;}
    public int getBrightness() {return brightness;}
    public void setBrightness(int brightness) {this.brightness = brightness;}
    public int getUserID() {return userID;}
}

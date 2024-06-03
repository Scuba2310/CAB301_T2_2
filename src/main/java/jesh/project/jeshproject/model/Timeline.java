package jesh.project.jeshproject.model;

public class Timeline {
    private int Timeline_id;
    private int Timeslot;
    private String Start_Time;
    private String End_Time;
    private int User_ID;


    public Timeline(int Timeslot, String Start_Time, String End_Time, int User_ID) {
        this.Timeslot = Timeslot;
        this.Start_Time = Start_Time;
        this.End_Time = End_Time;
        this.User_ID = User_ID;
    }

    // Timeline_id getter and setter
    public int getTimeline_id() {
        return Timeline_id;
    }

    public void setTimeline_id(int Timeline_id) {
        this.Timeline_id = Timeline_id; }

    // Timeslot getter and setter
    public int getTimeslot() {
        return Timeslot;
    }

    public void setTimeslot(int Timeslot) {
        this.Timeslot = Timeslot; }


    // Start_Time getter and setter
    public String getStart_Time() {
        return Start_Time;
    }
    public void setStart_Time(String Start_Time) {
        this.Start_Time = Start_Time;
    }


    // End_Time getter and setter
    public String getEnd_Time() {
        return End_Time;
    }
    public void setEnd_Time(String End_Time) {
        this.End_Time = End_Time;
    }

    // User_ID getter and setter
    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID; }

}

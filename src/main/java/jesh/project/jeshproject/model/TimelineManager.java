package jesh.project.jeshproject.model;

import java.sql.Time;
import java.util.ArrayList;

public class TimelineManager {
    private IUserDAO userDAO;

    public TimelineManager(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean addTimeline(Timeline timeline) {
        return userDAO.addTimeline(timeline);
    }
    public Timeline getTimeline(String name, int userID) {
        return userDAO.getTimeline(name, userID);
    }
    public boolean updateTimeline(Timeline timeline) {
        return userDAO.updateTimeline(timeline);
    }
    public ArrayList<String> getUserTimelines() {
        return userDAO.getTimelineNames();
    }

}

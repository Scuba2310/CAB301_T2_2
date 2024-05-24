package jesh.project.jeshproject.model;

import java.sql.Time;

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
    public boolean updateTimeline(String name, int userID, int startTime, int endTime, int brightness) {
        return userDAO.updateTimeline(name, userID, startTime, endTime, brightness);
    }

}

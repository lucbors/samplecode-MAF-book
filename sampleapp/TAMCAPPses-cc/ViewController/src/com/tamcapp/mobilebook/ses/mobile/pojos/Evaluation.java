package com.tamcapp.mobilebook.ses.mobile.pojos;

public class Evaluation {

    int id;
    int sessionId;
    int attendeeId;
    int presenterId;
    int score;

    public Evaluation() {
        super();
    }

    public Evaluation(int id, int sessionId, int attendeeId, int presenterId, int score) {
      super();
        this.id = id;
        this.sessionId = sessionId;
        this.attendeeId = attendeeId;
        this.presenterId = presenterId;
        this.score = score;


    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setAttendeeId(int attendeeId) {
        this.attendeeId = attendeeId;
    }

    public int getAttendeeId() {
        return attendeeId;
    }

    public void setPresenterId(int presenterId) {
        this.presenterId = presenterId;
    }

    public int getPresenterId() {
        return presenterId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
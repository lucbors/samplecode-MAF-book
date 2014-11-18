package com.tamcapp.mobilebook.mobile.pojos;

public class ScoreCount {


    int score;
    int count;
    public ScoreCount() {
        super();
    }

    public ScoreCount(int score, int count) {
        super();
        this.score=score;
        this.count=count;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}

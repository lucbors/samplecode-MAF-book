package com.tamcapp.mobilebook.ses.mobile.model;


import com.tamcapp.mobilebook.ses.mobile.pojos.Evaluation;
import com.tamcapp.mobilebook.ses.mobile.pojos.ScoreCount;

import java.util.ArrayList;
import java.util.List;

public class Evaluations {
    private List s_evaluations = null;
    private List s_scores = null;
    
    private int avg_presentation_skills = 4;

    private int avg_added_value=3;

    private int avg_material=4;

    public Evaluations() {
        super();
    }
    
    
    public int getSkills(){
        return avg_presentation_skills;
    }
            
    
    public int getAddedValue(){
        return avg_added_value;
    }
    
    public int getMaterials(){
        return avg_material;
    }
            
    public ScoreCount[] getScores(){
        
        ScoreCount[] scores = null;
        s_scores = new ArrayList();
        s_scores.add(new ScoreCount(1,2));
        s_scores.add(new ScoreCount(2,4));                                         
        s_scores.add(new ScoreCount(3,8));
        s_scores.add(new ScoreCount(4,11));
        s_scores.add(new ScoreCount(5,3));
        scores = (ScoreCount[]) s_scores.toArray(new ScoreCount[s_scores.size()]);
        return scores;
        
        
    }
    public Evaluation[] getEvaluations() {
          //This Method gets a list of the evaluations
          Evaluation[] evaluations = null;
           s_evaluations = new ArrayList();
            s_evaluations.add(new Evaluation(1,1,1,2,1));
            s_evaluations.add(new Evaluation(2,1,2,2,2));                                         
            s_evaluations.add(new Evaluation(3,1,3,2,3));
            s_evaluations.add(new Evaluation(4,1,4,2,3));
            s_evaluations.add(new Evaluation(5,1,5,2,3));
            s_evaluations.add(new Evaluation(6,1,6,2,4));
            s_evaluations.add(new Evaluation(7,1,7,2,4));
            s_evaluations.add(new Evaluation(8,1,8,2,5));
            s_evaluations.add(new Evaluation(9,1,9,2,4));
          
        //  evaluations = (Evaluation[]) s_evaluations.toArray(new ConferenceSession[s_evaluations.size()]);
        evaluations = (Evaluation[]) s_evaluations.toArray(new Evaluation[s_evaluations.size()]);
                
          return evaluations;
        } 
}

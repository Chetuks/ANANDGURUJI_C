package com.ananada.addme.neutrinos.anandaguruji.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahiti on 20/6/15.
 */
public class Questions {

    String question_id;
    String question;
    String answers;
    List<Options> options = new ArrayList<>();
    String percentage;
    String average;

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }



    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

}

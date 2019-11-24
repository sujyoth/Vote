package com.skar.vote.cardstackview;

import java.util.ArrayList;
import java.util.List;

public class Question {

    public String uID;
    public String question;
    public String url;
    public String choice_1;
    public String choice_2;
    public String choice_3;
    public String choice_4;
    public int choice_1_choosers;
    public int choice_2_choosers;
    public int choice_3_choosers;
    public int choice_4_choosers;

    public Question() {

    }

    public Question(String question, String url, String choice_1, String choice_2, int choice_1_choosers, int choice_2_choosers) {
        this.question = question;
        this.url = url;
        this.choice_1 = choice_1;
        this.choice_2 = choice_2;
        this.choice_1_choosers = choice_1_choosers;
        this.choice_2_choosers = choice_2_choosers;
    }

    public Question(String question, String url, String choice_1, String choice_2, String choice_3, int choice_1_choosers, int choice_2_choosers, int choice_3_choosers) {
        this.question = question;
        this.url = url;
        this.choice_1 = choice_1;
        this.choice_2 = choice_2;
        this.choice_3 = choice_3;
        this.choice_1_choosers = choice_1_choosers;
        this.choice_2_choosers = choice_2_choosers;
        this.choice_3_choosers = choice_3_choosers;
    }

    public Question(String question, String url, String choice_1, String choice_2, String choice_3, String choice_4, int choice_1_choosers, int choice_2_choosers, int choice_3_choosers, int choice_4_choosers) {
        this.question = question;
        this.url = url;
        this.choice_1 = choice_1;
        this.choice_2 = choice_2;
        this.choice_3 = choice_3;
        this.choice_4 = choice_4;
        this.choice_1_choosers = choice_1_choosers;
        this.choice_2_choosers = choice_2_choosers;
        this.choice_3_choosers = choice_3_choosers;
        this.choice_4_choosers = choice_4_choosers;
    }

    public List<String> getChoices() {
        List<String> choiceList = new ArrayList<>();
        choiceList.add(this.choice_1);
        choiceList.add(this.choice_2);
        if (this.choice_3 != null)
            choiceList.add(this.choice_3);
        if (this.choice_4 != null)
            choiceList.add(this.choice_4);
        return choiceList;
    }

    public int getChoice_1_choosers() {
        return choice_1_choosers;
    }

    public void setChoice_1_choosers(int choice_1_choosers) {
        this.choice_1_choosers = choice_1_choosers;
    }

    public int getChoice_2_choosers() {
        return choice_2_choosers;
    }

    public void setChoice_2_choosers(int choice_2_choosers) {
        this.choice_2_choosers = choice_2_choosers;
    }

    public int getChoice_3_choosers() {
        return choice_3_choosers;
    }

    public void setChoice_3_choosers(int choice_3_choosers) {
        this.choice_3_choosers = choice_3_choosers;
    }

    public int getChoice_4_choosers() {
        return choice_4_choosers;
    }

    public void setChoice_4_choosers(int choice_4_choosers) {
        this.choice_4_choosers = choice_4_choosers;
    }

    public String getChoice_1() {
        return choice_1;
    }

    public void setChoice_1(String choice_1) {
        this.choice_1 = choice_1;
    }

    public String getChoice_2() {
        return choice_2;
    }

    public void setChoice_2(String choice_2) {
        this.choice_2 = choice_2;
    }

    public String getChoice_3() {
        return choice_3;
    }

    public void setChoice_3(String choice_3) {
        this.choice_3 = choice_3;
    }

    public String getChoice_4() {
        return choice_4;
    }

    public void setChoice_4(String choice_4) {
        this.choice_4 = choice_4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.skar.vote.cardstackview;

import java.util.ArrayList;
import java.util.List;

public class Question {

    public String uID;
    public String questionSentence;
    public String choice1;
    public String choice2;
    public String choice3;
    public String choice4;

    public Question(String uID, String question, String choice1, String choice2) {
        this.uID = uID;
        this.questionSentence = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public Question(String uID, String question, String choice1, String choice2, String choice3) {
        this.uID = uID;
        this.questionSentence = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
    }

    public Question(String uID, String question, String choice1, String choice2, String choice3, String choice4) {
        this.uID = uID;
        this.questionSentence = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
    }

    public List<String> getChoices() {
        List<String> choiceList = new ArrayList<>();
        choiceList.add(this.choice1);
        choiceList.add(this.choice2);
        if (this.choice3 != null)
            choiceList.add(this.choice3);
        if (this.choice4 != null)
            choiceList.add(this.choice4);
        return choiceList;
    }
}

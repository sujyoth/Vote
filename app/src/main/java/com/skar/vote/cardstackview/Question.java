package com.skar.vote.cardstackview;

public class Question {

    public String uID;
    public String questionSentence;
    public String choice1;
    public String choice2;
    public String choice3;

    public Question(String uID, String question, String choice1, String choice2, String choice3) {
        this.uID = uID;
        this.questionSentence = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
    }
}

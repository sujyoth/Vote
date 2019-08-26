package com.skar.vote;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skar.vote.cardstackview.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.List;

public class ActivityQuestions extends AppCompatActivity implements CardStackListener {

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private CardStackView cardStackView;
    private View viewPreviousCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        setupCardStackView();
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
        Log.d("CardStackView", "onCardDragging: d = " + direction.name() + ", r = " + ratio);
    }

    @Override
    public void onCardSwiped(Direction direction) {
        CardStackAdapter.choice = null; // resetting choice for every new card
        adapter.incrementQuestionNumber();

        RadioGroup rgChoice = viewPreviousCard.findViewById(R.id.choice);
        rgChoice.removeAllViews(); // remove choices
        viewPreviousCard.findViewById(R.id.choice_linear_layout).setVisibility(View.VISIBLE);
        viewPreviousCard.findViewById(R.id.pieChart).setVisibility(View.GONE);

        Log.d("CardStackView", "onCardSwiped: p = " + manager.getTopPosition() + ", d = " + direction);
    }

    @Override
    public void onCardRewound() {
        Log.d("CardStackView", "onCardRewound: " + manager.getTopPosition());
    }

    @Override
    public void onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled:" + manager.getTopPosition());
    }

    @Override
    public void onCardAppeared(View view, int position) {
        RadioGroup rgChoice = view.findViewById(R.id.choice);
        RadioButton[] rbChoice = new RadioButton[4];

        List<String> choiceList = adapter.getCurrentQuestion().getChoices();
        Integer i = 0;
        // add choices for current question
        for (String choice : choiceList) {
            rbChoice[i] = new RadioButton(view.getContext());
            rbChoice[i].setText(choice);
            rbChoice[i].setTextColor(Color.WHITE);
            rbChoice[i].setTextSize(25);
            rbChoice[i].setId(i);
            rbChoice[i].setTag(R.id.TAG_CHOICE_NUMBER, "choice" + (i + 1));
            rgChoice.addView(rbChoice[i]);
            i++;
        }
        TextView tvQuestion = findViewById(R.id.question_text);
        tvQuestion.setText(adapter.getCurrentQuestion().questionSentence);
    }

    @Override
    public void onCardDisappeared(View view, int position) {
        viewPreviousCard = view; // get view of the swiped card
    }

    private void setupCardStackView() {
        initialize();
    }

    private void initialize() {
        manager = new CardStackLayoutManager(this, this);
        manager.setStackFrom(StackFrom.Bottom);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.2f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        adapter = new CardStackAdapter(this);
        cardStackView = findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }

}

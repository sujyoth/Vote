package com.skar.vote;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.skar.vote.cardstackview.CardStackAdapter;
import com.skar.vote.cardstackview.Question;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;
import java.util.List;

public class ActivityQuestions extends AppCompatActivity implements CardStackListener {

    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private CardStackView cardStackView;

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
        if (adapter.getChoice() != null)
            Toast.makeText(this, adapter.getChoice(), Toast.LENGTH_SHORT).show();
        CardStackAdapter.choice = null;
        Log.d("CardStackView", "onCardSwiped: p = " + manager.getTopPosition() + ", d = " + direction);
        if (manager.getTopPosition() == adapter.getItemCount() - 5) {
            paginate();
        }
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
    }

    @Override
    public void onCardDisappeared(View view, int position) {
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
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        adapter = new CardStackAdapter(this, createQuestions());
        cardStackView = findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }

    private void paginate() {
        List<Question> newList = new ArrayList<Question>() {{
            addAll(adapter.getQuestions());
            addAll(createQuestions());
        }};
        adapter.setQuestions(newList);
    }

    private List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("xxx", "Are you sure?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Do you think?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Where?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Here?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "How?", "Yes", "No", "Not Sure"));
        return questions;
    }

}

package com.skar.vote.cardstackview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skar.vote.R;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    public static String choice;
    private LayoutInflater inflater;
    private List<Question> questions;

    public CardStackAdapter(Context context, List<Question> questions) {
        this.inflater = LayoutInflater.from(context);
        this.questions = questions;
    }

    public String getChoice() {
        return choice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_spot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Question question = questions.get(position);

        holder.tvQuestion.setText(question.questionSentence);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), question.questionSentence, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        RadioGroup radioChoice;
        RadioButton radioSelected;

        ViewHolder(final View view) {
            super(view);
            this.radioChoice = view.findViewById(R.id.choice);
            this.tvQuestion = view.findViewById(R.id.question_text);

            radioChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    radioSelected = view.findViewById(radioChoice.getCheckedRadioButtonId());
                    if (radioSelected.getText() != null)
                        CardStackAdapter.choice = radioSelected.getText().toString();
                }
            });
        }
    }

}

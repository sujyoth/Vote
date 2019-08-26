package com.skar.vote.cardstackview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.skar.vote.R;

import java.util.ArrayList;
import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    public static String choice;
    private LayoutInflater inflater;
    private List<Question> questions;
    public Integer questionNumber = 0;
    private Question question;

    public CardStackAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        createQuestions();
    }

    public String getChoice() {
        return choice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_choice_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        question = questions.get(questionNumber);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), question.questionSentence, Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void createQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("xxx", "Are you sure?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Do you think?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Where?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Here?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "How?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Do you think?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Where?", "Yes", "No", "Not Sure"));
        questions.add(new Question("xxx", "Here?", "Yes", "No", "Not Sure"));
        this.questions = questions;
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

    public String getCurrentQuestion() {
        String currentQuestion = questions.get(questionNumber).questionSentence;
        return currentQuestion;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RadioGroup radioChoice;
        RadioButton radioSelected;
        PieChart pieChart;
        LinearLayout linearLayout;

        ViewHolder(final View view) {
            super(view);

                this.radioChoice = view.findViewById(R.id.choice);
            this.pieChart = view.findViewById(R.id.pieChart);
            this.linearLayout = view.findViewById(R.id.choice_linear_layout);

                radioChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        radioSelected = view.findViewById(radioChoice.getCheckedRadioButtonId());
                        linearLayout.setVisibility(View.GONE);
                        pieChart.setVisibility(View.VISIBLE);


                        pieChart.setUsePercentValues(true);
                        pieChart.getDescription().setEnabled(false);
                        pieChart.setExtraOffsets(5, 10, 5, 5);
                        pieChart.setDragDecelerationFrictionCoef(0.95f);
                        pieChart.setDrawHoleEnabled(true);
                        pieChart.setHoleColor(android.R.color.white);
                        pieChart.setTransparentCircleRadius(61f);


                        ArrayList<PieEntry> yValues = new ArrayList<>();
                        yValues.add(new PieEntry(34f, "Bangladesh"));
                        yValues.add(new PieEntry(23f, "USA"));
                        yValues.add(new PieEntry(14f, "UK"));
                        yValues.add(new PieEntry(35f, "India"));
                        yValues.add(new PieEntry(40f, "Russia"));
                        yValues.add(new PieEntry(23f, "Japan"));


                        PieDataSet dataSet = new PieDataSet(yValues, "Countries");
                        dataSet.setSliceSpace(3f);
                        dataSet.setSelectionShift(5f);
                        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                        PieData pieData = new PieData(dataSet);
                        dataSet.setValueTextSize(10f);
                        dataSet.getValueTextColor(Color.YELLOW);

                        pieChart.setData(pieData);
                        if (radioSelected != null)
                            if (radioSelected.getText() != null)
                                CardStackAdapter.choice = radioSelected.getText().toString();
                    }
                });

        }
    }

}

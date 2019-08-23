package com.skar.vote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nex3z.flowlayout.FlowLayout;

public class ActivityInterests extends AppCompatActivity {
    Button Politics, Sports, Economy, Technology;
    Button newcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        Politics = findViewById(R.id.buttonPolitics);
        Economy = findViewById(R.id.buttonEconomy);
        Sports = findViewById(R.id.buttonSports);
        Technology = findViewById(R.id.buttonTechnology);


    }

    public void genPolitics(View view) {
        addbutton();
        Politics.setVisibility(View.GONE);
    }

    public void genTech(View view) {
        addbutton();
        Technology.setVisibility(View.GONE);
    }


    public void genEco(View view) {
        addbutton();
        Economy.setVisibility(View.GONE);
    }

    public void genSports(View view) {
        addbutton();
        Sports.setVisibility(View.GONE);
    }

    public void addbutton() {

        FlowLayout flowLayout = findViewById(R.id.layoutInterests);
        newcat = new Button(this);
        newcat.setText("New Category");
        flowLayout.addView(newcat);

    }
}

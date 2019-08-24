package com.skar.vote;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class ActivityQuestions extends AppCompatActivity {

    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> array;
    private SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        flingContainer = findViewById(R.id.frame);

        array = new ArrayList<>();
        array.add(new Data("http://www.delaroystudios.com/images/1.jpg", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n"));
        array.add(new Data("http://www.delaroystudios.com/images/2.jpg", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n"));
        array.add(new Data("http://www.delaroystudios.com/images/3.jpg", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n"));
        array.add(new Data("http://www.delaroystudios.com/images/4.jpg", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n"));
        array.add(new Data("http://www.delaroystudios.com/images/5.jpg", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n"));
        array.add(new Data("http://www.delaroystudios.com/images/6.jpg", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n"));
        array.add(new Data("http://www.delaroystudios.com/images/7.jpg", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n"));


        myAppAdapter = new MyAppAdapter(array, ActivityQuestions.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> parkingList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.swipecard, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = rowView.findViewById(R.id.bookText);
                ViewHolder.background = rowView.findViewById(R.id.background);
                viewHolder.cardImage = rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");

            Glide.with(ActivityQuestions.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }

}

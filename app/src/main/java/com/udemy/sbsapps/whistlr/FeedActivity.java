package com.udemy.sbsapps.whistlr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    ListView listView;

    List<Map<String, String>> whistleData;

    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        setTitle("Your Feed");

        listView = findViewById(R.id.feedListView);

        whistleData = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Whistle");
        query.whereContainedIn("username", ParseUser.getCurrentUser().getList("isFollowing"));
        query.orderByDescending("createdAt");
        query.setLimit(20);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null && objects.size() > 0) {
                    for (ParseObject whistle : objects) {
                        Map<String, String> whistleInfo = new HashMap<>();
                        whistleInfo.put("content", whistle.getString("whistle"));
                        whistleInfo.put("username", whistle.getString("username"));
                        whistleData.add(whistleInfo);
                    }

                    adapter = new SimpleAdapter(FeedActivity.this, whistleData, android.R.layout.simple_list_item_2, new String[]{"content", "username"}, new int[]{android.R.id.text1, android.R.id.text2});
                    listView.setAdapter(adapter);
                }
            }
        });
    }
}

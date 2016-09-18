package me.ilinskiy.werewolvesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class RoleActivity extends AppCompatActivity {

    public static final String PLAYERS_KEY = "players";
    public static final String INDEX_KEY = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        Intent startIntent = getIntent();
        final Bundle extras = startIntent.getExtras();
        Integer index = (Integer) extras.get(INDEX_KEY);
        if (index == null) {
            index = Integer.MAX_VALUE;
        }
        final int idx = index;
        @SuppressWarnings("unchecked") final ArrayList<Player> players = (ArrayList<Player>) extras.getSerializable(PLAYERS_KEY);
        if (players != null) {
            Player role = players.get(idx);
            ((TextView) findViewById(R.id.role)).setText(role.name);
            ((TextView) findViewById(R.id.description)).setText(role.description);
        }
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent leaveIntent;
                if (players != null && (idx + 1) < players.size()) {
                    leaveIntent = new Intent(RoleActivity.this, IntermediateActivity.class);
                    extras.putInt(INDEX_KEY, idx + 1);
                    leaveIntent.putExtras(extras);
                } else {
                    leaveIntent = new Intent(RoleActivity.this, StartActivity.class);
                }
                startActivity(leaveIntent);
                finish();
            }
        });

    }
}

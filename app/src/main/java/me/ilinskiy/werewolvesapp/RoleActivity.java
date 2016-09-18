package me.ilinskiy.werewolvesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RoleActivity extends AppCompatActivity {

    public static final String PLAYERS_KEY = "players";
    public static final String INDEX_KEY = "index";
    public static final String ROLE_SHOWN_KEY = "roleShown";
    private TextView roleName;
    private TextView roleDescription;
    private int index;
    private boolean roleShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        Intent startIntent = getIntent();
        final Bundle extras = startIntent.getExtras();
        //noinspection unchecked
        final ArrayList<Player> players = (ArrayList<Player>) extras.getSerializable(PLAYERS_KEY);
        if (players == null) {
            finish();
            return;
        }

        roleName = (TextView) findViewById(R.id.role);
        roleDescription = (TextView) findViewById(R.id.description);
        final TextView indexView = (TextView) findViewById(R.id.indexTextView);
        TextView totalPlayersView = (TextView) findViewById(R.id.totalPlayerTextView);
        index = 0;
        roleShown = false;
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(INDEX_KEY);
            roleShown = savedInstanceState.getBoolean(ROLE_SHOWN_KEY);
            if (roleShown) {
                setRoleText(players.get(index));
            }
        }
        indexView.setText(String.valueOf(index + 1));
        totalPlayersView.setText(String.valueOf(players.size()));
        final Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roleShown) {
                    next();
                } else {
                    show();
                }
                roleShown = !roleShown;
            }

            private void next() {
                if ((index + 1) < players.size()) {
                    index++;
                    setRoleToEmptyText();
                    nextButton.setText(R.string.show_role);
                    indexView.setText(String.valueOf(index + 1));
                } else {
                    finish();
                }
            }

            private void show() {
                nextButton.setText(R.string.next_button);
                setRoleText(players.get(index));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX_KEY, index);
        outState.putBoolean(ROLE_SHOWN_KEY, roleShown);
    }

    private void setRoleToEmptyText() {
        roleName.setText("");
        roleDescription.setText("");

    }

    private void setRoleText(Player role) {
        roleName.setText(role.name);
        roleDescription.setText(role.description);
    }
}

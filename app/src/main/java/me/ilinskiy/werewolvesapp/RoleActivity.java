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
        index = 0;
        roleName = (TextView) findViewById(R.id.role);
        roleDescription = (TextView) findViewById(R.id.description);
        roleShown = false;
        //noinspection unchecked
        final ArrayList<Player> players = (ArrayList<Player>) extras.getSerializable(PLAYERS_KEY);
        if (players == null) {
            finish();
            return;
        }
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

    private void setRoleToEmptyText() {
        roleName.setText("");
        roleDescription.setText("");

    }

    private void setRoleText(Player role) {
        roleName.setText(role.name);
        roleDescription.setText(role.description);
    }
}

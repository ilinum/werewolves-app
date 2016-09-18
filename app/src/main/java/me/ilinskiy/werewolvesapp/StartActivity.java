package me.ilinskiy.werewolvesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ListView roleList = (ListView) findViewById(R.id.roleList);
        TextView playerTotalText = (TextView) findViewById(R.id.playersTotalText);
        List<Role> roleLoader = RoleLoader.loadRoles(getAssets());
        final RoleItemAdapter roleItemAdapter = new RoleItemAdapter(this, roleLoader, playerTotalText);
        roleList.setAdapter(roleItemAdapter);
        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Player> players = createPlayerList(roleItemAdapter.getRoles());
                Collections.shuffle(players);
                if (players.isEmpty()) {
                    Toast.makeText(StartActivity.this, R.string.no_players_message, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(StartActivity.this, IntermediateActivity.class);
                Bundle extras = new Bundle();
                extras.putInt(RoleActivity.INDEX_KEY, 0);
                extras.putSerializable(RoleActivity.PLAYERS_KEY, players);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<Player> createPlayerList(List<Role> roles) {
        ArrayList<Player> players = new ArrayList<>();
        for (Role role : roles) {
            for (int i = 0; i < role.getPlayers(); i++) {
                players.add(role);
            }
        }
        return players;
    }
}

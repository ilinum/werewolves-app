package me.ilinskiy.werewolvesapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private static final String ROLES_KEY = "roles";
    private ArrayList<Role> roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ListView roleList = (ListView) findViewById(R.id.roleList);
        TextView playerTotalText = (TextView) findViewById(R.id.playersTotalText);
        if (savedInstanceState != null) {
            //noinspection unchecked
            roles = (ArrayList<Role>) savedInstanceState.getSerializable(ROLES_KEY);
        }
        if (roles == null) {
            roles = RoleLoader.loadRoles(getAssets());
        }
        final RoleItemAdapter roleItemAdapter = new RoleItemAdapter(this, roles, playerTotalText);
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
                Intent intent = new Intent(StartActivity.this, RoleActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable(RoleActivity.PLAYERS_KEY, players);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roleItemAdapter.reset();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ROLES_KEY, roles);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.credits_option:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.credit).
                        setCancelable(false).
                        setPositiveButton("OK", null).
                        create().
                        show();
                return true;
            case R.id.exit_option:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

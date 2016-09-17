package me.ilinskiy.werewolvesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ListView roleList = (ListView) findViewById(R.id.roleList);
        TextView playerTotalTExt = (TextView) findViewById(R.id.playersTotalText);
        List<Role> roleLoader = RoleLoader.loadRoles(getAssets());
        roleList.setAdapter(new RoleItemAdapter(this, roleLoader, playerTotalTExt));
    }
}

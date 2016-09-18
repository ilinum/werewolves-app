package me.ilinskiy.werewolvesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IntermediateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        final Intent startIntent = getIntent();
        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = startIntent.getExtras();
                Intent intent = new Intent(IntermediateActivity.this, RoleActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });
    }
}

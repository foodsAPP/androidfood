package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;

public class CheckActivity extends AppCompatActivity {
 private ImageView backmune;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        this.backmune=findViewById(R.id.cbit_backbuttom);
        backmune.setOnClickListener(v->{
            Intent intent=new Intent(CheckActivity.this,MuneActivity.class);
            startActivity(intent);
        });
    }
}

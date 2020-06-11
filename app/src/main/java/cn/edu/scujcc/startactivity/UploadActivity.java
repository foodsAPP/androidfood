package cn.edu.scujcc.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class UploadActivity extends AppCompatActivity {
   private ImageView backmu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

     backmu=findViewById(R.id.backbutton);
     backmu.setOnClickListener(v->{
         Intent intent=new Intent(UploadActivity.this,MuneActivity.class);
         startActivity(intent);
     });

    }
}

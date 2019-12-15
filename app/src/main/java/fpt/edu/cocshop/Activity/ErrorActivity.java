package fpt.edu.cocshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import fpt.edu.cocshop.R;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        String msg = getIntent().getStringExtra("error");
        TextView textView = (TextView) findViewById(R.id.txt_error);
        textView.setText(msg);
    }
}

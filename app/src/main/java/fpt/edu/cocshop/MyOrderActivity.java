package fpt.edu.cocshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.Store;

public class MyOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        TextView ttt = findViewById(R.id.ttt);
        Store store = (Store) getIntent().getSerializableExtra(Constant.STORE);
        ttt.setText(store.getLatitude()+"");
    }
}

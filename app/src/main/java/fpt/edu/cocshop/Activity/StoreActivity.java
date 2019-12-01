package fpt.edu.cocshop.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.R;

public class StoreActivity extends AppCompatActivity {

    private ImageView mImgAvatar;
    private Brand brand;
    private RatingBar mRatingBar;
    private TextView mTxtLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData(){
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        brand = (Brand) getIntent().getSerializableExtra(Constant.STORE);
        Picasso.get()
                .load(brand.getPicturePath())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(mImgAvatar, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("PICASSO", e.getMessage());
                    }
                });
        mRatingBar.setRating(brand.getRating());
        mTxtLocation.setText(brand.getLocation().get(0).getName());

    }
    private void initView(){
        mImgAvatar  = (ImageView) findViewById(R.id.img_avatar);
        mRatingBar = (RatingBar) findViewById(R.id.rb_rating_store);
        mTxtLocation = (TextView) findViewById(R.id.txt_location_store);


    }
}

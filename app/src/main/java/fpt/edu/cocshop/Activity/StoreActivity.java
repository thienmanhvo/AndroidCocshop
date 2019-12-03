package fpt.edu.cocshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Custom.CustomViewPager;
import fpt.edu.cocshop.Fragment.StoreMenuFragment;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.R;

public class StoreActivity extends AppCompatActivity {

    private ImageView mImgAvatar;
    private Brand brand;
    private RatingBar mRatingBar;
    private TextView mTxtLocation;
    private CustomViewPager mViewPager;
    private Toolbar mToolBarStore;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout tabLayout;
    private NestedScrollView mNestedScrollView;


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

    private void initData() {
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
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

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(mViewPager);

        mToolBarStore.setTitle(brand.getName());
        mToolBarStore.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 150) {
                    mToolBarStore.setVisibility(View.VISIBLE);
                } else {
                    mToolBarStore.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initView() {
        mImgAvatar = (ImageView) findViewById(R.id.img_avatar);
        mRatingBar = (RatingBar) findViewById(R.id.rb_rating_store);
        mTxtLocation = (TextView) findViewById(R.id.txt_location_store);
        mViewPager = (CustomViewPager) findViewById(R.id.vp_menu);
        mToolBarStore = (Toolbar) findViewById(R.id.tb_store_in_top);
        tabLayout = (TabLayout) findViewById(R.id.tl_menu);
        mNestedScrollView = findViewById(R.id.nsv_store_activity);
//        mNestedScrollView.setFillViewport (true);
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        private int NUM_PAGE = 3;
        private int currentPosition = -1;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_PAGE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return StoreMenuFragment.newInstance();
                case 1:
                    //return HistoryFragment.newInstance();
                default:
                    return StoreMenuFragment.newInstance();
            }
        }

//        @Override
//        public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.setPrimaryItem(container, position, object);
//            if (position != currentPosition) {
//                Fragment fragment = (Fragment) object;
//                CustomViewPager pager = (CustomViewPager) container;
//                if (fragment.getView() != null) {
//                    currentPosition = position;
//                    pager.measureCurrentView(fragment.getView());
//                }
//            }
//        }
    }
}

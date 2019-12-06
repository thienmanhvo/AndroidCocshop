package fpt.edu.cocshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NavUtils;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Custom.AlphaTextView;
import fpt.edu.cocshop.Custom.CustomViewPager;
import fpt.edu.cocshop.Fragment.StoreMenuFragment;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.R;

public class StoreActivity extends AppCompatActivity {

    private ImageView mImgAvatar;
    private Brand brand;
    private RatingBar mRatingBar;
    private AlphaTextView mTxtLocation, mTxtStoreName;
    private ViewPager mViewPager;
    private Toolbar mToolBarStore;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout tabLayout;
    private AppBarLayout mAppBarLayout;
    private LinearLayout mLlStoreDescription;
    private com.google.android.material.appbar.CollapsingToolbarLayout CollapsingToolbarLayout;
    //private NestedScrollView mNestedScrollView;


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
        mTxtStoreName.setText(brand.getName());
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(0);
        mViewPager.setPageMargin(0);
        tabLayout.setupWithViewPager(mViewPager);
        setSupportActionBar(mToolBarStore);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(brand.getName() + "asdasdasdasdasdasdasdasdasdasd");
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_white);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                CollapsingToolbarLayout.bringChildToFront(tabLayout);
                float range = (float) -appBarLayout.getTotalScrollRange();
                mLlStoreDescription.setAlpha((int) (255 * (1.0f - (float) verticalOffset / range)));
                mLlStoreDescription.getBackground().setAlpha((int) (255 * (1.0f - (float) verticalOffset / range)));
                mTxtStoreName.onSetAlpha((int) (255 * (1.0f - (float) verticalOffset / range)));
                mTxtLocation.onSetAlpha((int) (255 * (1.0f - (float) verticalOffset / range)));
                if (mLlStoreDescription.getAlpha() <= 100) {
                    getSupportActionBar().setDisplayShowTitleEnabled(true);
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before);
                    mToolBarStore.setTitleTextColor(getResources().getColor(R.color.colorBlack, getResources().newTheme()));
                    mLlStoreDescription.setVisibility(View.INVISIBLE);
                } else {
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_white);
                    mLlStoreDescription.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(android.view.MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//            case R.id.action_settings:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void initView() {
        mImgAvatar = (ImageView) findViewById(R.id.img_avatar);
        mRatingBar = (RatingBar) findViewById(R.id.rb_rating_store);
        mTxtLocation = (AlphaTextView) findViewById(R.id.txt_location_store);
        mViewPager = (ViewPager) findViewById(R.id.vp_menu);
        mToolBarStore = (Toolbar) findViewById(R.id.tb_store_top);
        tabLayout = (TabLayout) findViewById(R.id.tl_menu);
        mTxtStoreName = (AlphaTextView) findViewById(R.id.txt_name_store);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.ab_store);
        mLlStoreDescription = (LinearLayout) findViewById(R.id.ll_store_description);
        CollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        //mNestedScrollView = findViewById(R.id.nsv_store_activity);
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

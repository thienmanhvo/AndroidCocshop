package fpt.edu.cocshop.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Custom.AlphaTextView;
import fpt.edu.cocshop.Fragment.StoreLocationFragment;
import fpt.edu.cocshop.Fragment.StoreMenuFragment;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.ExceptionHandler;

public class StoreActivity extends AppCompatActivity {

    private ImageView mImgAvatar;
    private Store store;
    private RatingBar mRatingBar;
    private AlphaTextView mTxtLocation, mTxtStoreName;
    private ViewPager mViewPager;
    private Toolbar mToolBarStore;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout tabLayout;
    private AppBarLayout mAppBarLayout;
    private LinearLayout mLlStoreDescription;
    private com.google.android.material.appbar.CollapsingToolbarLayout CollapsingToolbarLayout;
    private Menu mOptionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
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
        store = (Store) getIntent().getSerializableExtra(Constant.STORE);
        Picasso.get()
                .load(store.getImagePath())
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
        mRatingBar.setRating(store.getRating());
        mTxtLocation.setText(store.getLocationName());
        mTxtStoreName.setText(store.getName());
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(0);
        mViewPager.setPageMargin(0);
        tabLayout.setupWithViewPager(mViewPager);

        setSupportActionBar(mToolBarStore);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(store.getName() + "asdasdasdasdasdasdasdasdasdasd");
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
                    updateOptionsMenu(Color.BLACK);
                    mToolBarStore.setTitleTextColor(getResources().getColor(R.color.colorBlack, getResources().newTheme()));
                    mLlStoreDescription.setVisibility(View.INVISIBLE);
                } else {
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                    updateOptionsMenu(Color.WHITE);
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_white);
                    mLlStoreDescription.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void updateOptionsMenu(int resId) {
        if (mOptionsMenu != null) {
            SearchView searchView = (SearchView) mOptionsMenu.findItem(R.id.action_search).getActionView();
            ImageView icon = searchView.findViewById(R.id.search_button);
            ImageView iconClose = (ImageView) searchView.findViewById(R.id.search_close_btn);
            icon.setColorFilter(resId);
            iconClose.setColorFilter(resId);
            //invalidateOptionsMenu();
            EditText textView = (EditText) searchView.findViewById(R.id.search_src_text);
            textView.setTextColor(resId);
            textView.setHintTextColor(resId);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        mOptionsMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
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
                    return "Delivery";
                case 1:
                    return "Review";
                case 2:
                    return "Information";
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
                case 2:
                    return StoreLocationFragment.newInstance();
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

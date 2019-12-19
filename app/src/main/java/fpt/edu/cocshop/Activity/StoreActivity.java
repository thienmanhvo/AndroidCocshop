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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

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
import fpt.edu.cocshop.Store_List.ShowEmptyViewNoTask;
import fpt.edu.cocshop.Util.DoubleHandler;
import fpt.edu.cocshop.Util.ExceptionHandler;
import fpt.edu.cocshop.Util.PriceExtention;

public class StoreActivity extends AppCompatActivity implements ShowEmptyViewNoTask {

    private ImageView mImgAvatar, mImgSaved;
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
    private ProgressBar pbLoading;
    private LinearLayout mLLStoreInfo;
    private TextView mTxtEmpty, mTxtCategory, mTxtAvgPrice, mTxtRating, mTxtNumOfRating;

    public Store getStore() {
        return store;
    }

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
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(0);
        mViewPager.setPageMargin(0);
        tabLayout.setupWithViewPager(mViewPager);

        setSupportActionBar(mToolBarStore);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(store.getName());
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
                    if (store.getImagePath() == null || store.getImagePath().matches("")) {
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before);
                        updateOptionsMenu(Color.BLACK);
                    } else {
                        updateOptionsMenu(Color.WHITE);
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before_white);
                    }
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

    private void initView() {
        pbLoading = findViewById(R.id.pb_loading);
        mTxtCategory = findViewById(R.id.txt_category);
        mTxtAvgPrice = findViewById(R.id.txt_average_price);
        mTxtRating = findViewById(R.id.txt_rating);
        mTxtNumOfRating = findViewById(R.id.txt_rating_numbers);
        mImgSaved = findViewById(R.id.img_favorite_ra);
        mLLStoreInfo = findViewById(R.id.ll_store_info);
        mTxtEmpty = findViewById(R.id.tv_empty_view);
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

    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        mTxtEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        mTxtEmpty.setVisibility(View.GONE);
    }

    public void setDataToView(Store store) {
        this.store = store;
        if (store != null) {
            mLlStoreDescription.setVisibility(View.VISIBLE);
            mLLStoreInfo.setVisibility(View.VISIBLE);
            mTxtStoreName.setText(store.getName());
            getSupportActionBar().setTitle(store.getName());
            mTxtLocation.setText(store.getLocationName().trim());
            //mTxtCategory.setText("");
            double rating = store.getRating() * 1.0 / store.getNumberOfRating();
            mTxtNumOfRating.setText("(" + store.getNumberOfRating() + ")");
            mTxtRating.setText(DoubleHandler.doubleDisplayDecimalPlaces(rating, 1));
            mTxtAvgPrice.setText(PriceExtention.doubleToPriceWithK(store.getAveragePrice()));
            mRatingBar.setRating((float) rating);
            Picasso.get()
                    .load(store.getImagePath())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.mipmap.ic_image_error_foreground)
                    .into(mImgAvatar, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {

                            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before);
                            updateOptionsMenu(Color.BLACK);
                            Log.e("PICASSO", e.getMessage()

                            );
                        }
                    });
        }
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

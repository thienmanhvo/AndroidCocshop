package fpt.edu.cocshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Fragment.OnGoIngFragment;
import fpt.edu.cocshop.Fragment.StoreLocationFragment;
import fpt.edu.cocshop.Fragment.StoreMenuFragment;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.ExceptionHandler;

public class MyOrderActivity extends AppCompatActivity {
    private TabLayout mTlMyOrder;
    private ViewPager mVpMyOrder;
    private MyOrderActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

    }
    private void initView(){
        mVpMyOrder = (ViewPager) findViewById(R.id.vp_my_order);
        mTlMyOrder = (TabLayout) findViewById(R.id.tl_my_order);
        mSectionsPagerAdapter = new MyOrderActivity.SectionsPagerAdapter(getSupportFragmentManager());
        mVpMyOrder.setAdapter(mSectionsPagerAdapter);
        mVpMyOrder.setCurrentItem(0);
        mVpMyOrder.setPageMargin(0);
        mTlMyOrder.setupWithViewPager(mVpMyOrder);
    }
    private  void initData(){

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
                    return "On Going";
                case 1:
                    return "History";
                case 2:
                    return "Cart";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return OnGoIngFragment.newInstance();

                case 1:

                case 2:

                default:
                    return OnGoIngFragment.newInstance();
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

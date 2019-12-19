package fpt.edu.cocshop.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import fpt.edu.cocshop.Activity.MyOrderActivity;
import fpt.edu.cocshop.Adapter.SectionPageMyOrderAdapter;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.ExceptionHandler;

public class MyOrderFragment extends Fragment {
    private TabLayout mTlMyOrder;
    private ViewPager mVpMyOrder;
    private SectionPageMyOrderAdapter mSectionsPagerAdapter;
    private View mView;

    public static MyOrderFragment newInstance() {
        MyOrderFragment fragment = new MyOrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.activity_my_order, container, false);
        // mCartBottomSheet = inflater.inflate(R.layout.fragment_cart_bottom_sheet, container, false).findViewById(R.id.ll_cart_bottom_sheet);
        initView();
        initData();
        return mView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        mVpMyOrder = (ViewPager) mView.findViewById(R.id.vp_my_order);
        mTlMyOrder = (TabLayout) mView.findViewById(R.id.tl_my_order);
        mSectionsPagerAdapter = new SectionPageMyOrderAdapter(getFragmentManager());
        mVpMyOrder.setAdapter(mSectionsPagerAdapter);
        mVpMyOrder.setCurrentItem(0);
        mVpMyOrder.setPageMargin(0);
        mTlMyOrder.setupWithViewPager(mVpMyOrder);
    }

    private void initData() {

    }


}


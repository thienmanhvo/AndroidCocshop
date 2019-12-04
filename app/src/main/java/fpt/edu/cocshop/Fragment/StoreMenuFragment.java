package fpt.edu.cocshop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Activity.StoreActivity;
import fpt.edu.cocshop.Adapter.FoodPicksAdapter;
import fpt.edu.cocshop.Adapter.StoreMenuItemAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Location;
import fpt.edu.cocshop.Model.Menu;
import fpt.edu.cocshop.Model.MenuItem;
import fpt.edu.cocshop.R;


public class StoreMenuFragment extends Fragment {
    private RecyclerView mRcvMenu;
    private StoreMenuItemAdapter mStoreMenuItemAdapter;
    private List<Object> mMenuList;
    private View mView;


    public StoreMenuFragment() {
        // Required empty public constructor
    }

    public static StoreMenuFragment newInstance() {
        StoreMenuFragment fragment = new StoreMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_store_menu, container, false);
        initView();
        initData();
        return mView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        mRcvMenu = mView.findViewById(R.id.rcv_store_menu_item);
        mRcvMenu.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRcvMenu.setLayoutManager(manager);
    }

    private void initData() {
        mMenuList = new ArrayList<>();
        for (int j = 0; j <= 4; j++) {
            Menu header = new Menu();
            header.setName("header" + j);
            mMenuList.add(header);
            for (int i = 0; i <= 3; i++) {
                MenuItem item = new MenuItem();
                item.setName("Đùi gà nướng" + " " + i);
                item.setImagePath("https://znews-photo.zadn.vn/w660/Uploaded/Ohunoaa/2016_12_31/d6.jpg");
                item.setPrice(20500);
                mMenuList.add(item);
            }
        }
        updateUIRcvMenu(mMenuList);


    }

    private void updateUIRcvMenu(List<Object> mMenuList) {
        if (mStoreMenuItemAdapter == null) {
            mStoreMenuItemAdapter = new StoreMenuItemAdapter(getContext(), mMenuList);
            mRcvMenu.setAdapter(mStoreMenuItemAdapter);
            mStoreMenuItemAdapter.setmOnFoodPicksClickListener(new FoodPicksAdapter.OnFoodPicksClickListener() {
                @Override
                public void onClick(Brand brand) {
                    Intent intent = new Intent(getContext(), StoreActivity.class);
                    intent.putExtra(Constant.STORE, brand);
                    startActivity(intent);
                }
            });
        } else {
            mStoreMenuItemAdapter.notifyDataSetChanged();
        }
    }
}

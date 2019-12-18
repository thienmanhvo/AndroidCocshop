package fpt.edu.cocshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fpt.edu.cocshop.Adapter.StoreListAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListPresenter;
import fpt.edu.cocshop.Home_Store_List.ShowEmptyViewWithTask;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Store_List.ShowEmptyViewNoTask;
import fpt.edu.cocshop.Store_List.StoreListContract;
import fpt.edu.cocshop.Store_List.StoreListPresenter;
import fpt.edu.cocshop.Util.CurrentLocation;
import fpt.edu.cocshop.Util.GpsUtils;

public class StoreListActivity extends AppCompatActivity implements StoreListContract.View, ShowEmptyViewNoTask {

    private static final String TAG = "StoreListActivity";
    private TextView mTxtToolBarTitle, mTxtEmptyView;
    private RecyclerView rcvStoreList;
    private StoreListAdapter mStoreListAdapter;
    private List<Store> mStoreList;
    private RelativeLayout mRLStoreList;
    private ProgressBar pbLoading;
    private StoreListPresenter mStoreListPresenter;
    private Toolbar mToolBarStore;
    private String brandId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);


        initView();
        initData();
    }

    private void initView() {
        mTxtToolBarTitle = findViewById(R.id.txt_store_list_brand_title);
        rcvStoreList = findViewById(R.id.rv_store_list);
        LinearLayoutManager llStoreList = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvStoreList.setLayoutManager(llStoreList);
        mRLStoreList = findViewById(R.id.rl_store_list);
        mTxtEmptyView = findViewById(R.id.tv_empty_view);
        pbLoading = findViewById(R.id.pb_loading);
        mToolBarStore = (Toolbar) findViewById(R.id.tb_store_list);
        setSupportActionBar(mToolBarStore);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before);
    }

    private void initData() {
        Brand brand = (Brand) getIntent().getSerializableExtra(Constant.STORE_LIST);
        mTxtToolBarTitle.setText(brand.getName());
        brandId = brand.getId();
        mStoreList = new ArrayList<>();
        updateUIRcvFoodPicks(mStoreList);
        mStoreListPresenter = new StoreListPresenter(this);
        //mStoreListPresenter.requestDataFromServer(10, 1, 10.806941, 106.788891, brand.getId());
        mStoreListPresenter.requestDataFromServer(10, 1,  CurrentLocation.latitude, CurrentLocation.longitude, brandId);
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
    private void updateUIRcvFoodPicks(List<Store> mStoreList) {
        if (mStoreListAdapter == null) {
            mStoreListAdapter = new StoreListAdapter(this, mStoreList);
            rcvStoreList.setAdapter(mStoreListAdapter);
            mStoreListAdapter.setmOnStoreListClickListener(new StoreListAdapter.OnItemOrderClickListener() {
                @Override
                public void onClickStoreItem(Store store) {
                    Intent intent = new Intent(StoreListActivity.this, StoreActivity.class);
                    intent.putExtra(Constant.STORE, store);
                    startActivity(intent);
                }
            });
        } else {
            mStoreListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setStoreToRecyclerView(List<Store> StoreArrayList) {
        if (StoreArrayList == null || StoreArrayList.size() == 0) {
            showEmptyView();
        } else {
            hideEmptyView();
        }
        mStoreList.addAll(StoreArrayList);
        mStoreListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(String throwable) {
        Log.e(TAG, throwable);
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyView() {
        rcvStoreList.setVisibility(View.GONE);
        mTxtEmptyView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideEmptyView() {
        rcvStoreList.setVisibility(View.VISIBLE);
        mTxtEmptyView.setVisibility(View.GONE);
    }



}

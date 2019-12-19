package fpt.edu.cocshop.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Activity.StoreActivity;
import fpt.edu.cocshop.Activity.StoreListActivity;
import fpt.edu.cocshop.Adapter.FoodPicksAdapter;
import fpt.edu.cocshop.Adapter.PopularBrandAdapter;
import fpt.edu.cocshop.Adapter.TopStoreAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListContract;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListPresenter;
import fpt.edu.cocshop.Home_Store_List.ShowEmptyViewWithTask;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.CurrentLocation;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeStoreListContract.View, ShowEmptyViewWithTask {
    private static final String TAG = "HomeFragment";
    private RecyclerView mRcvFoodPicks, mRcvTopBrand, mRcvTopStore;
    private FoodPicksAdapter mFoodPicksAdapter;
    private PopularBrandAdapter mPopularBrandAdapter;
    private TopStoreAdapter mTopStoreAdapter;
    private List<Store> mStoreList;
    private List<Brand> mPoppularBrandList;
    private List<Store> mTopStoreList;
    private View mView;
    private ProgressBar pbLoading;
    private TextView txtEmptyView, txtPoppularBrandEmptyView, txtTopStoreEmptyView;
    private HomeStoreListPresenter storeListPresenter;
    private LinearLayout mLLFoodsPick, mLLPopularBrand, mLLTopStore;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initData();
        return mView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        pbLoading = mView.findViewById(R.id.pb_loading);

        mRcvFoodPicks = mView.findViewById(R.id.rcv_food_picks);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRcvFoodPicks.setLayoutManager(manager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRcvTopBrand = mView.findViewById(R.id.rcv_popular_brands);
        mRcvTopBrand.setLayoutManager(layoutManager);

        LinearLayoutManager managerChef = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        mRcvTopStore = mView.findViewById(R.id.rcv_top_store);
        mRcvTopStore.setLayoutManager(managerChef);

        txtEmptyView = mView.findViewById(R.id.tv_empty_view);
        txtPoppularBrandEmptyView = mView.findViewById(R.id.tv_poppular_brand_empty_view);
        txtTopStoreEmptyView = mView.findViewById(R.id.tv_top_store_empty_view);

        mLLFoodsPick = mView.findViewById(R.id.ll_food_pick);
        mLLPopularBrand = mView.findViewById(R.id.ll_popular_brand);
        mLLTopStore = mView.findViewById(R.id.ll_top_store);


    }

    private void initData() {
        mStoreList = new ArrayList<>();
        mPoppularBrandList = new ArrayList<>();
        mTopStoreList = new ArrayList<>();
        updateUIRcvFoodPicks(mStoreList);
        updateUIRcvPoppularBrand(mPoppularBrandList);
        updateUIRcvTopStore(mTopStoreList);
        storeListPresenter = new HomeStoreListPresenter(this);
        storeListPresenter.requestDataFromServer(10, 1, CurrentLocation.latitude, CurrentLocation.longitude, 10);
    }

    private void updateUIRcvFoodPicks(List<Store> mBrandList) {
        if (mFoodPicksAdapter == null) {
            mFoodPicksAdapter = new FoodPicksAdapter(getContext(), mBrandList);
            mRcvFoodPicks.setAdapter(mFoodPicksAdapter);
            mFoodPicksAdapter.setmOnFoodPicksClickListener(new FoodPicksAdapter.OnFoodPicksClickListener() {
                @Override
                public void onClick(Store store) {
                    Intent intent = null;
                    if (store.totalStore == 1) {
                        intent = new Intent(getContext(), StoreActivity.class);
                        intent.putExtra(Constant.STORE, store);

                    } else {
                        intent = new Intent(getContext(), StoreListActivity.class);
                        intent.putExtra(Constant.STORE_LIST, new Brand(store.brandId, store.name));
                    }
                    startActivity(intent);
                }
            });
        } else {
            mFoodPicksAdapter.notifyDataSetChanged();
        }
    }

    private void updateUIRcvTopStore(List<Store> mStoreList) {
        if (mTopStoreAdapter == null) {
            mTopStoreAdapter = new TopStoreAdapter(getContext(), mStoreList);
            mRcvTopStore.setAdapter(mTopStoreAdapter);
            mTopStoreAdapter.setmOnTopStoreClickListener(new TopStoreAdapter.OnItemOrderClickListener() {
                @Override
                public void onClickStoreItem(Store store) {

                    Intent intent = new Intent(getContext(), StoreActivity.class);
                    intent.putExtra(Constant.STORE, store);
                    startActivity(intent);

                }
            });
        } else {
            mFoodPicksAdapter.notifyDataSetChanged();
        }
    }

    private void updateUIRcvPoppularBrand(List<Brand> mBrandList) {
        if (mPopularBrandAdapter == null) {
            mPopularBrandAdapter = new PopularBrandAdapter(getContext(), mBrandList);
            mRcvTopBrand.setAdapter(mPopularBrandAdapter);
            mPopularBrandAdapter.setmOnPopularBrandClickListener(new PopularBrandAdapter.OnPopularBrandClickListener() {
                @Override
                public void onClick(Brand Brand) {
                    Intent intent = new Intent(getContext(), StoreListActivity.class);
                    intent.putExtra(Constant.STORE_LIST, Brand);
                    startActivity(intent);
                }
            });
        } else {
            mFoodPicksAdapter.notifyDataSetChanged();
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
    public void setNearestStoreToRecyclerView(List<Store> StoreArrayList) {
        if (StoreArrayList == null || StoreArrayList.size() == 0) {
            showEmptyView(Constant.TASK_NEAREST_STORE);
        } else {
            hideEmptyView(Constant.TASK_NEAREST_STORE);
        }
        mStoreList.addAll(StoreArrayList);
        mFoodPicksAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPopularBrandToRecyclerView(List<Brand> BrandArrayList) {
        if (BrandArrayList == null || BrandArrayList.size() == 0) {
            showEmptyView(Constant.TASK_POPULAR_BRAND);
        } else {
            hideEmptyView(Constant.TASK_POPULAR_BRAND);
        }
        mPoppularBrandList.addAll(BrandArrayList);
        mFoodPicksAdapter.notifyDataSetChanged();
    }

    @Override
    public void setTopStoreToRecyclerView(List<Store> StoreArrayList) {
        if (StoreArrayList == null || StoreArrayList.size() == 0) {
            showEmptyView(Constant.TASK_TOP_STORE);
        } else {
            hideEmptyView(Constant.TASK_TOP_STORE);
        }
        mTopStoreList.addAll(StoreArrayList);
        mTopStoreAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResponseFailure(String throwable) {
        Log.e(TAG, throwable);
        Toast.makeText(getContext(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyView(int taskId) {
        switch (taskId) {
            case Constant.TASK_NEAREST_STORE:
                mLLFoodsPick.setVisibility(View.VISIBLE);
                mRcvFoodPicks.setVisibility(View.GONE);
                txtEmptyView.setVisibility(View.VISIBLE);

                break;
            case Constant.TASK_POPULAR_BRAND:
                mLLPopularBrand.setVisibility(View.VISIBLE);
                mRcvTopBrand.setVisibility(View.GONE);
                txtPoppularBrandEmptyView.setVisibility(View.VISIBLE);
                break;

            case Constant.TASK_TOP_STORE:
                mLLTopStore.setVisibility(View.VISIBLE);
                mRcvTopStore.setVisibility(View.GONE);
                txtTopStoreEmptyView.setVisibility(View.VISIBLE);
                break;

        }

    }

    @Override
    public void hideEmptyView(int taskId) {
        switch (taskId) {
            case Constant.TASK_NEAREST_STORE:
                mLLFoodsPick.setVisibility(View.VISIBLE);
                mRcvFoodPicks.setVisibility(View.VISIBLE);
                txtEmptyView.setVisibility(View.GONE);
                break;
            case Constant.TASK_POPULAR_BRAND:

                mLLPopularBrand.setVisibility(View.VISIBLE);
                mRcvTopBrand.setVisibility(View.VISIBLE);
                txtPoppularBrandEmptyView.setVisibility(View.GONE);
                break;
            case Constant.TASK_TOP_STORE:
                mLLTopStore.setVisibility(View.VISIBLE);
                mRcvTopStore.setVisibility(View.VISIBLE);
                txtTopStoreEmptyView.setVisibility(View.GONE);
                break;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        storeListPresenter.onDestroy();
    }
}

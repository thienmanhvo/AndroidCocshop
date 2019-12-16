package fpt.edu.cocshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Adapter.FoodPicksAdapter;
import fpt.edu.cocshop.Adapter.StoreListAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;

public class StoreListActivity extends AppCompatActivity {

    private TextView mTxtToolBarTitle;
    private RecyclerView rcvStoreList;
    private StoreListAdapter mStoreListAdapter;
    private List<Store> mStoreList;

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
    }

    private void initData() {
        String title = (String) getIntent().getSerializableExtra(Constant.STORE_LIST_TITLE);
        mTxtToolBarTitle.setText(title);
        mStoreList = new ArrayList<>();
        updateUIRcvFoodPicks(mStoreList);

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
}

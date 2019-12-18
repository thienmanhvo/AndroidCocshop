package fpt.edu.cocshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Adapter.ItemOrderAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.Model.ItemOrder;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.ExceptionHandler;
import fpt.edu.cocshop.Util.PriceExtention;

public class CheckOutActivity extends AppCompatActivity {

    private RecyclerView mRvDishItem;
    private BottomSheetBehavior mSheetBehavior;
    private LinearLayout mCheckoutBottomSheet;
    private CartObj cartObj;
    private List<String> listIdOrderItem;
    private ItemOrderAdapter mItemOrderAdapter;
    private TextView mTxtTotalPrice, mTxtTotalPricePayment, mTxtDiscount, mTxtDeliveryFee, mTxtTotalPriceInBottom, mTxtAddMore;
    private Toolbar mTbCheckOut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        initView();
        initData();
    }

    private void initData() {
        cartObj = (CartObj) getIntent().getSerializableExtra(Constant.CART_OBJ);
        listIdOrderItem = new ArrayList<>(cartObj.getCart().keySet());
        initCartView(cartObj);
        updateUIRcvMenu(cartObj);
    }

    private void initView() {
        mTbCheckOut = findViewById(R.id.tb_checkout);
        mTxtAddMore = findViewById(R.id.txt_add_more);
        setSupportActionBar(mTbCheckOut);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigate_before);
        mTxtTotalPrice = findViewById(R.id.txt_checkout_total_price);
        mTxtTotalPricePayment = findViewById(R.id.txt_checkout_total_price_payment);
        mTxtDiscount = findViewById(R.id.txt_checkout_discounts);
        mTxtDeliveryFee = findViewById(R.id.txt_checkout_delivery_fee);
        mTxtTotalPriceInBottom = findViewById(R.id.txt_checkout_button_total_price);
        mCheckoutBottomSheet = findViewById(R.id.ll_checkout_bottom_sheet);
        mSheetBehavior = BottomSheetBehavior.from(mCheckoutBottomSheet);
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mRvDishItem = findViewById(R.id.rv_checkout_list_item);
        mRvDishItem.setHasFixedSize(true);
//        mRvDishItem.addItemDecoration(new CustomDecoration(ContextCompat.getDrawable(this, R.drawable.custom_horizontal_line)));
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRvDishItem.setLayoutManager(manager);
        mTxtAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnCart();
            }
        });
    }

    private void updateUIRcvMenu(final CartObj cartObj) {
//        try {
        if (mItemOrderAdapter == null) {
            mItemOrderAdapter = new ItemOrderAdapter(this, cartObj);
            mRvDishItem.setAdapter(mItemOrderAdapter);
            //mRvDishItem.setLayoutManager(new LinearLayoutManager();
            mItemOrderAdapter.setmOnFoodPicksClickListener(new ItemOrderAdapter.OnItemOrderClickListener() {
                private void init(ItemOrderAdapter.ViewHolderItem item, int sign, int position) {
                    ItemOrder dishItem = cartObj.getCart().get(listIdOrderItem.get(position));
                    int defaultSize = cartObj.getCart().size();
                    if (sign == 1) {
                        cartObj.addToCart(dishItem, sign);
                        initCartView(cartObj);
                    }
                    if (sign == -1) {
                        cartObj.addToCart(dishItem, sign);
                        if (cartObj.getTotalQuantity() == 0) {
                            returnCart();
                        }
                        if (defaultSize != cartObj.getCart().size()) {
                            listIdOrderItem = new ArrayList<>(cartObj.getCart().keySet());
                            mItemOrderAdapter.setListId(listIdOrderItem);
                        }
                        initCartView(cartObj);
                    }

                }

                @Override
                public void onClickAddItem(ItemOrderAdapter.ViewHolderItem item, int position) {
                    init(item, 1, position);
                    mItemOrderAdapter.notifyDataSetChanged();
                }

                @Override
                public void onClickMinusItem(ItemOrderAdapter.ViewHolderItem item, int position) {
                    init(item, -1, position);
                    mItemOrderAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                returnCart();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking()
                && !event.isCanceled()) {
            returnCart();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void returnCart() {
        Intent data = new Intent();
        data.putExtra(Constant.CART_OBJ, cartObj);
        setResult(RESULT_OK, data);
        CheckOutActivity.this.finish();
    }

    private void initCartView(CartObj cartObj) {
        long totalPrice = cartObj.getTotalPrice();
        mTxtTotalPrice.setText(PriceExtention.longToPrice(cartObj.getTotalPriceOld(), Constant.NUMBER_COMMA));
        mTxtDiscount.setText(PriceExtention.longToPrice(-cartObj.getTotalPrice() + cartObj.getTotalPriceOld(), Constant.NUMBER_COMMA));
        //mTxtDeliveryFee.setText(PriceExtention.longToPrice(cartObj.getTotalPriceOld(), Constant.NUMBER_COMMA));
        mTxtTotalPricePayment.setText(PriceExtention.longToPrice(totalPrice, Constant.NUMBER_COMMA));
        mTxtTotalPriceInBottom.setText(PriceExtention.longToPrice(totalPrice, Constant.NUMBER_COMMA));
    }
}
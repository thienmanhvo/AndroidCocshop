package fpt.edu.cocshop.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.Model.ItemOrder;
import fpt.edu.cocshop.Model.MenuDish;
import fpt.edu.cocshop.Model.Product;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.DoubleHandler;
import fpt.edu.cocshop.Util.PriceExtention;

public class StoreMenuItemAdapter extends ExpandableRecyclerAdapter<MenuDish, Product, StoreMenuItemAdapter.ViewHolderHeader, StoreMenuItemAdapter.ViewHolderItem> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private LayoutInflater mInflater;

    public interface OnStoreMenuListener {
        void onClickAddItem(ViewHolderItem item, int parentPosition, int childPosition);

        void onClickMinusItem(ViewHolderItem item, int parentPosition, int childPosition);
    }

    private Context mContext;
    private List<MenuDish> mListItem;
    private Double mDiscount;
    private OnStoreMenuListener mOnStoreMenuClickListener;
    private CartObj cartObj;


    public void setmDiscount(Double mDiscount) {
        this.mDiscount = mDiscount;
    }

    public void setmOnStoreMenuClickListener(OnStoreMenuListener mOnStoreMenuClickListener) {
        this.mOnStoreMenuClickListener = mOnStoreMenuClickListener;
    }

    public StoreMenuItemAdapter(Context mContext, List<MenuDish> mListItem, CartObj cartObj, Double mDiscount) {
        super(mListItem);
        this.mContext = mContext;
        this.mListItem = mListItem;
        mInflater = LayoutInflater.from(mContext);
        this.cartObj = cartObj;
        this.mDiscount = mDiscount;
    }

    public void setCartObj(CartObj cartObj) {
        this.cartObj = cartObj;
    }

    @NonNull
    @Override
    public ViewHolderHeader onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View v = mInflater.inflate(R.layout.item_menu_header, parentViewGroup, false);
        return new ViewHolderHeader(v);
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View v = mInflater.inflate(R.layout.item_menu_list, childViewGroup, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindParentViewHolder(@NonNull ViewHolderHeader parentViewHolder, int parentPosition, @NonNull MenuDish parent) {
        parentViewHolder.bind(parent, parentPosition);
    }

    @Override
    public void onBindChildViewHolder(@NonNull ViewHolderItem childViewHolder, int parentPosition, int childPosition, @NonNull Product child) {
        childViewHolder.bind(child, parentPosition, childPosition);
    }

    protected class ViewHolderHeader extends ParentViewHolder {
        private ImageView mImgToggle, mImgShowItem;
        private TextView mTxtName;
        private RelativeLayout mRlHeader;

        public ViewHolderHeader(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_menu_name);
            mImgToggle = itemView.findViewById(R.id.btn_store_toggle_menu);
            mImgShowItem = itemView.findViewById(R.id.btn_store_show_item);
            mRlHeader = itemView.findViewById(R.id.rl_store_menu);
        }

        public void bind(MenuDish menuDish, final int position) {
            mTxtName.setText(menuDish.getName());
            mRlHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isExpanded()) {
                        mImgToggle.setImageResource(R.drawable.ic_navigate_next);
                        collapseView();
                    } else {
                        mImgToggle.setImageResource(R.drawable.ic_keyboard_arrow_up);
                        expandView();
                    }
                    //mOnStoreMenuClickListener.onClickToggleMenuItem(position);
                }
            });
        }

        @Override
        public boolean shouldItemViewClickToggleExpansion() {
            return false;
        }
    }

    public class ViewHolderItem extends ChildViewHolder {
        private ImageView mImgDescription, mBtnAddItem, mBtnMinusItem;
        private TextView mTxtName;
        private TextView mTxtPriceOld, mTxtPrice, mTxtNumOfItem;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            mImgDescription = itemView.findViewById(R.id.img_menu_item_avatar);
            mTxtName = itemView.findViewById(R.id.txt_menu_item_name);
            mTxtPrice = itemView.findViewById(R.id.txt_menu_item_price);
            mTxtPriceOld = itemView.findViewById(R.id.txt_menu_item_price_old);
            mTxtPriceOld.setPaintFlags(mTxtPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mBtnAddItem = itemView.findViewById(R.id.btn_menu_item_add);
            mTxtNumOfItem = itemView.findViewById(R.id.txt_store_menu_number_item);
            mBtnMinusItem = itemView.findViewById(R.id.btn_menu_item_minus);
        }

        public void bind(Product item, final int parentPosition, final int childPosition) {
            mTxtName.setText(item.getProductName());
            long price = item.getPrice();
            if (mDiscount != null) {
                mTxtPriceOld.setText(PriceExtention.longToPrice(price, Constant.NUMBER_COMMA));
                mTxtPrice.setText(PriceExtention.doubleToPrice(Double.parseDouble(DoubleHandler.doubleDisplayDecimalPlaces(price * mDiscount, 2))));
            } else {
                mTxtPriceOld.setText("0");
                mTxtPriceOld.setVisibility(View.INVISIBLE);
                mTxtPrice.setText(PriceExtention.longToPrice(price, Constant.NUMBER_COMMA));
            }


            mBtnAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnStoreMenuClickListener.onClickAddItem(ViewHolderItem.this, parentPosition, childPosition);
                }
            });
            mBtnMinusItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnStoreMenuClickListener.onClickMinusItem(ViewHolderItem.this, parentPosition, childPosition);
                }
            });
            ItemOrder itemInCart = cartObj.getCart().get(item.getId());
            if (itemInCart != null) {
                if (itemInCart.getQuantityInCart() == 0) {
                    mTxtNumOfItem.setVisibility(View.GONE);
                    mBtnMinusItem.setVisibility(View.GONE);
                } else {
                    mTxtNumOfItem.setVisibility(View.VISIBLE);
                    mBtnMinusItem.setVisibility(View.VISIBLE);
                }
                mTxtNumOfItem.setText(itemInCart.getQuantityInCart() + "");
            } else {
                mTxtNumOfItem.setVisibility(View.GONE);
                mBtnMinusItem.setVisibility(View.GONE);
            }

            Picasso.get()
                    .load(item.getImagePath())
                    .error(R.mipmap.ic_image_error_foreground)
                    .placeholder(R.mipmap.ic_image_error_foreground)
                    .fit()
                    .into(mImgDescription, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e("PICASSO", e.getMessage());
                        }
                    });
        }

        public void setmTxtNumOfItem(TextView mTxtNumOfItem) {
            this.mTxtNumOfItem = mTxtNumOfItem;
        }

        public ImageView getmBtnMinusItem() {
            return mBtnMinusItem;
        }

        public ImageView getmImgDescription() {
            return mImgDescription;
        }

        public ImageView getmBtnAddItem() {
            return mBtnAddItem;
        }

        public TextView getmTxtName() {
            return mTxtName;
        }

        public TextView getmTxtPriceOld() {
            return mTxtPriceOld;
        }

        public TextView getmTxtPrice() {
            return mTxtPrice;
        }

        public TextView getmTxtNumOfItem() {
            return mTxtNumOfItem;
        }
    }
}

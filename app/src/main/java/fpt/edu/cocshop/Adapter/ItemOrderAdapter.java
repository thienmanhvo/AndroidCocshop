package fpt.edu.cocshop.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Model.CartObj;
import fpt.edu.cocshop.Model.ItemOrder;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.PriceExtention;

public class ItemOrderAdapter extends RecyclerView.Adapter<ItemOrderAdapter.ViewHolderItem> {


    private CartObj cartObj;
    private List<String> listId;
    private Context mContext;
    private ItemOrderAdapter.OnItemOrderClickListener mOnFoodPicksClickListener;

    public void setmOnFoodPicksClickListener(OnItemOrderClickListener mOnFoodPicksClickListener) {
        this.mOnFoodPicksClickListener = mOnFoodPicksClickListener;
    }

    public void setListId(List<String> listId) {
        this.listId = listId;
    }

    public interface OnItemOrderClickListener {
        void onClickAddItem(ViewHolderItem item, int position);

        void onClickMinusItem(ViewHolderItem item, int position);
    }

    public ItemOrderAdapter(Context mContext, CartObj cartObj) {
        this.mContext = mContext;
        this.cartObj = cartObj;
        listId = new ArrayList<>(cartObj.getCart().keySet());
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_checkout, parent, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem holder, int position) {
        holder.bind(cartObj.getCart().get(listId.get(position)), position);
    }


    @Override
    public int getItemCount() {
        return cartObj.getCart().size();
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

        public void bind(ItemOrder item, final int position) {
            mTxtName.setText(item.getName());
            mTxtPrice.setText(PriceExtention.longToPrice(item.getPrice(), Constant.NUMBER_COMMA));
            mTxtPriceOld.setText(PriceExtention.longToPrice(item.getPriceOld(), Constant.NUMBER_COMMA));
            mBtnAddItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnFoodPicksClickListener.onClickAddItem(ViewHolderItem.this, position);
                }
            });
            mBtnMinusItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnFoodPicksClickListener.onClickMinusItem(ViewHolderItem.this, position);
                }
            });
            if (item.getQuantityInCart() == 0) {
                mTxtNumOfItem.setVisibility(View.GONE);
                mBtnMinusItem.setVisibility(View.GONE);
            } else {
                mTxtNumOfItem.setVisibility(View.VISIBLE);
                mBtnMinusItem.setVisibility(View.VISIBLE);
            }
            mTxtNumOfItem.setText(item.getQuantityInCart() + "");
            Picasso.get()
                    .load(item.getImagePath())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
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

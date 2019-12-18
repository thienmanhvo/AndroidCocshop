package fpt.edu.cocshop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.DoubleHandler;
import fpt.edu.cocshop.Util.PriceExtention;

public class TopStoreAdapter extends RecyclerView.Adapter<TopStoreAdapter.ViewHolderItem> {

    private List<Store> mStoreList;
    private Context mContext;
    private TopStoreAdapter.OnItemOrderClickListener mOnTopStoreClickListener;

    public void setmOnTopStoreClickListener(TopStoreAdapter.OnItemOrderClickListener mOnTopStoreClickListener) {
        this.mOnTopStoreClickListener = mOnTopStoreClickListener;
    }

    public interface OnItemOrderClickListener {
        void onClickStoreItem(Store store);

    }

    public TopStoreAdapter(Context mContext, List<Store> mStoreList) {
        this.mContext = mContext;
        this.mStoreList = mStoreList;
    }

    @NonNull
    @Override
    public TopStoreAdapter.ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_store_list, parent, false);
        return new TopStoreAdapter.ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoreAdapter.ViewHolderItem holder, int position) {
        holder.bind(mStoreList.get(position));
        holder.mLlStoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnTopStoreClickListener.onClickStoreItem(mStoreList.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mStoreList.size();
    }

    public class ViewHolderItem extends ChildViewHolder {
        private ImageView mImgDescription;
        private TextView mTxtName;
        private TextView mTxtLocationName, mTxtAveragePrice, mTxtDistance, mTxtRating, mTxtPromo;
        private RatingBar mRbStoreRating;
        private LinearLayout mLlStoreList;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            mImgDescription = itemView.findViewById(R.id.img_store_list_avatar);
            mTxtName = itemView.findViewById(R.id.txt_store_list_name);
            mTxtAveragePrice = itemView.findViewById(R.id.txt_store_list_average_price);
            mTxtLocationName = itemView.findViewById(R.id.txt_store_list_location);
            mTxtDistance = itemView.findViewById(R.id.txt_store_list_distance);
            mRbStoreRating = itemView.findViewById(R.id.rb_store_list_rating);
            mLlStoreList = itemView.findViewById(R.id.ll_store_list);
            mTxtRating = itemView.findViewById(R.id.txt_store_list_rating);
            mTxtPromo = itemView.findViewById(R.id.txt_promo);
        }

        public void bind(Store item) {
            mTxtName.setText(item.getName() + " - " + item.getLocationName().split(",")[0].replace(item.getLocationName().split(" ")[0], ""));
            mTxtAveragePrice.setText(PriceExtention.doubleToPriceWithK(item.getAveragePrice()));
            mTxtLocationName.setText(item.getLocationName());
            mTxtDistance.setText(DoubleHandler.doubleDisplayDecimalPlaces(item.getDistance(), 2) + " km");
            mRbStoreRating.setRating((float) ((item.getRating() * 1.0) / item.getNumberOfRating()));
            mTxtRating.setText(DoubleHandler.doubleDisplayDecimalPlaces(item.getRating() * 1.0 / item.getNumberOfRating(), 2));
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
            if (item.getPromotions() != null && item.getPromotions().size() != 0) {
                mTxtPromo.setText(item.getPromotions().get(0).getName());
                mTxtPromo.setVisibility(View.VISIBLE);
            }
        }

    }
}

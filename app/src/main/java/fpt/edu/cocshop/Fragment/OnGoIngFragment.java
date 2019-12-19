package fpt.edu.cocshop.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import de.hdodenhof.circleimageview.CircleImageView;
import fpt.edu.cocshop.Activity.StoreActivity;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListPresenter;
import fpt.edu.cocshop.Model.Order;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.OnGoing_Order.OnGoingOrderContract;
import fpt.edu.cocshop.OnGoing_Order.OnGoingOrderPresenter;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.CurrentLocation;
import fpt.edu.cocshop.Util.MyAccount;

public class OnGoIngFragment extends Fragment implements OnMapReadyCallback, OnGoingOrderContract.View {
    private GoogleMap mMap;
    private View mView;
    private MapView mMapView;
    private static final String TAG = "OnGoIngFragment";
    private ProgressBar pbLoading;
    private OnGoingOrderPresenter mOnGoingOrderPresenter;
    private CircleImageView mMarkerImageView;
    private View mCustomMarkerView;

    public static OnGoIngFragment newInstance() {
        OnGoIngFragment fragment = new OnGoIngFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMapView = mView.findViewById(R.id.map_view_on_going);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.a
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_on_going, container, false);
        mCustomMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_view_marker, null);
        mMarkerImageView = (CircleImageView) mCustomMarkerView.findViewById(R.id.profile_image);
        pbLoading = mView.findViewById(R.id.pb_loading);
        return mView;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady() called with");
        mMap = googleMap;
        mOnGoingOrderPresenter = new OnGoingOrderPresenter(this);
        mOnGoingOrderPresenter.requestDataFromServer();
    }

    private void addCustomMarker(@DrawableRes int pointerId, final LatLng latLng, String url) {
        Log.d(TAG, "addCustomMarker()");
        if (mMap == null) {
            return;
        }

        // adding a marker on map with image from  drawable
       /* mMap.addMarker(new MarkerOptions()
                .position(mDummyLatLng)
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, R.drawable.avatar))));*/

        // adding a marker with image from URL using glide image loading library
        mCustomMarkerView.setBackgroundResource(pointerId);
        Picasso.get()
                .load(url)
                .resize(30, 30)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.drawable.ic_launcher_background)
                //.resize(100,100)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, bitmap))));
                        // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));

                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.e("PICASSO", e.getMessage());
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    private Bitmap getMarkerBitmapFromView(View view, Bitmap bitmap) {

        mMarkerImageView.setImageBitmap(bitmap);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = view.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
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
    public void onResponseSuccess(Order order) {
        if (order != null) {
            LatLng sydney = new LatLng(order.deliveryToLatitude, order.deliveryToLongitude);

            //Store store = ((StoreActivity) getActivity()).getStore();
            LatLng mDummyLatLng = new LatLng(order.getStore().getLatitude(), order.getStore().getLongitude());

            MapsInitializer.initialize(getActivity());

            addCustomMarker(R.drawable.custom_mark_blue, mDummyLatLng, "https://previews.123rf.com/images/tribalium123/tribalium1231209/tribalium123120900263/15414369-crossed-fork-and-spoon-food-icon-food-symbol.jpg");
            addCustomMarker(R.drawable.custom_mark_red, sydney, MyAccount.url);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(sydney)      // Sets the center of the map to Mountain View
                    .zoom(17)                   // Sets the zoom
                    //.bearing(90)                // Sets the orientation of the camera to east
                    // .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onResponseFailure(String throwable) {
        Log.e(TAG, throwable);
        Toast.makeText(getContext(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }
}

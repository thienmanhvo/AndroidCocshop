package fpt.edu.cocshop.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;

import fpt.edu.cocshop.Model.UserDetail;
import fpt.edu.cocshop.R;


public class UserFragment extends Fragment  {
    TextView nameTV;
    TextView mailTV;
    TextView idTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        nameTV  = view.findViewById(R.id.name);
        mailTV = view.findViewById(R.id.mail);
        idTV  = view.findViewById(R.id.id);
        Bundle bundle = getArguments();
        System.out.println("bundlesout"+bundle.getString("name"));
        if (bundle != null){

            nameTV.setText(bundle.getString("name"));
            mailTV.setText(bundle.getString("mail"));
            idTV.setText(bundle.getString("id"));
        }

        return view;
    }



}

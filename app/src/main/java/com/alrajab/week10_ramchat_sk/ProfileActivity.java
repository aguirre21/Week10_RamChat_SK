package com.alrajab.week10_ramchat_sk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((TextView) findViewById(R.id.nameTextView)).setText(getFirebaseUser().getCurrentUser().getDisplayName());

        findViewById(R.id.sign_Out).setOnClickListener(this::signOut);

        Glide.with(this)
                .load( getFirebaseUser().getCurrentUser().getPhotoUrl() )
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(((ImageView)findViewById(R.id.profilePic)));

    }

    public FirebaseAuth getFirebaseUser(){
        return FirebaseAuth.getInstance();
    }
    private void signOut(View view) {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_signout]
    }
}
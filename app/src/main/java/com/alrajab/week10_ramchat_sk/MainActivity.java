package com.alrajab.week10_ramchat_sk;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String ANONYMOUS = "anonymous";
    public static final int DEFLT_MSG_LMT = 1000;
    private String mUsername;

    @BindView(R.id.sendButton) public Button mSendButton;
    @BindView(R.id.messageEditText) public EditText mMessageEditText;
    @BindView(R.id.messageListView) public ListView mLv;

    private MessageAdapter mMessageAdapter;
    private List<RamMessage> msgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

        mUsername = ANONYMOUS;

        msgs = new ArrayList<>();

        mMessageAdapter = new MessageAdapter(MainActivity.this,R.layout.note_list_item, msgs);
        mLv.setAdapter(mMessageAdapter);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0)
                    mSendButton.setEnabled(true);
                else
                    mSendButton.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        });

        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFLT_MSG_LMT)});

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RamMessage msg = new RamMessage(mMessageEditText.getText().toString(), mUsername, null);
               //Moaa msgs.add(msg);
                // Clear input box
                mMessageEditText.setText("");
                mMessageAdapter.add(msg);
                mLv.invalidate();
            }
        });
    }
}
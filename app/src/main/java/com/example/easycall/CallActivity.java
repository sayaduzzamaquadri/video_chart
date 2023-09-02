package com.example.easycall;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userIdEditeText;
    TextView hayUserTextView;
    ZegoSendCallInvitationButton voiceCallBtn,videoCallBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userIdEditeText = findViewById(R.id.user_id_edit_text);
        hayUserTextView = findViewById(R.id.hay_user_text_view);
        voiceCallBtn = findViewById(R.id.voice_call_btn);
        videoCallBtn = findViewById(R.id.video_call_btn);

        String userID = getIntent().getStringExtra("userID");
        hayUserTextView.setText("Hay "+userID);

        userIdEditeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String targetUserID = userIdEditeText.getText().toString().trim();
                setVoiceCallBtn(targetUserID);
                setVideoCallBtn(targetUserID);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void setVoiceCallBtn(String targetUserID){
        voiceCallBtn.setIsVideoCall(false);
        voiceCallBtn.setResourceID("zego_uikit_call");
        voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));

    }

    void setVideoCallBtn(String targetUserID){
        videoCallBtn.setIsVideoCall(true);
        videoCallBtn.setResourceID("zego_uikit_call");
        videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }
}
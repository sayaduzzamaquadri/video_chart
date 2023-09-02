package com.example.easycall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    EditText userIdEditText;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIdEditText = findViewById(R.id.user_id_edit_text);
        startBtn = findViewById(R.id.start_btn);

        startBtn.setOnClickListener((v)->{
            String userID = userIdEditText.getText().toString().trim();
            if(userID.isEmpty()){
                return;
            }
            // Start the service
            startService(userID);
            Intent intent = new Intent(MainActivity.this,CallActivity.class);
            intent.putExtra("userID",userID);
            startActivity(intent);
        });
    }

    void startService(String userID){
        Application application = getApplication();
        long appID = 836305070;
        String appSign ="e08c41540050126ef8293270ec4d523613a49925f8bdb9e536854a93736a2cae";  // yourAppSign
        String userName =userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}
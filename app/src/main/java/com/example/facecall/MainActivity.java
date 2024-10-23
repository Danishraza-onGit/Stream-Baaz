package com.example.facecall;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
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

        startBtn.setOnClickListener((v) -> {
            String userID = userIdEditText.getText().toString().trim();
            if(userID.isEmpty()){
                return;
            }
            //start the call
            startService(userID);
            Intent intent = new Intent(MainActivity.this, CallActivity.class) ; //You're telling Android: "I want to move from the MainActivity screen to the CallActivity screen."
            intent.putExtra("userID", userID);//You’re telling Android: "When you move to the next screen (CallActivity), take this extra piece of information with you. The information is labeled userID, and its value is whatever userID holds."
            startActivity(intent);//After this line, you'd typically use startActivity(intent) to actually make the screen switch happen.
        });


    }

    void startService(String userID){
        Application application = getApplication() ; // Android's application context
        long appID = 2115843621;   // yourAppID
        String appSign ="9ef008854e986e878ce6c80a75f007ee6cecf5ef86d471cb812cb4436af851cf";  // yourAppSign
//        String userID =; // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName = userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallService.unInit();
    }
}
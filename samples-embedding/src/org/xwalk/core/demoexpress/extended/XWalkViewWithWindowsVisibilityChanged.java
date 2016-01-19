package org.xwalk.core.demoexpress.extended;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithWindowsVisibilityChanged extends XWalkActivity implements MessageInfoXWalkView.MessageListener{

    private MessageInfoXWalkView mXWalkView;

    private TextView tv;

    private Button hb;

    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_windows_visibility_changed);
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView = (MessageInfoXWalkView) findViewById(R.id.win_visibility_xwalk_view);
        mXWalkView.setMessageListener(this);
        mXWalkView.load("http://www.baidu.com", null);
        tv = (TextView)findViewById(R.id.windows_visibility_msg_label);
        tv.setTextColor(Color.GREEN);
        thisActivity = this;
        hb = (Button)findViewById(R.id.new_win_button);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thisActivity, BlankWindowForVisibilityTesting.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMessageSent(String msg) {
        if(null != tv){
            String combineMsg = tv.getText().toString();
            if (0 == combineMsg.length()){
                combineMsg = "onWindowVisibilityChanged is invoked, visibility:" + msg;
            }else{
                combineMsg += "->" + msg;
            }

            tv.setText(combineMsg);
        }
    }
}

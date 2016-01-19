package org.xwalk.core.demoexpress.extended;


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithWindowFocusChanged extends XWalkActivity implements MessageInfoXWalkView.MessageListener{

    private MessageInfoXWalkView mXWalkView;

    private TextView tv;

    private Button hb;

    private Activity thisActivity;


    @Override
    protected void onXWalkReady() {
        mXWalkView = (MessageInfoXWalkView) findViewById(R.id.win_changed_xwalk_view);
        mXWalkView.setMessageListener(this);
        mXWalkView.load("http://www.baidu.com", null);
        tv = (TextView)findViewById(R.id.windows_focus_msg_label);
        tv.setTextColor(Color.GREEN);
        thisActivity = this;
        hb = (Button)findViewById(R.id.popup_button);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  AlertDialog.Builder(thisActivity)
                        .setTitle("New Window")
                        .setMessage("New window is pop-up." +
                                "Check if the invoking message is shown")
                        .setPositiveButton("confirm", null)
                        .show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_window_focus_changed);
    }

    @Override
    public void onMessageSent(String msg) {
        if(null != tv){
            tv.setText(msg);
        }
    }
}

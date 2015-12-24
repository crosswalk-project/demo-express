package org.xwalk.core.demoexpress.client;


import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithClientKeyEvent extends XWalkActivity {

    private XWalkView mXWalkView;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_client_key_event);
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView = (XWalkView) findViewById(R.id.client_keyevent_xwalk_view);
        mXWalkView.load("http://www.baidu.com", null);
        mXWalkView.setUIClient(new XWalkUIClient(mXWalkView) {
            @Override
            public boolean shouldOverrideKeyEvent(XWalkView view, KeyEvent event) {
                tv.setText("XWalkUIClient.shouldOverrideKeyEvent is invoked. Event: " + event.toString());
                return false;
            }
        });

        tv = (TextView)findViewById(R.id.client_keyevent_tip);
        tv.setTextColor(Color.GREEN);
    }
}

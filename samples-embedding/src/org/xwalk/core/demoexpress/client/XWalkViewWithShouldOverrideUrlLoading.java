package org.xwalk.core.demoexpress.client;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.widget.TextView;

public class XWalkViewWithShouldOverrideUrlLoading extends XWalkActivity {
    private XWalkView mXWalkView;
    private TextView mTitleText1;
    private TextView mTitleText2;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_should_override_url_loading);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    class TestXWalkResourceClientBase extends XWalkResourceClient {

        public TestXWalkResourceClientBase(XWalkView mXWalkView) {
            super(mXWalkView);
        }

        @Override
        public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
            if(url.endsWith("openedWindow.html")) {
                count++;
                mTitleText1.setText("shouldOverrideUrlLoading is invoked ");
                mTitleText2.setText(count + " times");
            }
            return super.shouldOverrideUrlLoading(mXWalkView, url);
        }
    }

    @Override
    protected void onXWalkReady() {

        mXWalkView.setResourceClient(new TestXWalkResourceClientBase(mXWalkView));

        XWalkPreferences.setValue(XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS, true);
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
        XWalkPreferences.setValue(XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW, true);
        mTitleText1 = (TextView) findViewById(R.id.message_tv);
        mTitleText2 = (TextView) findViewById(R.id.message_tv2);
        mXWalkView.load("file:///android_asset/window_navigate.html", null);
    }
}

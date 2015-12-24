package org.xwalk.core.demoexpress.client;


import android.os.Bundle;
import android.webkit.ValueCallback;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithOnCreateWindowRequested extends XWalkActivity {
    private XWalkView mXWalkView;
    private TextView mTitleText;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_on_create_window_requested);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    class TestXWalkUIClientBase extends XWalkUIClient {

        public TestXWalkUIClientBase(XWalkView arg0) {
            super(arg0);
        }

        @Override
        public boolean onCreateWindowRequested(XWalkView view, InitiateBy initiator, ValueCallback<XWalkView> callback) {
            count++;
            mTitleText.setText("onCreateWindowRequested is invoked "+count+" times");
            return super.onCreateWindowRequested(view, initiator, callback);
        }
    }

    @Override
    protected void onXWalkReady() {

        mXWalkView.setUIClient(new TestXWalkUIClientBase(mXWalkView));

        XWalkPreferences.setValue(XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS, true);
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
        XWalkPreferences.setValue(XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW, true);

        mTitleText = (TextView) findViewById(R.id.message_tv);
        mXWalkView.load("file:///android_asset/window_create_open.html", null);
    }
}

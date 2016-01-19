package org.xwalk.core.demoexpress.client;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithReceivedTitle extends XWalkActivity{

    private XWalkView mXWalkView;

    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_received_title);
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView = (XWalkView) findViewById(R.id.console_xwalk_view);
        mXWalkView.load("file:///android_asset/title_change.html", null);
        thisActivity = this;
        mXWalkView.setUIClient(new XWalkUIClient(mXWalkView) {
            @Override
            public void onReceivedTitle(XWalkView view, String title) {
                super.onReceivedTitle(view, title);
                if(!TextUtils.isEmpty(title)) {
                    thisActivity.setTitle(title);
                }
            }
        });
    }
}

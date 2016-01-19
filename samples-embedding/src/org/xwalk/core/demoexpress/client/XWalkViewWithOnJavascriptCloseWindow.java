// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.client;



import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.widget.TextView;


public class XWalkViewWithOnJavascriptCloseWindow extends XWalkActivity {
    private XWalkView mXWalkView;
    private TextView mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_on_javascript_close_window);
        mXWalkView = (XWalkView) findViewById(R.id.xwalk_view);
        mMessage = (TextView) findViewById(R.id.message_tv);
    }

    @Override
    protected void onXWalkReady() {

        mXWalkView.setUIClient(new XWalkUIClient(mXWalkView) {

            @Override
            public void onJavascriptCloseWindow(XWalkView view) {
                // TODO Auto-generated method stub
                super.onJavascriptCloseWindow(view);
                mMessage.setText("onJavascriptCloseWindow is invoked");
            }
        });

        mXWalkView.load("file:///android_asset/window_close_js.html", null);
    }
}

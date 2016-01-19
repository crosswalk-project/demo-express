// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.misc;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.widget.TextView;

public class XWalkViewWithPreferences extends XWalkActivity {
    private XWalkView mXWalkView;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_preferences);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
        msg = (TextView) findViewById(R.id.message_tv);
    }

    @Override
    protected void onXWalkReady() {
        // Enable remote debugging.
        // You can debug the web content via PC chrome.
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
        XWalkPreferences.setValue(XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW, true);
        XWalkPreferences.setValue(XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS, 3);
        XWalkPreferences.setValue(XWalkPreferences.ALLOW_UNIVERSAL_ACCESS_FROM_FILE, "testSetValue_String");
        XWalkPreferences.setValue(XWalkPreferences.PROFILE_NAME, "MyDefault");
        Boolean remote_debug = XWalkPreferences.getBooleanValue(XWalkPreferences.REMOTE_DEBUGGING);
        Boolean js_open_window = XWalkPreferences.getBooleanValue(XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW);
        Integer support_multi_window = XWalkPreferences.getIntegerValue(XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS);
        String allow_access = XWalkPreferences.getStringValue(XWalkPreferences.ALLOW_UNIVERSAL_ACCESS_FROM_FILE);
        String profile_name = XWalkPreferences.getStringValue(XWalkPreferences.PROFILE_NAME);
        String txt = "XWalkPreferences.REMOTE_DEBUGGING: " + remote_debug + "\n" +
                     "XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW: " + js_open_window + "\n" +
                     "XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS: " + support_multi_window + "\n" +
                     "XWalkPreferences.ALLOW_UNIVERSAL_ACCESS_FROM_FILE: " + allow_access + "\n" +
                     "XWalkPreferences.PROFILE_NAME: " + profile_name;
        msg.setText(txt);
        mXWalkView.load("http://www.baidu.com/", null);
    }
}

// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.basic;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;

public class XWalkViewWithOnHideOnShow extends XWalkActivity {
    private XWalkView mXWalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_hide_show);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    @Override
    protected void onXWalkReady() {
        // The web page below will display a video.
        // When home button is pressed, the activity will be in background, and the video will be paused.
        mXWalkView.load("http://www.iandevlin.com/html5/webvtt-example.html", null);
    }
}

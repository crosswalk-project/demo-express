// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.basic;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.widget.TextView;

public class XWalkViewWithVersionAndAPIVersion extends XWalkActivity {
    private XWalkView mXWalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_api_version);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    @Override
    protected void onXWalkReady() {

        String apiVersion = mXWalkView.getAPIVersion();
        String xwalkVersion = mXWalkView.getXWalkVersion();
        TextView text1 = (TextView) super.findViewById(R.id.text1);
        text1.setText("API Version: " + apiVersion + "; XWalk Version: " + xwalkVersion);
        mXWalkView.load("", "");
    }
}

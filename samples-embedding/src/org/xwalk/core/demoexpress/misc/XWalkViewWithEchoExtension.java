// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.misc;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.widget.TextView;

public class XWalkViewWithEchoExtension extends XWalkActivity {
    private ExtensionEcho mExtension;
    private XWalkView mXWalkView;
    private TextView textDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_extension);
    }

    @Override
    protected void onXWalkReady() {
        textDes = (TextView) super.findViewById(R.id.extension_des);
        textDes.setText("This sample is designed for extension feature. " +
                "If the extension gives a correct feedback, this page will show the" +
                " feedback message from extension and contain 'OK' in green color.");

        mExtension = new ExtensionEcho();
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview_extension);
        mExtension.print();
        mXWalkView.load("file:///android_asset/echo.html", null);
    }
}

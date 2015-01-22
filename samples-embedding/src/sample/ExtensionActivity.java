// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.embedded.api.sample;

import org.xwalk.embedded.api.sample.ExtensionEcho;
import org.xwalk.core.XWalkView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ExtensionActivity extends XWalkBaseActivity {
    private ExtensionEcho mExtension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = new StringBuffer();
        message.append("Test Purpose: \n\n")
        .append("Verifies extension can be supported .\n\n")
        .append("Expected Result:\n\n")
        .append("Test passes if the display of app contains 'passed' in green color.");

        setContentView(R.layout.xwview_extension_layout);

        textDes = (TextView) super.findViewById(R.id.extension_des);
        textDes.setText("This sample is designed for extension feature. If the extension give a correct feedback, this page will contains 'passed' in green color.");

        mExtension = new ExtensionEcho();
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview_extension);

        mXWalkView.load("file:///android_asset/echo.html", null);
    }
}

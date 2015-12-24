// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.extended;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class XWalkViewWithClearFormData extends XWalkActivity {
    private XWalkView mXWalkView;
    private Button clearFormData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_clear_form_data);
        clearFormData = (Button) findViewById(R.id.clear_formdata);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    @Override
    protected void onXWalkReady() {

        clearFormData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mXWalkView.clearFormData();
            }
        });
        mXWalkView.load("file:///android_asset/datalist.html", null);
    }
}

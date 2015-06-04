// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.embedding.api.demo;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public abstract class XWalkBaseActivity extends XWalkActivity {
    protected XWalkView mXWalkView;
    protected Button mDetailInfoButton;
    protected StringBuffer message;
    protected TextView textDes;

    protected void showDetailInfo(final Context context) {
        mDetailInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new  AlertDialog.Builder(context)
                .setTitle("Info" )
                .setMessage(message.toString())
                .setPositiveButton("confirm" ,  null )
                .show();
            }
        });
    }


}

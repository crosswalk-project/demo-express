// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.client;


import org.xwalk.core.ClientCertRequest;
import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class XWalkViewWithOnReceivedLoadError extends XWalkActivity {
    private XWalkView mXWalkView;
    private TextView mTextView;
    private TextView mMessage;
    private static final String TAG = XWalkViewWithOnReceivedLoadError.class.getName();
    private static final String BAD_SSL_WEBSITE = "https://egov.privasphere.com/";

    class ResourceClient extends XWalkResourceClient {

        public ResourceClient(XWalkView xwalkView) {
            super(xwalkView);
        }

        public void onReceivedLoadError(XWalkView view, int errorCode, String description,
                String failingUrl) {
            Log.d(TAG, "Load Failed:" + description);
            super.onReceivedLoadError(view, errorCode, description, failingUrl);
            mMessage.setText(mTextView.getText().toString() + "Load Failed: " + description
                    + "\n");
        }

        public void onReceivedClientCertRequest(XWalkView view,
                ClientCertRequest handler) {
            // TODO Auto-generated method stub
            Log.d(TAG, "ClientCert Request:" + handler);
            super.onReceivedClientCertRequest(view, handler);
            mMessage.setText(mTextView.getText().toString() + "ClientCert Request: " + handler
                    + "\n");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_api_version);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
        mTextView = (TextView) findViewById(R.id.text1);
        mMessage = (TextView) findViewById(R.id.message_tv);
        mTextView.setText("XWalkView is handling a Bad SSL client certificate request. The load website is "
                + BAD_SSL_WEBSITE + "\n");
    }

    @Override
    protected void onXWalkReady() {

        mXWalkView.setResourceClient(new ResourceClient(mXWalkView));
        mXWalkView.load(BAD_SSL_WEBSITE, null);
    }
}

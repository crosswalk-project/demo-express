package org.xwalk.core.demoexpress.client;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.widget.ImageView;

public class XWalkViewWithOpenFileChooser extends XWalkActivity {
    private XWalkView mXWalkView;
    private ImageView mImageView;
    private ValueCallback<Uri> mUploadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_openfile_chooser);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
        mImageView = (ImageView) findViewById(R.id.imageview);
    }

    @Override
    protected void onXWalkReady() {

        mXWalkView.setUIClient(new UIClient(mXWalkView));
        mXWalkView.load("file:///android_asset/open_file.html", null);
    }


    class UIClient extends XWalkUIClient {

        public UIClient(XWalkView xwalkView) {
            super(xwalkView);
        }

        public void openFileChooser(XWalkView view, ValueCallback<Uri> uploadFile,
                String acceptType, String capture) {
            super.openFileChooser(view, uploadFile, acceptType, capture);
            mUploadMessage = uploadFile;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        if (mUploadMessage == null) return;
        Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
        mUploadMessage.onReceiveValue(result);
        mUploadMessage = null;

        Bitmap bm = null;
        ContentResolver resolver = getContentResolver();
        try {
            bm = MediaStore.Images.Media.getBitmap(resolver, result);
            mImageView.setImageBitmap(bm);
            bm = null;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

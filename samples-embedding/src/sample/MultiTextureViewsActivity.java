package org.xwalk.embedded.api.sample;

import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MultiTextureViewsActivity extends XWalkBaseActivity {

    private Button mResizeButton;
    private RelativeLayout root;
    private boolean sizeFlag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true);

        setContentView(R.layout.multi_texture_views);
        RelativeLayout parent = (RelativeLayout) findViewById(R.id.multi_texture_views);
        textDes = (TextView)findViewById(R.id.multiViews_des);
        textDes.setText("This sample demonstrates Multiple TextureViews can be shown" +
        		" in order. A,B,C views are TextureViews, D,E,F views are WebViews, " +
        		"the sort order of A,B,C are the same with D,E,F when rotate or " +
        		"restore the screen. Click \"Resize\" button to enlarge or reduce the views.");
        mResizeButton = (Button) findViewById(R.id.run_resize);
        LinearLayout btnLay = (LinearLayout) findViewById(R.id.toolbar2);
        btnLay.setY(180);
        btnLay.setX(550);
        root = (RelativeLayout) findViewById(R.id.root_views);
        for (int i = 0; i < 3; i++) {
            XWalkView xWalkView = new XWalkView(this, this);
            xWalkView.setX(i * 100);
            xWalkView.setY(130 + (i + 1) * 60);
            xWalkView.load(null, String.format("<html><head><meta name='viewport' content='width=device-width'/></head>"
                    + "<body style='background-color: %s;'><h1>%s</h1></body></html>", i % 2 == 0 ? "white" : "grey", i == 0 ? "A" : i == 1 ?  "B" : "C"));
            root.addView(xWalkView, 200, 200);
        }

        for (int i = 3; i < 6; i++) {
            WebView webView = new WebView(this);
            webView.setX(i * 100);
            webView.setY(130 + (i + 1) * 60);
            webView.loadData(String.format("<html><body style='background-color: %s'><h1>%s</h1></body></html>",
                    i % 2 == 0 ? "white" : "grey", i == 3 ? "D" : i == 4 ?  "E" : "F"), "text/html", "utf-8");
            root.addView(webView, 200, 200);
        }

        setContentView(parent);
        mResizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sizeFlag) {
                    resize(root, 200, 200);
                    sizeFlag = false;
                } else {
                    resize(root, 400, 400);
                    sizeFlag = true;
                }
            }
        });
    }

    private void resize(RelativeLayout views, int width, int height) {
        for (int i = 0; i < views.getChildCount(); i++) {
            View child = views.getChildAt(i);
            child.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        }
    }
}

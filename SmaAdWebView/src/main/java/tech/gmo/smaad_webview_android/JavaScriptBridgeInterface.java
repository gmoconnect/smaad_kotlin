package tech.gmo.smaad_webview_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class JavaScriptBridgeInterface {
    private static final String LOG_TAG = "JSBridgeInterface";
    private SmaAdWebView.Listener mListener;
    private Context mContext;

    public JavaScriptBridgeInterface(Context context, SmaAdWebView.Listener listener) {
        this.mListener = listener;
        this.mContext = context;
    }

    @JavascriptInterface
    public void webViewClosed(){
        if (mListener != null){
            mListener.onWebViewClosed();
        }
    }

    @JavascriptInterface
    public void launchURL(final String url){
        // UIスレッドで実行する必要があるため、runOnUiThreadを使用
        if (mContext instanceof Activity) {
            ((Activity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // URLを解析し、Intentを作成してブラウザを起動
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "Could not launch external browser for: " + url, e);
                    }
                }
            });
        }
    }
}

package tech.gmo.smaad_webview_android

import android.content.Context

object AppInfoUtil {

    /**
     * アプリケーションのバージョン名を取得する関数
     *
     * @param context アプリケーションのコンテキスト
     * @return アプリケーションのバージョン名。取得できない場合は"Not Available"を返す。
     */
    fun getAppVersionName(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Not Available"
        } catch (e: Exception) {
            "Not Available"
        }
    }
}
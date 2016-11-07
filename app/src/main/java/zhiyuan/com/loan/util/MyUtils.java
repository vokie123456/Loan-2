package zhiyuan.com.loan.util;import android.app.ActivityManager;import android.app.ActivityManager.RunningAppProcessInfo;import android.app.ProgressDialog;import android.content.Context;import android.net.ConnectivityManager;import android.net.NetworkInfo;import android.util.DisplayMetrics;import android.util.TypedValue;import android.view.WindowManager;import java.io.Closeable;import java.io.IOException;import java.util.List;public class MyUtils {    private static ProgressDialog dialog;    public static String getProcessName(Context cxt, int pid) {        ActivityManager am = (ActivityManager) cxt                .getSystemService(Context.ACTIVITY_SERVICE);        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();        if (runningApps == null) {            return null;        }        for (RunningAppProcessInfo procInfo : runningApps) {            if (procInfo.pid == pid) {                return procInfo.processName;            }        }        return null;    }    public static void close(Closeable closeable) {        try {            if (closeable != null) {                closeable.close();            }        } catch (IOException e) {            e.printStackTrace();        }    }    public static DisplayMetrics getScreenMetrics(Context context) {        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);        DisplayMetrics dm = new DisplayMetrics();        wm.getDefaultDisplay().getMetrics(dm);        return dm;    }    public static float dp2px(Context context, float dp) {        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,                context.getResources().getDisplayMetrics());    }    public static boolean isWifi(Context context) {        ConnectivityManager connectivityManager = (ConnectivityManager) context                .getSystemService(Context.CONNECTIVITY_SERVICE);        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {            return true;        }        return false;    }    public static void executeInThread(Runnable runnable) {        new Thread(runnable).start();    }    public static void showDialog(String message,Context context){        try {            if (dialog == null) {                dialog = new ProgressDialog(context);                dialog.setCancelable(true);            }            dialog.setMessage(message);            dialog.show();        } catch (Exception e) {            // 在其他线程调用dialog会报错        }    }    public static void hideDialog() {        if (dialog != null && dialog.isShowing())            try {                dialog.dismiss();            } catch (Exception e) {            }    }}
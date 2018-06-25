package cn.com.base.util;

import android.app.Activity;

import java.util.Stack;

/**
 * activity管理工具
 * 
 * @author Administrator
 *
 */
public class CurActivityManager {
	private static final String TAG = "ActivityManager";
	private static Stack<Activity> activityStack;
	private static CurActivityManager instance;
	private Activity currActivity;

	private CurActivityManager() {
	}

	public static CurActivityManager getActivityManager() {
		if (instance == null) {
			instance = new CurActivityManager();
		}
		return instance;
	}

	// 退出栈顶Activity
	public void popActivity(Activity activity) {
		if (activity == null || activityStack == null) {
			return;
		}
		if (activityStack.contains(activity)) {
			activityStack.remove(activity);
		}
		currActivity = activity;
		// activity.finish();

		// activity = null;
	}

	public void destoryActivity(Activity activity) {
		if (activity == null) {
			return;
		}
		activity.finish();
		if (activityStack.contains(activity)) {
			activityStack.remove(activity);
		}
		activity = null;
	}

	// 获得当前栈顶Activity
	public Activity currentActivity() {
		if (activityStack == null || activityStack.empty()) {
			return null;
		}
		return activityStack.lastElement();
	}

	// 将当前Activity推入栈中
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	// 退出栈中除指定的Activity外的所有
	public void popAllActivityExceptOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			destoryActivity(activity);
		}
	}

	// 退出栈中所有Activity
	public void popAllActivity() {
		popAllActivityExceptOne(null);
	}

	public Activity getCurrentActivity() {
		return currActivity;
	}

	public int getActivityStackSize() {
		int size = 0;
		if (activityStack != null) {
			size = activityStack.size();
		}
		return size;
	}
}

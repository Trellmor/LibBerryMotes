package com.trellmor.berrymotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.trellmor.berrymotes.lib.R;

/**
 * Emote helpers
 * 
 * @author Daniel
 *
 */
public class EmoteUtils {
	public static final String BERRYMOTES_NAME = "com.trellmor.berrymotes";
	
	public static final int BERRYMOTES_VERSION_1_0_0 = 10000;
	public static final int BERRYMOTES_VERSION_1_1_0 = 10100;
	public static final int BERRYMOTES_VERSION_1_2_0 = 10200;
	public static final int BERRYMOTES_VERSION_1_3_0 = 10300;
	
	private EmoteUtils() {
	}

	/**
	 * Check if BerryMotes app is installed 
	 * 
	 * @param context Android context
	 * @return true if BerryMotes is installed
	 */
	public static boolean isBerryMotesInstalled(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			pm.getPackageInfo(BERRYMOTES_NAME, PackageManager.GET_ACTIVITIES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}
	
	/**
	 * Check if BerryMotes app is installed 
	 * 
	 * @param context Android context
	 * @param version BerryMotes version
	 * @return true if BerryMotes is installed
	 */
	public static boolean isBerryMotesInstalled(Context context, int version) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo(BERRYMOTES_NAME, PackageManager.GET_ACTIVITIES);
			return pi.versionCode >= version;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}
	
	/**
	 * Launch play store to install BerryMotes
	 * 
	 * @param context Android context
	 */
	public static void launchMarket(Context context) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + BERRYMOTES_NAME));
		context.startActivity(intent);
	}
	
	/**
	 * Open BerryMotes settings
	 * 
	 * @param context Android context
	 */
	public static void launchBerryMotesSettings(Context context) {
		Intent intent = new Intent();
		intent.setAction(BERRYMOTES_NAME + ".Settings");
		context.startActivity(intent);
	}
	
	/**
	 * Show AlertDialog to install BerryMotes
	 * 
	 * @param context Android context
	 */
	public static void showInstallDialog(Context context) {
		final Context theContext = context;
		AlertDialog.Builder builder = new AlertDialog.Builder(theContext);
		builder.setTitle(R.string.berrymotes_app_name);
		builder.setMessage(R.string.berrymotes_not_installed);
		builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EmoteUtils.launchMarket(theContext);
			}
		});
		builder.setNegativeButton(android.R.string.cancel, null);
		builder.show();
	}
}

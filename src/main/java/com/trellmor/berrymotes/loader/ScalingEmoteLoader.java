package com.trellmor.berrymotes.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public class ScalingEmoteLoader implements EmoteLoader {
	private final Context mContext;

	/**
	 * Create new ScalingEmoteLoader instance
	 * 
	 * Automatically upscales the bitmap to the device DPI
	 * 
	 * @param context
	 *            Android context
	 */
	public ScalingEmoteLoader(Context context) {
		mContext = context;
	}

	@Override
	public Drawable fromPath(String path) {
		Drawable drawable = null;
		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDensity = DisplayMetrics.DENSITY_MEDIUM;		
		
		Bitmap bitmap = BitmapFactory.decodeFile(path, options);		
		if (bitmap != null) {
			drawable = new BitmapDrawable(mContext.getResources(), bitmap);
		}
		
		return drawable;
	}
}

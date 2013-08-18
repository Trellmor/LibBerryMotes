/*
 * LibBerryMotes
 * 
 * Copyright (C) 2013 Daniel Triendl <trellmor@trellmor.com>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package com.trellmor.berrymotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class EmotesFormatter {
	private boolean mEmotesEnabled = false; 
	private SettingsChangedListener mSettingsChangedListener = new SettingsChangedListener();
	
	public EmotesFormatter(Context context) {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		mEmotesEnabled = settings.getBoolean(EmoteSettings.KEY_BERRYMOTES_ENABLED, false);
		settings.registerOnSharedPreferenceChangeListener(mSettingsChangedListener);
	}
	
	public String formatString(String s) {
		if (mEmotesEnabled) {
			return replaceEmotes(s);
		} else {
			return s;
		}
	}
	
	public static String replaceEmotes(String s) {
		return s.replaceAll("\\[\\]\\(\\/([\\w:!#\\/]+)([-\\w!]*)([^)]*)\\)",
				"<img src=\"$1\" alt=\"$1\" />");
	}
	
	private class SettingsChangedListener implements SharedPreferences.OnSharedPreferenceChangeListener {

		@Override
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			if (EmoteSettings.KEY_BERRYMOTES_ENABLED.equals(key)) {
				mEmotesEnabled = sharedPreferences.getBoolean(key, false);
			}
		}
		
	}
}
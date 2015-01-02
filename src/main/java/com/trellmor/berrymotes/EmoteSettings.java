/*
 * LibBerryMotes
 * 
 * Copyright (C) 2015 Daniel Triendl <trellmor@trellmor.com>
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

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import com.trellmor.berrymotes.lib.R;

/**
 * Emote settings helper
 * 
 * @author Daniel
 *
 */
public class EmoteSettings {
	/**
	 * Key for storing emotes enabled preference
	 */
	public static final String KEY_BERRYMOTES_ENABLED = "berrymotes.enabled";
	
	/**
	 * Key for launching BerryMotes settings
	 */
	public static final String KEY_BERRYMOTES_SETTINGS = "berrymotes.settings";

	private EmoteSettings() {
	}

	/**
	 * Add emote settings to an existing settings activity
	 * 
	 * @param context Settings activity
	 */
	@SuppressWarnings("deprecation")
	public static void addEmoteSettings(PreferenceActivity context) {
		context.addPreferencesFromResource(R.xml.pref_emotes);

		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

		Preference prefEnabled = context.findPreference(KEY_BERRYMOTES_ENABLED);
		prefEnabled.setOnPreferenceChangeListener(sEnabledChangeListener);
		boolean enabled = settings.getBoolean(KEY_BERRYMOTES_ENABLED, false);
		boolean canEnable = sEnabledChangeListener.onPreferenceChange(prefEnabled,
				settings.getBoolean(KEY_BERRYMOTES_ENABLED, false));

		if (enabled && !canEnable) {
			settings.edit().putBoolean(KEY_BERRYMOTES_ENABLED, false).commit();
		}

		Preference prefSettings = context.findPreference(KEY_BERRYMOTES_SETTINGS);
		prefSettings.setOnPreferenceClickListener(sSettingsClickListener);
	}

	public static void addEmoteSettings(PreferenceFragment fragment) {
		fragment.addPreferencesFromResource(R.xml.pref_emotes);

		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(fragment.getActivity());

		Preference prefEnabled = fragment.findPreference(KEY_BERRYMOTES_ENABLED);
		prefEnabled.setOnPreferenceChangeListener(sEnabledChangeListener);
		boolean enabled = settings.getBoolean(KEY_BERRYMOTES_ENABLED, false);
		boolean canEnable = sEnabledChangeListener.onPreferenceChange(prefEnabled,
				settings.getBoolean(KEY_BERRYMOTES_ENABLED, false));

		if (enabled && !canEnable) {
			settings.edit().putBoolean(KEY_BERRYMOTES_ENABLED, false).commit();
		}

		Preference prefSettings = fragment.findPreference(KEY_BERRYMOTES_SETTINGS);
		prefSettings.setOnPreferenceClickListener(sSettingsClickListener);
	}

	/**
	 * Helper to set preference summaries and show install BerryMotes dialog if necessary
	 */
	public static final Preference.OnPreferenceChangeListener sEnabledChangeListener = new Preference.OnPreferenceChangeListener() {

		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			boolean canChange = true;
			if (KEY_BERRYMOTES_ENABLED.equals(preference.getKey())) {
				boolean boolValue = Boolean.parseBoolean(newValue.toString());

				if (boolValue) {
					canChange = EmoteUtils.isBerryMotesInstalled(preference
							.getContext());
					
					if (!canChange) {
						EmoteUtils.showInstallDialog(preference.getContext());
					}
				}

				if (boolValue && canChange) {
					preference
							.setSummary(R.string.pref_description_berrymotes_enable_true);
				} else {
					preference
							.setSummary(R.string.pref_description_berrymotes_enable_false);
				}
			}
			return canChange;
		}
	};
	

	/**
	 * Helper to launch BerryMotes settings or show install BerryMotes dialog if necessary
	 */	
	public static final Preference.OnPreferenceClickListener sSettingsClickListener = new Preference.OnPreferenceClickListener() {
		
		@Override
		public boolean onPreferenceClick(Preference preference) {
			if (EmoteUtils.isBerryMotesInstalled(preference.getContext())) {
				EmoteUtils.launchBerryMotesSettings(preference.getContext());
			} else {
				EmoteUtils.showInstallDialog(preference.getContext());
			}
			return true;
		}
	};
}

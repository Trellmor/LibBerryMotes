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

package com.trellmor.berrymotes.provider;

import android.net.Uri;

/**
 * File Contract
 * 
 * @author Daniel
 *
 */
public class FileContract {
	private FileContract() {
	}

	/**
	 * Content provider authority.
	 */
	public static final String CONTENT_AUTHORITY = "com.trellmor.berrymotes.files";

	/**
	 * Base uri (content://com.trellmor.berrymotes.files)
	 */
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://"
			+ CONTENT_AUTHORITY);

	/**
	 * URI Path for application files
	 */
	public static final String PATH_FILE = "file";

	/**
	 * URI Path for emote files
	 */
	public static final String PATH_EMOTE = "emote";
	
	/**
	 * Content uri for application files
	 */
	public static final Uri CONTENT_URI_FILE = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FILE).build();
	
	/**
	 * Content uri for emote files
	 */
	public static final Uri CONTENT_URI_EMOTE = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EMOTE).build();
	
	/**
	 * Generate the URI for an emote
	 * 
	 * @param name Emote name
	 * @param apng Set to true for animated emotes. Animated emotes will be returned as gifs
	 * @return Emote file URI
	 */
	public static Uri getUriForEmote(String name, boolean apng) {
		String ext;
		if (apng) {
			ext = ".gif";
		} else {
			ext = ".png";
		}
		return CONTENT_URI_EMOTE.buildUpon().appendPath(name + ext).build();
	}
}

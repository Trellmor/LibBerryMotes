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

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class EmotesContract {
	private EmotesContract() {
	}

	/**
	 * Content provider authority.
	 */
	public static final String CONTENT_AUTHORITY = "com.trellmor.berrymotes";

	/**
	 * Base URI. (content://com.example.android.network.sync.basicsyncadapter)
	 */
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://"
			+ CONTENT_AUTHORITY);
	
	private static final String PATH_EMOTES = "emotes";

	public static final class Emote implements BaseColumns {
		private Emote() {
		}

		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
				+ "/vnd.berrymotes.emotes";
		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
				+ "/vnd.berrymotes.emote";
		
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EMOTES).build();

		public static final String TABLE_NAME = "emotes";

		public static final String COLUMN_NAME = "name";

		public static final String COLUMN_NSFW = "nsfw";

		public static final String COLUMN_APNG = "apng";

		public static final String COLUMN_IMAGE = "image";

		public static final String COLUMN_DELAY = "frame_delay";

		public static final String COLUMN_INDEX = "frame_index";
	}
}

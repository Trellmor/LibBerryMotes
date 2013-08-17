package com.trellmor.BerryMotes;

public class EmotesFormatter {
	public static String formatString(String s) {
		return s.replaceAll("\\[\\]\\(\\/([\\w:!#\\/]+)([-\\w!]*)([^)]*)\\)",
				"<img src=\"$1\" alt=\"$1\" />");
	}
}
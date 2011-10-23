package com.krikelin.dreamcatcher.providers;

import android.net.Uri;
import android.provider.BaseColumns;

public class Dream {
	public static final class Dreams implements BaseColumns {
		public static final String DREAM_ID = "_id";
		public static final String TITLE = "title";
		public static final String DESCRIPTION = "description";
		public static final String TIME = "time";
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.krikelin.lovecatcher.Dream";
		public static final String EROTIC = "erotic";
		public static final String TAGS = "tags";
		public static final String PART = "part";

		public static final Uri CONTENT_URI = Uri.parse("content://" + DreamContentProvider.AUTHORITY + "/dreams");

	}
}

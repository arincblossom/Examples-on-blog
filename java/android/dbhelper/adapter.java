public class FileListDBAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_PATH = "path";
	private static final String DATABASE_TABLE = "rings"; // DB TABLE NAME
	private Context mCtx;
	private FileDBHelper mDbHelper;
	private SQLiteDatabase mDb; // DB를 저장하는 용도

	public FileListDBAdapter(Context context) {
		mCtx = context;
	}

	// open/close DB
	public FileListDBAdapter open() throws SQLException {
		mDbHelper = new FileDBHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		if (mDbHelper != null)
			mDbHelper.close();
		if (mDb != null)
			mDb.close();
	}
}
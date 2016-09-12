// 사전처리
if (Build.VERSION.SDK_INT < 19) {
	Intent intent = new Intent();
	intent.setType("image/*");
	intent.setAction(Intent.ACTION_GET_CONTENT);
	startActivityForResult(intent, GALLERY_INTENT_CALLED);
} else {
	Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
	intent.addCategory(Intent.CATEGORY_OPENABLE);
	intent.setType("image/*");
	startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED);
}

// 이전 형태의 경로로 만들기
// 참고: http://stackoverflow.com/questions/19834842/android-gallery-on-kitkat-returns-different-uri-for-intent-action-get-content

// id만 뽑아서 새로 만들기
// 참고: http://stackoverflow.com/questions/20260416/retrieve-absolute-path-when-select-image-from-gallery-kitkat-android

///  아래의 코드는 onActivityResult에 들어가는 코드
if(requestCode == GALLERY_KITKAT_INTENT_CALLED && resultCode == RESULT_OK){

    Uri originalUri = data.getData();

    final int takeFlags = data.getFlags()
			& (Intent.FLAG_GRANT_READ_URI_PERMISSION
			| Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
    // Check for the freshest data.
    getContentResolver().takePersistableUriPermission(originalUri, takeFlags);

    /* now extract ID from Uri path using getLastPathSegment() and then split with ":"
    then call get Uri to for Internal storage or External storage for media I have used getUri()
    */

    String id = originalUri.getLastPathSegment().split(":")[1]; 
    final String[] imageColumns = {MediaStore.Images.Media.DATA };
    final String imageOrderBy = null;

    Uri uri = getUri();
    String selectedImagePath = "path";

    Cursor imageCursor = managedQuery(uri, imageColumns,
	  MediaStore.Images.Media._ID + "="+id, null, imageOrderBy);

    if (imageCursor.moveToFirst()) {
	selectedImagePath = imageCursor.getString(imageCursor.getColumnIndex(MediaStore.Images.Media.DATA));
    }
    Log.e("path",selectedImagePath ); // use selectedImagePath 
}else if() {
      // for older version use existing code here
}

// By using this method get the Uri of Internal/External Storage for Media
private Uri getUri() {
    String state = Environment.getExternalStorageState();
    if(!state.equalsIgnoreCase(Environment.MEDIA_MOUNTED))
	return MediaStore.Images.Media.INTERNAL_CONTENT_URI;

    return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
}
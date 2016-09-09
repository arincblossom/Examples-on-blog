public void savedb() {
	File dbFile = new File(mDb.getPath());
	File recv = new File("/mnt/sdcard/rrm.db");

	try {
		FileInputStream inp = new FileInputStream(dbFile);
		FileOutputStream out = new FileOutputStream(recv);

		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = inp.read(buffer, 0, 1024)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		inp.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
TextView tv[];

tv = new TextView[10];

for(int i = 0; i < 10; i++) {
	tv[i] = (TextView) findViewById(getResId(this, "tvid"+String.valueOf(i));
}

public int getResId(Context ctx, String id) {
	Resources res = ctx.getResources();
	return res.getIdentifier(resName, "id", ctx.getPackageName());
}
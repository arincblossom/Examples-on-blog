srlayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
ptrList = (ListView) findViewById(R.id.list_timeline);
srlayout.setOnRefreshListener(new RefreshListener());
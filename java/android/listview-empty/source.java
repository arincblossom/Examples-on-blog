@Override
public void onContentChanged() {
    super.onContentChanged();

    View empty = findViewById(R.id.empty);
    ListView list = (ListView) findViewById(R.id.list);
    list.setEmptyView(empty);
}
package com.example.abhishekshah1.contentresolver;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class MainActivity extends Activity {

    ListView listView;
    TextView Name;
    TextView FatherName;
    TextView MotherName;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
         FatherName = (TextView) findViewById(R.id.father_name);
      Name = (TextView) findViewById(R.id.name);
      MotherName = (TextView) findViewById(R.id.mother_name);
        Cursor cr=loadDatabase();
        cr.moveToFirst();
         cr.moveToNext();
        String s=cr.getString(0);
        String s1=cr.getString(1);
        String s2=cr.getString(2);
        String s3=cr.getString(3);
        adapter = new SimpleCursorAdapter(this, R.layout.card_view, cr, new String[]{Constants.NAME,Constants.FATHER_NAME,Constants.MOTHER_NAME}, new int[]{R.id.name,R.id.father_name,R.id.mother_name});///*,R.id.father_name,R.id.mother_name*/};
         listView.setAdapter(adapter);

    }
  private Cursor loadDatabase() {
      ContentResolver resolver = getContentResolver();
      String[] projection = new String[]{"_id",Constants.NAME, Constants.FATHER_NAME, Constants.MOTHER_NAME};
      String selection = null;
      String[] selectionArguments = null;
      String sortOrder = "ASC";
      Uri uri = Uri.parse(Constants.CONTENT_URI);
      String myType = resolver.getType(uri);
    //  Log.i("type:", myType);
      Cursor cursor = resolver.query(uri, projection, selection, selectionArguments, null);
      int i=cursor.getCount();
      return cursor;
      //cursor.moveToFirst();
     // cursor.moveToNext();
     // adapter = new SimpleCursorAdapter(this, R.layout.card_view, cursor,new String[]{cursor.getString(1),/*Constants.FATHER_NAME,Constants.MOTHER_NAME*/}, new int[]{R.id.name/*,R.id.father_name,R.id.mother_name*/});
    //  listView.setAdapter(adapter);
   //   while (cursor.isAfterLast() == false) {
        //  String name = cursor.getString(0);
          //String fathername = cursor.getString(1);
         // String mothername = cursor.getString(2);

          //cursor.moveToNext();
     // }


/*
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
/*
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Cursor c = (Cursor) adapter.getItem(position);

              String cid = c.getString(c
                      .getColumnIndex(ContactsContract.Contacts._ID));
              Intent iCInfo = new Intent(getApplicationContext(), Cinfo.class);
              iCInfo.putExtra("cid", cid);
              startActivity(iCInfo);}
      });
      });*/
  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

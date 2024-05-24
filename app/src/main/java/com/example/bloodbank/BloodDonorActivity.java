package com.example.bloodbank;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class BloodDonorActivity extends Activity {

    private ListView listViewContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        listViewContacts = findViewById(R.id.listViewContacts);

        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                    new int[]{android.R.id.text1},
                    0
            );

            listViewContacts.setAdapter(adapter);

            listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                    String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    // Create an intent to send back the selected contact's name to the calling activity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("contactName", contactName);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            });

            cursor.close();
        }
    }
}

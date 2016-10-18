package com.example.sqlitedatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

public class Main extends Activity {
	private EditText editTextTableName, editTextName, editTextPhone;
	private DatabaseActivity databaseActivity;
	private RadioGroup radioGroup;
	private final String DB_NAME = "SqlDatabase.db";
	private final int version = 1;

	private final String TABLE_NAME = "dbtable";
	private final String ID = "_id";
	private final String NAME = "name";
	private final String PHONE = "phone";
	private final String DB_CREATE_ENTRIES = "CREATE TABLE dbtable(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT)";

	private ListView listView;
	private List<Map<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
	}

	private void init() {
		editTextTableName = (EditText) findViewById(R.id.editTextTableName);
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextPhone = (EditText) findViewById(R.id.editPhone);

		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		listView = (ListView) findViewById(R.id.listView1);

		databaseActivity = new DatabaseActivity(getApplicationContext(), DB_NAME, version);
		databaseActivity.addTable(DB_CREATE_ENTRIES);
		databaseActivity.createTable();
	}

	private String getTableName() {
		return editTextTableName.getText().toString();
	}

	private String getName() {
		return editTextName.getText().toString();
	}

	private String getPhone() {
		return editTextPhone.getText().toString();
	}

	// show all data in table
	private void show() {
		Cursor cursor = databaseActivity.getAll(getTableName());
		if (cursor == null)
			return;

		setListView(cursor);
	}

	private void setListView(Cursor c) {
		list = new ArrayList<Map<String, Object>>();

		if (c.getCount() == 0) {
			clearListView();
			return;
		}

		do {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put(ID, c.getString(0));
			item.put(NAME, c.getString(1));
			item.put(PHONE, 0 + c.getString(2));
			list.add(item);

		} while (c.moveToNext());

		ListAdapter adapter = new SimpleAdapter(Main.this, list, R.layout.listview_item,
				new String[] { ID, NAME, PHONE }, new int[] { R.id.textViewId, R.id.textViewName, R.id.textViewPhone });

		listView.setAdapter(adapter);
	}

	private void clearListView() {
		list = new ArrayList<Map<String, Object>>();
		ListAdapter adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		listView.setAdapter(adapter1);
	}

	/**************************BUTTON CLICK*****************************/

	public void onInsertClick(View v) {
		ContentValues values = new ContentValues();
		values.put("name", getName());
		values.put("phone", getPhone());
		databaseActivity.insertData(getTableName(), values);

		show();
	}

	public void onReadClick(View v) {
		if (getTableName().equals(""))
			return;

		if (!getName().equals("")) {
			Cursor c = databaseActivity.readData(getTableName(), new String[] { ID, NAME, PHONE }, NAME, getName());
			setListView(c);
		} else if (!getPhone().equals("")) {
			Cursor c = databaseActivity.readData(getTableName(), new String[] { ID, NAME, PHONE }, PHONE, getPhone());
			setListView(c);
		} else {
			show();
		}
	}

	// Update data by radio button
	public void onUpdateClick(View v) {
		if (getTableName().equals("") || getName().equals("") || getPhone().equals(""))
			return;

		if (radioGroup.getCheckedRadioButtonId() == R.id.radio1) {
			ContentValues values = new ContentValues();
			values.put(PHONE, getPhone());
			databaseActivity.updateData(getTableName(), values, NAME, getName());

		} else if (radioGroup.getCheckedRadioButtonId() == R.id.radio0) {
			ContentValues values = new ContentValues();
			values.put(NAME, getName());
			databaseActivity.updateData(getTableName(), values, PHONE, getPhone());
		}

		show();
	}

	public void onDeleteClick(View v) {
		if (getTableName().equals(""))
			return;

		if (!getName().equals("")) {
			databaseActivity.deleteData(getTableName(), NAME, getName());
		}

		if (!getPhone().equals("")) {
			databaseActivity.deleteData(getTableName(), PHONE, getPhone());
		}
		show();
	}

	public void onEmptyClick(View v) {
		databaseActivity.emptyTable(getTableName());
		show();
	}
	
	public void onTableClick(View v) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainTable.class);
		startActivity(intent);
	}

}

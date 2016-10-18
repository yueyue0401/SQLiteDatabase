package com.example.sqlitedatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sqlitedatabase.table.Table;
import com.example.sqlitedatabase.table.Table_HRVFiveValue;
import com.example.sqlitedatabase.table.Table_HeathData_NeedToUpload;
import com.example.sqlitedatabase.table.Table_NeedToUploadSyncFile;
import com.example.sqlitedatabase.table.Table_WhiteAccountAndSetting;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainTable extends Activity {
	private ListView listView;
	private EditText editText1, editText2;
	private RadioGroup radioGroup;
	private RadioButton radioButton1, radioButton2;
	private Spinner spinner;
	private TextView textView1, textView2;
	
	private Table_HeathData_NeedToUpload tableHeath;
	private Table_HRVFiveValue tableHRV;
	private Table_NeedToUploadSyncFile tableNeed;
	private Table_WhiteAccountAndSetting tableWhite;

	// 0: Table_HeathData_NeedToUpload
	// 1: Table_HRVFiveValue
	// 2: Table_NeedToUploadSyncFile
	// 3: Table_WhiteAccountAndSetting
	private int whichTable;
	private List<Map<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablemain);

		initView();
		initTable();
	}

	

	private void initView() {
		listView = (ListView) findViewById(R.id.listView1);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		radioButton1 = (RadioButton) findViewById(R.id.radioT0);
		radioButton2 = (RadioButton) findViewById(R.id.radioT1);
		textView1 = (TextView) findViewById(R.id.textViewT1);
		textView2 = (TextView) findViewById(R.id.textViewT2);

		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(spnOnItemSelected);

		whichTable = 0;
	}
	
	private void initTable() {
		Table.initTable(getApplicationContext());
		tableHeath = new Table_HeathData_NeedToUpload(getApplicationContext());
		tableHRV = new Table_HRVFiveValue(getApplicationContext());
		tableNeed = new Table_NeedToUploadSyncFile(getApplicationContext());
		tableWhite = new Table_WhiteAccountAndSetting(getApplicationContext());
		
		changeView();
	}
	
	private AdapterView.OnItemSelectedListener spnOnItemSelected = new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
			whichTable = position;
			changeView();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
	};

	private void changeView() {
		switch (whichTable) {
		case 0:
			textView1.setText("weight unit:");
			textView2.setText("blood_sugar_unit");
			radioButton1.setText("weight unit");
			radioButton2.setText("blood_sugar_unit");
			editText1.setHint("INTEGER");
			editText2.setHint("INTEGER");
			break;
		case 1:
			textView1.setText("hf_acc:");
			textView2.setText("acc_code:");
			radioButton1.setText("hf_acc");
			radioButton2.setText("acc_code");
			editText1.setHint("VARCHAR");
			editText2.setHint("VARCHAR");
			break;
		case 2:
			textView1.setText("Sync_End:");
			textView2.setText("Need_Reupload:");
			radioButton1.setText("Sync_End");
			radioButton2.setText("Need_Reupload");
			editText1.setHint("INTEGER");
			editText2.setHint("INTEGER");
			break;
		case 3:
			textView1.setText("email:");
			textView2.setText("code:");
			radioButton1.setText("email");
			radioButton2.setText("code");
			editText1.setHint("VARCHAR");
			editText2.setHint("VARCHAR");
			break;
		}
		showAll();
	}
	
	private String getText1(){
		return editText1.getText().toString();
	}
	
	private String getText2(){
		return editText2.getText().toString();
	}
	
	private void showAll(){
		Cursor c = null;
		switch (whichTable) {
		case 0:
			c = tableHeath.getAllData();
			break;
		case 1:
			c = tableHRV.getAllData();
			break;
		case 2:
			c = tableNeed.getAllData();
			break;
		case 3:
			c = tableWhite.getAllData();
			break;
		}
		
		if (c == null)
			return;
		
		setListView(c);
	}
	
	private void setListView(Cursor c){
		if (c.getCount() == 0) {
			clearListView();
			return;
		}
		
		list = new ArrayList<Map<String, Object>>();
		do {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("text1", c.getString(0));
			item.put("text2", c.getString(1));
			item.put("text3", c.getString(2));
			list.add(item);
		} while (c.moveToNext());
		
		ListAdapter adapter = new SimpleAdapter(MainTable.this, list, R.layout.table_listview_item,
				new String[] { "text1", "text2", "text3" }, new int[] { R.id.textViewTA1, R.id.textViewTA2, R.id.textViewTA3 });

		listView.setAdapter(adapter);
	}
	
	private void clearListView() {
		list = new ArrayList<Map<String, Object>>();
		ListAdapter adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
		listView.setAdapter(adapter1);
	}

	/**********************************
	 * BUTTON CLICK
	 ***********************************/

	public void onInsertClickT(View v) {
		ContentValues values = new ContentValues();
		
		switch (whichTable) {
		case 0:
			values.put("updata_weight_unit", getText1());
			values.put("updata_blood_sugar_unit", getText2());
			tableHeath.insertData(values);
			break;
		case 1:
			values.put("hf_acc", getText1());
			values.put("hf_acc_code", getText2());
			tableHRV.insertData(values);
			break;
		case 2:
			values.put("upfile_Sync_End", getText1());
			values.put("upfile_Need_Reupload", getText2());
			tableNeed.insertData(values);
			break;
		case 3:
			values.put("o_email", getText1());
			values.put("o_code", getText2());
			tableWhite.insertData(values);
			break;
		}
		
		showAll();
	}

	public void onReadClickT(View v) {
		if(getText1().equals("") && getText2().equals("")){
			showAll();
			return;
		}
		
		Cursor c = null;
		
		if(radioGroup.getCheckedRadioButtonId() == R.id.radioT0){
			switch (whichTable) {
			case 0:
				c = tableHeath.readData(null, "updata_weight_unit", getText1());
				break;
			case 1:
				c = tableHRV.readData(null, "hf_acc", getText1());
				break;
			case 2:
				c = tableNeed.readData(null, "upfile_Sync_End", getText1());
				break;
			case 3:
				c = tableWhite.readData(null, "o_email", getText1());
				break;
			}
			
		}else{
			switch (whichTable) {
			case 0:
				c = tableHeath.readData(null, "updata_blood_sugar_unit", getText2());
				break;
			case 1:
				c = tableHRV.readData(null, "hf_acc_code", getText2());
				break;
			case 2:
				c = tableNeed.readData(null, "upfile_Need_Reupload", getText2());
				break;
			case 3:
				c = tableWhite.readData(null, "o_code", getText2());
				break;
			}
		}
		setListView(c);
	}

	public void onDeleteClickT(View v) {
		if(radioGroup.getCheckedRadioButtonId() == R.id.radioT0){
			switch (whichTable) {
			case 0:
				tableHeath.deleteData("updata_weight_unit",getText1());
				break;
			case 1:
				tableHRV.deleteData("hf_acc",getText1());
				break;
			case 2:
				tableNeed.deleteData("upfile_Sync_End",getText1());
				break;
			case 3:
				tableWhite.deleteData("o_email",getText1());
				break;
			}
		
		}else{
			switch (whichTable) {
			case 0:
				tableHeath.deleteData("updata_blood_sugar_unit", getText2());
				break;
			case 1:
				tableHRV.deleteData("hf_acc_code", getText2());
				break;
			case 2:
				tableNeed.deleteData("upfile_Need_Reupload", getText2());
				break;
			case 3:
				tableWhite.deleteData("o_code", getText2());
				break;
			}
		}
		showAll();
	}

	public void onUpdateClickT(View v) {
		if(getText1().equals("") && getText2().equals("")){
			Toast.makeText(getApplicationContext(), "please enter the text", Toast.LENGTH_SHORT).show();
			return;
		}
		
		ContentValues values = new ContentValues();
		
		if(radioGroup.getCheckedRadioButtonId() == R.id.radioT1){
			switch (whichTable) {
			case 0:
				values.put("updata_blood_sugar_unit",getText2());
				tableHeath.UpdataData(values,"updata_weight_unit", getText1());
				break;
			case 1:
				values.put("hf_acc_code",getText2());
				tableHRV.UpdataData(values, "hf_acc", getText1());
				break;
			case 2:
				values.put("upfile_Need_Reupload",getText2());
				tableNeed.UpdataData(values, "upfile_Sync_End", getText1());
				break;
			case 3:
				values.put("o_code",getText2());
				tableWhite.UpdataData(values, "o_email", getText1());
				break;
			}
			
		}else{
			switch (whichTable) {
			case 0:
				values.put("updata_weight_unit",getText1());
				tableHeath.UpdataData(values, "updata_blood_sugar_unit", getText2());
				break;
			case 1:
				values.put("hf_acc",getText1());
				tableHRV.UpdataData(values, "hf_acc_code", getText2());
				break;
			case 2:
				values.put("upfile_Sync_End",getText1());
				tableNeed.UpdataData(values, "upfile_Need_Reupload", getText2());
				break;
			case 3:
				values.put("o_email",getText1());
				tableWhite.UpdataData(values, "o_code", getText2());
				break;
			}
		}
		
		showAll();
	}

	public void onEmptyClickT(View v) {
		switch (whichTable) {
		case 0:
			tableHeath.emptyData();
			break;
		case 1:
			tableHRV.emptyData();
			break;
		case 2:
			tableNeed.emptyData();
			break;
		case 3:
			tableWhite.emptyData();
			break;
		}
		showAll();
	}
}

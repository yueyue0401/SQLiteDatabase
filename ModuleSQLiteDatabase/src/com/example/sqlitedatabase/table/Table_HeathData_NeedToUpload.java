package com.example.sqlitedatabase.table;

import android.content.Context;

// "HeathData" is a wrong word?
public class Table_HeathData_NeedToUpload extends Table {
	private String tableName = "Table_HealthData_NeedToUpload";
	private final String CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + tableName + "(" 
			+ "updata_index INTEGER PRIMARY KEY,"
			+ "updata_weight_unit INTEGER,"
			+ "updata_blood_sugar_unit INTEGER,"
			+ "updata_blood_sugar_secction INTEGER," // "secction" is a wrong word?
			+ "updata_Sync_End INTEGER,"
			+ "updata_head_measure_mode INTEGER," 
			+ "updata_cholesterol_unit INTEGER,"
			+ "updata_uric_acid_unit INTEGER,"
			+ "updata_temperature_type INTEGER,"
			+ "updata_spo2 INTEGER,"
			+ "updata_heart_rate INTEGER,"
			+ "updata_owner_account VARCHAR,"
			+ "updata_device_id VARCHAR,"
			+ "updata_product_id VARCHAR,"
			+ "updata_Last_Process_Time VARCHAR,"
			+ "updata_measure_time REAL,"
			+ "updata_timezone REAL,"
			+ "updata_h100_receive_time REAL,"
			+ "updata_user_id INTEGER,"
			+ "updata_type INTEGER,"
			+ "updata_weight REAL,"
			+ "updata_blood_systolic REAL,"
			+ "updata_blood_diastoloc REAL,"
			+ "updata_blood_heart REAL,"
			+ "updata_blood_sugar REAL,"
			+ "updata_temperature REAL,"
			+ "updata_uric_acid REAL,"
			+ "updata_cholesterol REAL"
			+ ")";

	public Table_HeathData_NeedToUpload(Context context) {
		CreatEntries = CREATE_ENTRIES;
		theTableName = tableName;
		addTable();
	}
}

package com.example.sqlitedatabase.table;

import android.content.Context;

public class Table_HRVFiveValue extends Table {
	private String tableName = "Table_HRVFiveValue";
	private final String CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + tableName + "(" 
			+ "hf_index INTEGER PRIMARY KEY,"
			+ "hf_acc VARCHAR,"
			+ "hf_acc_code VARCHAR,"
			+ "hf_date VARCHAR,"
			+ "hf_result_code VARCHAR,"
			+ "update_Last_Process_Time VARCHAR,"
			+ "hf_time INTEGER,"
			+ "hf_analyze_code INTEGER,"
			+ "hf_CELevel INTEGER,"
			+ "hf_age REAL,"
			+ "hf_RR_hr REAL,"
			+ "hf_SD_sd REAL,"
			+ "hf_LHR REAL,"
			+ "hf_LF_lf REAL,"
			+ "hf_HF_hf REAL,"
			+ "hf_vl REAL,"
			+ "hf_tp REAL,"
			+ "hf_SDNN REAL,"
			+ "hf_srv_index REAL,"
			+ "hf_measure_hr REAL"
			+ ")";	

	public Table_HRVFiveValue(Context context) {
		CreatEntries = CREATE_ENTRIES;
		theTableName = tableName;
		addTable();
	}
}

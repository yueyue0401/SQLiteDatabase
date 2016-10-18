package com.example.sqlitedatabase.table;

import android.content.Context;

public class Table_NeedToUploadSyncFile extends Table {
	private String tableName = "Table_NeedToUploadSyncFile";
	private final String CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + tableName + "(" 
			+ "upfile_index INTEGER PRIMARY KEY,"
			+ "upfile_Sync_End INTEGER,"
			+ "upfile_Need_Reupload INTEGER,"
			+ "upfile_Parse_End INTEGER,"
			+ "upfile_gender INTEGER,"
			+ "upfile_age INTEGER,"
			+ "upfile_owner_account VARCHAR,"
			+ "upfile_filePath VARCHAR,"
			+ "upfile_fileName VARCHAR,"
			+ "upfile_Last_Process_Time VARCHAR,"
			+ "upfile_BLE_MAC_Addr VARCHAR,"
			+ "upfile_TimeZone VARCHAR,"
			+ "upfile_Need_Receive_File_Length REAL,"
			+ "upfile_weight REAL,"
			+ "upfile_height REAL,"
			+ "upfile_averge_heat_rate REAL,"
			+ "upfile_target REAL"
			+ ")";
	
	public Table_NeedToUploadSyncFile(Context context) {
		CreatEntries = CREATE_ENTRIES;
		theTableName = tableName;
		addTable();
	}
}

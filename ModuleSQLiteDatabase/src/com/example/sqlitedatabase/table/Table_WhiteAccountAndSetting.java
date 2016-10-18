package com.example.sqlitedatabase.table;

import android.content.Context;

public class Table_WhiteAccountAndSetting extends Table {
	private String tableName = "Table_WhiteAccountAndSetting";
	private final String CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + tableName + "(" 
			+ "o_email VARCHAR,"
			+ "o_code VARCHAR,"
			+ "o_xmpp_password VARCHAR,"
			+ "o_displayname VARCHAR,"
			+ "o_birth VARCHAR,"
			+ "d_products_no VARCHAR,"
			+ "d_firmware_ver VARCHAR,"
			+ "d_item_display_order VARCHAR,"
			+ "d_BLE_MAC_Addr VARCHAR,"
			+ "d_ky207_version VARCHAR,"
			+ "o_weight INTEGER,"
			+ "o_height INTEGER,"
			+ "o_gender INTEGER,"
			+ "o_unit INTEGER,"
			+ "o_speed INTEGER,"
			+ "o_averge_heat_rate INTEGER," // "heat" is wrong word?
			+ "o_target INTEGER,"
			+ "o_have_device INTEGER,"
			+ "o_skip_ANS_desc INTEGER,"
			+ "d_display_line_number INTEGER,"
			+ "d_user_force_disconnect INTEGER,"
			+ "wl_need_upload INTEGER,"
			+ "o_alerthr_new INTEGER,"
			+ "o_have_Apodx_STI_device_new INTEGER,"
			+ "o_last_sync_data_time REAL,"
			+ "o_update_time_from_server REAL,"
			+ "o_avatarphoto BLOB"
			+ " )";
	
	public Table_WhiteAccountAndSetting(Context context) {
		CreatEntries = CREATE_ENTRIES;
		theTableName = tableName;
		addTable();
	}
}

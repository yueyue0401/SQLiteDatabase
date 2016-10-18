1.	Database.java: onCreate -> 建立table, onUpgrade->版本升級會調用.

2.	DatabaseActivity.java: database可用的method都在這裡, 他會去使用SQLiteDatabase的指令, 以及new database.java

	I.	DatabaseActivity(Context, String, int): 初始化
	II.	addTable(String): 將table新增指令加到ArrayList.
	III.	createTable(): 建立Table By ArrayList
	IV.	getCreateEntries(): 取得ArrayList, 給Database建立Table時調用
	V.	insertData(String, ContentValues): 新增資料
	VI.	readData(String, String[], String, String): return Cursor, Query資料
	VII.	deleteData(String, String, String): return int, 刪除資料
	VIII.	updateData(String, ContentValues, String, String): return int, 修改資料
	IX.	getAll(String): return Cursor, 取得全部資料
	X.	emptyTable(String): 清空table
	XI.	deleteTable(String): return int, 刪除資料

3.	Main.java: 模組化SQLiteOpenHelper的使用sample.

4.	MainTable.java: 模組化SQLiteOpenHelper及模組化table的使用sample.

5.	Table.java: 模組化table繼承此class, 裡面會去使用DatabaseActivity.java的method, 及table共用的method.

	I.	initTable(Context): 初始化databaseActivity.
	II.	addTable(): 將table新增指令加到ArrayList.
	III.	createTable(): 建立Table By ArrayList
	IV.	insertData(ContentValues): 新增資料
	V.	readData(String[], String, String): return Cursor, Query資料
	VI.	UpdataData(ContentValues, String, String): return int, 修改資料
	VII.	deleteData(String, String): return int, 刪除資料
	VIII.	emptyData(): 清空table
	IX.	getAllData(): return Cursor, 取得全部資料

6.	Table class extends Table:

	I.	Table_HeathData_NeedToUpload.java
	II.	Table_HRVFiveValue.java
	III.	Table_NeedToUploadSyncFile.java
	IV.	Table_WhiteAccountAndSetting.java

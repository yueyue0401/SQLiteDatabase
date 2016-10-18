package com.example.sqlitedatabase;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The class to operate SQLiteOpenHelper.
 * 
 * Available Method: #DatabaseActivity(Context, String, int),
 * #createTable(String), #getCreateEntries(), #insertData(String,
 * ContentValues), #readData(String, String[], String, String),
 * #deleteData(String, String, String), #updateData(String, ContentValues,
 * String, String), #getAll(String), #emptyTable(String), #deleteTable(String),
 *
 * @author 2055
 *
 */
public class DatabaseActivity {
	private static ArrayList<String> createEntries;
	private SQLiteDatabase sqliteDatabase;
	private Database database;

	/**
	 * Initial database.
	 * 
	 * @param context
	 *            the context of launching activity
	 * @param dbName
	 *            the name of database file (ex. myDatabase.db)
	 * @param dbVersion
	 *            the version of database
	 */
	public DatabaseActivity(Context context, String dbName, int dbVersion) {
		database = new Database(context, dbName, null, dbVersion);
		createEntries = new ArrayList<String>();
	}

	/**
	 * Add table entries into array.
	 * 
	 * @param createEntries
	 *            the entries of create table
	 */
	public void addTable(String createEntries) {
		DatabaseActivity.createEntries.add(createEntries);
	}

	/**
	 * Create table.
	 */
	public void createTable() {
		sqliteDatabase = database.getWritableDatabase();
	}

	/**
	 * Get the entries of create table.
	 * 
	 * @return entries array
	 */
	public static ArrayList<String> getCreateEntries() {
		return createEntries;
	}

	/**
	 * Inserting a row into the database.
	 * 
	 * @param tableName
	 *            the name of table to insert data
	 * @param values
	 *            this map contains the initial column values for the row. The
	 *            keys should be the column names and the values the column
	 *            values
	 */
	public void insertData(String tableName, ContentValues values) {
		sqliteDatabase = database.getWritableDatabase();
		sqliteDatabase.insert(tableName, null, values);
	}

	/**
	 * Search the data from the table
	 * 
	 * @param tableName
	 *            the name of the table to read
	 * @param columns
	 *            list of which columns to return. Passing null will return all
	 *            columns, which is discouraged to prevent reading data from
	 *            storage that isn't going to be used.
	 * @param columnName
	 *            the name of the column to search
	 * @param selectionArgs
	 *            the value to search
	 * @return the Cursor that have the matching values
	 */
	public Cursor readData(String tableName, String[] columns, String columnName, String selectionArgs) {
		sqliteDatabase = database.getReadableDatabase();
		Cursor cursor = sqliteDatabase.query(tableName, columns, columnName + "=?", new String[] { selectionArgs },
				null, null, null);
		cursor.moveToFirst();
		return cursor;
	}

	/**
	 * Delete the data in database.
	 * 
	 * @param tableName
	 *            the name of table to delete
	 * @param columnName
	 *            the name of column to search
	 * @param selectionArgs
	 *            the value of column to delete
	 */
	public int deleteData(String tableName, String columnName, String selectionArgs) {
		sqliteDatabase = database.getWritableDatabase();
		return sqliteDatabase.delete(tableName, columnName + " Like?", new String[] { selectionArgs });
	}

	/**
	 * Update data in the table.
	 * 
	 * @param tableName
	 *            the name of table to update
	 * @param values
	 *            the ContentValues to update
	 * @param columnName
	 *            the name of the column to search
	 * @param selectionArgs
	 *            the value of the column to update
	 * @return the amount of updated data
	 */
	public int updateData(String tableName, ContentValues values, String columnName, String selectionArgs) {
		sqliteDatabase = database.getWritableDatabase();
		return sqliteDatabase.update(tableName, values, columnName + "=?", new String[] { selectionArgs });
	}

	/**
	 * Get all data in the database.
	 * 
	 * @param tableName
	 *            the name of table to get
	 * @return the Cursor that have all data
	 */
	public Cursor getAll(String tableName) {
		sqliteDatabase = database.getReadableDatabase();
		Cursor cursor = sqliteDatabase.query(tableName, null, null, null, null, null, null);
		cursor.moveToFirst();
		return cursor;
	}

	/**
	 * Empty the table.
	 * 
	 * @param tableName
	 *            the name of table to empty
	 */
	public void emptyTable(String tableName) {
		sqliteDatabase = database.getWritableDatabase();
		sqliteDatabase.delete(tableName, null, null);
	}

	/**
	 * Delete the table in the database
	 * 
	 * @param tableName
	 *            the name of table to delete
	 */
	public void deleteTable(String tableName) {
		sqliteDatabase = database.getWritableDatabase();
		sqliteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
	}
}

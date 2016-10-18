package com.example.sqlitedatabase.table;

import com.example.sqlitedatabase.DatabaseActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The parent of all tables.
 * 
 * @author 2055
 *
 */
public class Table {
	private final static String databaseName = "databaseTable2.db";
	private final static int version = 2;

	protected String theTableName = "";
	protected String CreatEntries;

	private static DatabaseActivity databaseActivity;

	/**
	 * Initial the database and new a database object.
	 * 
	 * @param context
	 *            the context of launching activity
	 */
	public static void initTable(Context context) {
		databaseActivity = new DatabaseActivity(context, databaseName, version);
	}

	/**
	 * Add table entries into entries array.
	 */
	public void addTable() {
		databaseActivity.addTable(CreatEntries);
	}

	/**
	 * After adding all table entries, using this method to create the tables.
	 */
	public void createTable() {
		databaseActivity.createTable();
	}

	/**
	 * Inserting a row into the database.
	 * 
	 * @param values
	 *            this map contains the initial column values for the row. The
	 *            keys should be the column names and the values the column
	 *            values
	 */
	public void insertData(ContentValues values) {
		databaseActivity.insertData(theTableName, values);
	}

	/**
	 * Search the data from the table
	 * 
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
	public Cursor readData(String[] columns, String columnName, String selectionArgs) {
		Cursor c = databaseActivity.readData(theTableName, columns, columnName, selectionArgs);
		return c;
	}

	/**
	 * Update data in the table.
	 * 
	 * @param values
	 *            the ContentValues to update
	 * @param columnName
	 *            the name of the column to search
	 * @param selectionArgs
	 *            the value of the column to update
	 * @return the amount of updated data
	 */
	public int UpdataData(ContentValues values, String columnName, String selectionArgs) {
		int amountUpdate;
		amountUpdate = databaseActivity.updateData(theTableName, values, columnName, selectionArgs);
		return amountUpdate;
	}

	/**
	 * Delete the data in database.
	 * 
	 * @param columnName
	 *            the name of column to search
	 * @param selectionArgs
	 *            the value of column to delete
	 * 
	 * @return the amount of deleted data
	 */
	public int deleteData(String columnName, String selectionArgs) {
		int amountDelete;
		amountDelete = databaseActivity.deleteData(theTableName, columnName, selectionArgs);
		return amountDelete;
	}

	/**
	 * Empty the table.
	 */
	public void emptyData() {
		databaseActivity.emptyTable(theTableName);
	}

	/**
	 * Get all data in the database.
	 * 
	 * @return the Cursor that have all data
	 */
	public Cursor getAllData() {
		Cursor c = databaseActivity.getAll(theTableName);
		return c;
	}
}

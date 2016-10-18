package com.example.sqlitedatabase;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	private ArrayList<String> tableCommand;

	/**
	 * Create a database object. A helper class to manage database creation and
	 * version management.
	 * 
	 * @param context
	 *            to use to open or create the database
	 * 
	 * @param name
	 *            the name of database file
	 * @param factory
	 *            to use for creating cursor objects, or null for the default
	 * @param version
	 *            number of the database (starting at 1); if the database is
	 *            older, onUpgrade(SQLiteDatabase, int, int) will be used to
	 *            upgrade the database; if the database is newer,
	 *            onDowngrade(SQLiteDatabase, int, int) will be used to
	 *            downgrade the database
	 */
	public Database(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		tableCommand = DatabaseActivity.getCreateEntries();
		if (tableCommand.size() == 0)
			return;
		
		for(String command : tableCommand){
		db.execSQL(command);
		}
	}

	// if newVersion > oldVersion, this method will fire. Use this to upgrade
	// database.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

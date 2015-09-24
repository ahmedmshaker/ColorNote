package com.example.colornote;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ColorNoteDataBase extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 3;

	private static final String DATABASE_NAME = "mydatabase";

	private static final String TABLE_GALLERY = "gallery";
	Context con;


	public ColorNoteDataBase(Context context) {

	super(context, DATABASE_NAME, null, DATABASE_VERSION);

	// TODO Auto-generated constructor stub
	con=context;
	}

	 
	@Override
	public void onCreate(SQLiteDatabase db) {

	// TODO Auto-generated method stub

	// Create Table Name

	db.execSQL("CREATE TABLE " + TABLE_GALLERY +

	"(GalleryID INTEGER PRIMARY KEY AUTOINCREMENT," +

	"Name TEXT(100)," +

	"Note TEXT(100)," +
   "colorNote INTEGER,"+
	"NoteDate Date);");

	Log.d("CREATE TABLE","Create Table Successfully.");
	}  
	public void deleteNote(long id){
		try {
			SQLiteDatabase db;
			db=this.getWritableDatabase();
			db.delete(TABLE_GALLERY, "GalleryID="+id, null);
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long UpdateDatanote(long id,String name,String note,int color,String Notedate){
		try {
			ContentValues editcontact=new ContentValues();
			editcontact.put("Name", name);
			editcontact.put("Note", note);
			editcontact.put("colorNote", color);
			editcontact.put("NoteDate", getDateTime(Notedate));
			SQLiteDatabase db;
			db = this.getWritableDatabase();
			long getid=db.update(TABLE_GALLERY, editcontact, "GalleryID="+id, null);
			db.close();
			return getid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
		
	}


	public long InsertData(String strName, String note,int color,String Notedate) {

	// TODO Auto-generated method stub

	 

	try {

	SQLiteDatabase db;

	db = this.getWritableDatabase(); // Write Data


	 

	ContentValues Val = new ContentValues();



	Val.put("Name", strName);

	Val.put("Note", note);
	Val.put("colorNote", color);
	Val.put("NoteDate",getDateTime( Notedate));
	
	long rows = db.insert(TABLE_GALLERY, null, Val);

	db.close();

	return rows; // return rows inserted.

	 

	} catch (Exception e) {
		Log.d("faild", e.getMessage());

	return -1;

	}
	}

	public Cursor getItemsinItemTable(int id) {
		
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			String strSQL = "SELECT  * FROM " + TABLE_GALLERY + " WHERE  GalleryID= " + id ;
			return db.rawQuery(strSQL, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			
		

	}

	// Select All Data

	public String[][] SelectAllData() {

	// TODO Auto-generated method stub

	try {

	String arrData[][] = null; 

	SQLiteDatabase db;

	db = this.getReadableDatabase(); // Read Data

	String strSQL = "SELECT  * FROM " + TABLE_GALLERY;

	Cursor cursor = db.rawQuery(strSQL, null);

	if(cursor != null)

	{

	if (cursor.moveToFirst()) {

	arrData = new String[cursor.getCount()][cursor.getColumnCount()];

	/***
	095.
	*  [x][0] = GalleryID
	096.
	*  [x][1] = Name
	097.
	*  [x][2] = note
	*  
	098.
	*/
	int i= 0;

	do {               

	arrData[i][0] = cursor.getString(0);

	arrData[i][1] = cursor.getString(1);

	arrData[i][2] = cursor.getString(2);
	arrData[i][3] = cursor.getString(3);
	arrData[i][4] = cursor.getString(4);

	i++;

	} while (cursor.moveToNext());                     

	 

	}

	}

	cursor.close();

	return arrData;

	 

	} catch (Exception e) {

		return null;

	}
	}
	
	@Override

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	// TODO Auto-generated method stub

	db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);

	 

	// Re Create on method  onCreate

	onCreate(db);

	}
	private String getDateTime(String EVentDate) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.parse(EVentDate);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date = new Date();
		return format.format(date);
		}




}

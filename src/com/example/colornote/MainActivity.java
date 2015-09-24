package com.example.colornote;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity implements OnItemClickListener {
	GridView grid;
	GridViewAdapter myadapter;
	String[][] values;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		grid=(GridView) findViewById(R.id.gridView1);
		grid.setOnItemClickListener(this);
	
	}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	new mytask(this).execute((Object)null);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id=item.getItemId();
		if (id==R.id.action_settings) {
			Intent intent=new Intent(this, noteedit.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
class mytask extends AsyncTask<Object, Object, String[][]>{
	Context con;
	ColorNoteDataBase mydb;
   public mytask(Context c) {
	// TODO Auto-generated constructor stub
	   con=c;
	   mydb=new ColorNoteDataBase(con);
}
	@Override
	protected String[][] doInBackground(Object... params) {
		// TODO Auto-generated method stub
		values=mydb.SelectAllData(); 
		return mydb.SelectAllData();
	}
	@Override
	protected void onPostExecute(String[][] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (result!=null) {
			grid.setAdapter(new GridViewAdapter(con,result));
		}else{
			grid.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.gridview_item));
		}
		
	}
}
@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	Intent intent=new Intent(this, Show.class);
	Bundle b=new Bundle();
	b.putInt("id", Integer.parseInt(values[arg2][0]));
	b.putString("name", values[arg2][1]);
	b.putString("notname", values[arg2][2].toString());
	b.putString("colornote", values[arg2][3].toString());
	intent.putExtra("busket", b);
	

	startActivity(intent);
	
}
}

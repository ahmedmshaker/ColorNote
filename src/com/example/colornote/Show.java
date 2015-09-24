package com.example.colornote;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Show extends Activity {
EditText e1,e2;
ColorNoteDataBase mydb;
private Bundle busket;
String name,notename,colornote;
private int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		e1=(EditText) findViewById(R.id.editText1);
		e2=(EditText) findViewById(R.id.editText2);
		mydb=new ColorNoteDataBase(this);
		busket=getIntent().getBundleExtra("busket");
		if(busket!=null){
			id=busket.getInt("id");
			name=busket.getString("name");
			e1.setText(name);
			notename=busket.getString("notname");
			e2.setText(notename);
			colornote=busket.getString("colornote");
			e2.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(Integer.parseInt(colornote))));
}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show, menu);
		return true;
	}
 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	 int itemid=item.getItemId();
	 if (itemid==R.id.delete) {
		mydb.deleteNote(id);
		finish();
		return true;
	}else if (itemid==R.id.update) {
		Intent i=new Intent(this, noteedit.class);
		Bundle b=new Bundle();
		b.putInt("id", id);
		b.putString("name", name);
		b.putString("notname", notename);
		b.putString("colornote",colornote);
		i.putExtra("busket", b);
		startActivity(i);
		return true;
		
	}
		return super.onOptionsItemSelected(item);
	}

}

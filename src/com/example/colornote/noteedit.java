package com.example.colornote;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


public class noteedit extends Activity implements OnClickListener {
	SeekBar redSeekBar, greenSeekBar, blueSeekBar;
	EditText e1,e2;
	Button btn;
	private int mYear;
	private int mMonth;
	private int mDay;
	ColorNoteDataBase mydb;
	private Bundle busket;
	private int id;
	private String name;
	private String notename;
	private String colornote;
	int col;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noteedit);
		mydb=new ColorNoteDataBase(this);
		e1=(EditText) findViewById(R.id.editText1);
		e2=(EditText) findViewById(R.id.editText2);
		 redSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		    greenSeekBar = (SeekBar) findViewById(R.id.seekBar2);
		    blueSeekBar = (SeekBar) findViewById(R.id.seekBar3);
		    redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		    greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		    blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		    btn=(Button) findViewById(R.id.button1);
		    btn.setOnClickListener(this);
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
	private SeekBar.OnSeekBarChangeListener seekBarChangeListener
	= new SeekBar.OnSeekBarChangeListener()
	{

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
	  boolean fromUser) {
	// TODO Auto-generated method stub
		getColorFromSeekbars();
		//Toast.makeText(getBaseContext(), getColorFromSeekbars(), Toast.LENGTH_LONG).show();
		
	 
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	// TODO Auto-generated method stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	// TODO Auto-generated method stub
	}
	};
	private int seekR;
	private int seekG;
	private int seekB;

	
private void getColorFromSeekbars()
    { 
	seekR = redSeekBar.getProgress();
	 seekG = greenSeekBar.getProgress();
	 seekB = blueSeekBar.getProgress();
	 /* col=  0xff000000
			  + seekR * 0x10000
			  + seekG * 0x100
			  + seekB;*/
	 e2.setBackgroundColor(
	  0xff000000
	  + seekR * 0x10000
	  + seekG * 0x100
	  + seekB
	  );
      
    }
	@SuppressLint("NewApi")
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String name=e1.getText().toString();
		String note=e2.getText().toString();
		final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
       // Drawable m = e2.getBackground();
        ColorDrawable drawable = (ColorDrawable)e2.getBackground();
      col = drawable.getColor();
        System.out.println(col);
	    String Notedate=mYear+"/"+mMonth+"/"+mDay;
	        if (busket!=null) {
				mydb.UpdateDatanote(id, name, note, col, Notedate);
				Intent intent=new Intent(this, MainActivity.class);
				startActivity(intent);
			}else{
	        if(name.length()>0&&note.length()>0)
		mydb.InsertData(name, note, col, Notedate);
	        finish();
	        }
	}

}

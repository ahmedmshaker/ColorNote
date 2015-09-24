package com.example.colornote;


import android.content.Context;
import android.graphics.Color;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	LayoutInflater inflater;
	private String[][] lis;

	public GridViewAdapter(Context c,String[][] lists){
	context = c;
	lis=lists;
	
	inflater = (LayoutInflater) (context)
			.getSystemService(context.LAYOUT_INFLATER_SERVICE);}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lis.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	private class ViewHolder {
		private TextView txt1;
		private TextView txt2;
		private TextView txt3;
		private LinearLayout ll;

	}

	ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.gridview_item, parent,
					false);
			holder.txt1 = (TextView) convertView.findViewById(R.id.textView1);
			holder.txt2 = (TextView) convertView.findViewById(R.id.textView2);
			holder.txt3 = (TextView) convertView.findViewById(R.id.textView3);
			holder.ll = (LinearLayout) convertView.findViewById(R.id.linear);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.txt1.setText(lis[position][1].toString());
		holder.txt2.setText(lis[position][2].toString());
		//Log.d("col", "#"+Integer.toHexString(Integer.parseInt(lis[position][3].toString())));
		
		try {
			holder.ll.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(Integer.parseInt(lis[position][3].toString()))));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		holder.txt3.setText(lis[position][4].toString());
		
       
		return convertView;
	}

}

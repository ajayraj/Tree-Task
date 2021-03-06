package com.ghsoft.treetaskapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghsoft.treetask.R;
import com.ghsoft.treetask.TaskManager;
import com.ghsoft.treetask.TaskNode;

public class NewTreeView extends ActionBarActivity {

	private TaskNode task;

	@SuppressLint("SimpleDateFormat")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_tree_view);

		Object sTask = getIntent().getSerializableExtra("task");

		task = (TaskNode) sTask;

		TextView name = (TextView) findViewById(R.id.hname);
		TextView path = (TextView) findViewById(R.id.path);
		TextView description = (TextView) findViewById(R.id.hdescription);
		TextView timeStamp = (TextView) findViewById(R.id.htimestamp);
		LinearLayout headerBase = (LinearLayout) findViewById(R.id.header_base);
		
		if (task.getTimeStamp() != null) {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
			String timeString = df.format(task.getTimeStamp());
			timeStamp.setText(timeString);
		}

		TextView percent = (TextView) findViewById(R.id.percent);
		TreeTaskProgressBar completion = (TreeTaskProgressBar) findViewById(R.id.completion);
		completion.setMax(100);

		completion.setProgress(0);
		percent.setText(0 + "%");

		name.setText(task.getName());
		path.setText(task.getPath());
		description.setText(task.getDescription());
		
		if (task.getUseColor()) {
			getSupportActionBar().setBackgroundDrawable(new ColorDrawable(task.getColor()));
			headerBase.setBackgroundColor(task.getColor());
		}
		
		Button newTask = (Button)findViewById(R.id.newtask);
		newTask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(NewTreeView.this, NewTask.class);
				i.putExtra("task", task);
				finish();
				startActivity(i);
				overridePendingTransition(R.anim.slide_up, R.anim.short_zoom);

				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_tree_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case R.id.edit:

			i = new Intent(NewTreeView.this, EditTask.class);
			i.putExtra("task", task);
			finish();
			startActivity(i);
			overridePendingTransition(R.anim.slide_up, R.anim.short_zoom);
			break;

		case R.id.newTask:
			i = new Intent(NewTreeView.this, NewTask.class);
			i.putExtra("task", task);
			finish();
			startActivity(i);
			overridePendingTransition(R.anim.slide_up, R.anim.short_zoom);

			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public void onBackPressed() {

		TaskManager.save(task.getHead());
		Intent i = new Intent(NewTreeView.this, Main.class);
		if (task.completion() == 100)
			i.putExtra("page", 1);
		else
			i.putExtra("page", 0);
		finish();
		startActivity(i);
		overridePendingTransition(R.anim.back_slide, R.anim.back_zoom);

	}

}

package org.hitlabnz.helloglass;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.android.glass.app.Card;
import com.google.android.glass.timeline.TimelineManager;

public class MainActivity extends Activity {
	
	TextView coordTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set content view of the activity and get handle to the text view 
		setContentView(R.layout.activity_main);
		coordTextView = (TextView)this.findViewById(R.id.coordTextView);
		
		// insert a static card into timeline
		Card card = new Card(this);
		card.setText("Hello GDK!");
		card.setFootnote("From Hello Glass");
		
		TimelineManager tm = TimelineManager.from(this);
		tm.insert(card);
		
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			// print out the touch position
			coordTextView.setText(String.format("%.1f,  %.1f", event.getX(), event.getY()));
			break;
		}
		
		return super.onGenericMotionEvent(event);
	}
	
}

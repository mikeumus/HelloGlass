package org.hitlabnz.helloglass;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.mirror.Mirror;
import com.google.api.services.mirror.Mirror.Timeline;
import com.google.api.services.mirror.model.NotificationConfig;
import com.google.api.services.mirror.model.TimelineItem;
import com.google.glassware.AuthUtil;

@SuppressWarnings("serial")
public class HelloGlassServlet extends HttpServlet {
		
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		// get access to Mirror API
		Mirror mirror = getMirror(req);
		
		// get access to the timeline
		Timeline timeline = mirror.timeline();
		
		// create a timeline item (card)
		TimelineItem timelineItem = new TimelineItem()
			.setText( "Hello Glass!" )
			.setDisplayTime(new DateTime(new Date()))
			.setNotification(new NotificationConfig().setLevel("DEFAULT"));		

		// get an image to use with the card
		URL url = new URL("http://hello-glass-hitlabnz.appspot.com/static/hitlabnz.jpg");
		
		// insert the card into the timeline
		timeline.insert(timelineItem, new InputStreamContent("image/jpeg", url.openStream())).execute();
		
		// print out results on the web browser
		resp.setContentType("text/html; charset=utf-8");
		resp.getWriter().println(
				"<html><head><meta http-equiv=\"refresh\" content=\"3;url=/index.html\"></head> " +
				"<body>Hello Glass! card inserted to timeline.<br></body></html>" );
	}
	
	private Mirror getMirror(HttpServletRequest req) throws IOException {
		// get credential
		Credential credential = AuthUtil.getCredential(req);
		
		// build access to Mirror API
		return new Mirror.Builder( 
				new UrlFetchTransport(), new JacksonFactory(), credential)
					.setApplicationName("Hello Glass").build();
	}
}

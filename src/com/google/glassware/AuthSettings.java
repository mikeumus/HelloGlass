package com.google.glassware;

import java.util.Arrays;
import java.util.List;

public class AuthSettings {
	public static String CLIENT_ID = "555615950876-14k9b9sggrtpulhu9s72le4vsejjscak.apps.googleusercontent.com";
	public static String CLIENT_SECRET = "lo9hajhpQFneXP5K8YR0gEVN";
	
	public static final List<String> GLASS_SCOPE = 
			  Arrays.asList("https://www.googleapis.com/auth/glass.timeline",
					  	"https://www.googleapis.com/auth/glass.location",
					  	"https://www.googleapis.com/auth/userinfo.profile");
}

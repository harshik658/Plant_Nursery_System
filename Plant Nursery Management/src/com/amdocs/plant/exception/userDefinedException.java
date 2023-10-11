package com.amdocs.plant.exception;

public class userDefinedException extends Exception {
	String alert;
	public userDefinedException(String str) {
		alert = str;
	}
	public String toString() {
		return ("Something went wrong "+ alert);
	}
}

package com.Atef.gestionstock.service;

import java.io.InputStream;

import com.flickr4java.flickr.FlickrException;

public interface FlickrService {
	
	String savePhoto(InputStream photo , String title ) throws FlickrException;

}

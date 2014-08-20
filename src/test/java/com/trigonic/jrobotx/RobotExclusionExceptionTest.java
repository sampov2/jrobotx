package com.trigonic.jrobotx;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import com.trigonic.jrobotx.util.URLInputStreamFactory;

public class RobotExclusionExceptionTest
{
	IOException exceptionToThrow;
	URLInputStreamFactory mockURLInputStreamFactory;
	URL testURL;
	String testUserAgent;
	
	@Before
	public void setUp() throws Exception
	{
		testURL = new URL("http://www.google.com");
		testUserAgent = "Agent Orange";
		mockURLInputStreamFactory = new URLInputStreamFactory() {
			
			public InputStream openStream(URL url) throws IOException
			{
				throw exceptionToThrow;
			}
		};
	}

	/**
	 * Crawling is allowed when the URLInputStreamFactory throws an FileNotFoundException 
	 */
	@Test
	public void testFileNotFoundException()
	{
		RobotExclusion exclusion = new RobotExclusion(mockURLInputStreamFactory);
		
		exceptionToThrow = new FileNotFoundException();
		
		assertTrue(exclusion.allows(testURL, testUserAgent, false));
		assertTrue(exclusion.allows(testURL, testUserAgent, true));
	}


	/**
	 * Default values passed to the allows() method should be respected when a generic
	 * IOException is received from the URLInputStreamFactory
	 */
	@Test
	public void testGenericIOException()
	{
		RobotExclusion exclusion = new RobotExclusion(mockURLInputStreamFactory);
		
		exceptionToThrow = new IOException();
		
		assertFalse(exclusion.allows(testURL, testUserAgent, false));
		assertTrue(exclusion.allows(testURL, testUserAgent, true));
	}

}

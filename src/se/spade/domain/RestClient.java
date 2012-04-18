package se.spade.domain;

import java.io.IOException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

public interface RestClient {
	
	String get(URL url) throws ClientProtocolException, IOException;

}

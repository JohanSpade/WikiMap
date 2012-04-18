package se.spade.infrastructure;

import java.io.IOException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApacheRestClient implements RestClient{

	private final HttpClient httpClient;

	public ApacheRestClient() {
		this.httpClient = new DefaultHttpClient();
	}

	@Override
	public String get(URL url) throws ClientProtocolException, IOException {
		try {
			return httpClient().execute(new HttpGet(url.toExternalForm()), new BasicResponseHandler());
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	HttpClient httpClient(){
		return httpClient;
	}
}
package se.spade.infrastructure;

import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import se.spade.domain.RestClient;
import se.spade.domain.exception.RestClientException;

public class ApacheRestClient implements RestClient{

	private final HttpClient httpClient;

	public ApacheRestClient() {
		this.httpClient = new DefaultHttpClient();
	}

	@Override
	public String get(URL url) {
		try {
			return httpClient().execute(new HttpGet(url.toExternalForm()), new BasicResponseHandler());
		}
		catch(Exception e){
			throw new RestClientException("Error executing http GET to URL ".concat(url.toExternalForm()), e);
		}		
		finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	HttpClient httpClient(){
		return httpClient;
	}
}
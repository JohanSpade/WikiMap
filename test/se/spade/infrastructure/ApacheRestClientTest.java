package se.spade.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import se.spade.domain.RestClient;

public class ApacheRestClientTest {
	
	@Mock
	private HttpClient httpClient;
	
	private RestClient restClient = new ApacheRestClientSeam();
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnPageAsString() throws ClientProtocolException, IOException{
		givenHttpClient();
		
		String response = whenClientIsCalled();

		thenHttpClientIsCalledWithCorrectUrl();

		thenResponseIsValid(response);
	}

	private String whenClientIsCalled() throws ClientProtocolException,
			IOException, MalformedURLException {
		String response = restClient.get(new URL("http://www.valid.url.com"));
		return response;
	}

	@SuppressWarnings("unchecked")
	private void thenHttpClientIsCalledWithCorrectUrl() throws IOException,
			ClientProtocolException {
		ArgumentCaptor<HttpGet> argument = ArgumentCaptor.forClass(HttpGet.class);
		verify(httpClient).execute(argument.capture(), any(ResponseHandler.class));
		assertEquals("http://www.valid.url.com", argument.getValue().getURI().toString());
	}

	private void thenResponseIsValid(String response) {
		assertEquals("<html>validHTML</html>", response);
	}

	@SuppressWarnings("unchecked")
	private void givenHttpClient() throws IOException, ClientProtocolException {
		when(httpClient.execute(any(HttpGet.class), any(ResponseHandler.class))).thenReturn("<html>validHTML</html>");
	}

	private class ApacheRestClientSeam extends ApacheRestClient{
		@Override
		HttpClient httpClient(){
			return httpClient;
		}
	}
}

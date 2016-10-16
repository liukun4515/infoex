package com.thu.database.infoex.util;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liukun
 *
 */
public class HttpMethod {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpMethod.class);

	private static HttpClient httpClient = HttpClients.createDefault();
	private static HttpGet httpGet = new HttpGet();
	private static int index = 1;
	private final static String charset = "UTF-8";

	public static String doGet(String url) {
		String result = null;
		HttpEntity httpEntity = null;
		URI uri = URI.create(url);
		httpGet.setURI(uri);

		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			httpResponse.getEntity().getContentType();
			if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				try {
					LOGGER.error(
							"The responde state is not 200, which is " + httpResponse.getStatusLine().getStatusCode());
					throw new HttpException(
							"The responde state is not 200, which is " + httpResponse.getStatusLine().getStatusCode());
				} catch (HttpException e) {
					e.printStackTrace();
				}
			}
			httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity, charset);
			LOGGER.info("The entity length is " + httpEntity.getContentLength() + " | the encoding is "
					+ httpEntity.getContentType());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Execute http get method : " + index);
		index++;
		return result;
	}

}

package br.com.softctrl.http.rest;

import static br.com.softctrl.http.util.StreamUtils.inputStreamToString;
import java.io.InputStream;

import br.com.softctrl.http.rest.listener.RequestFinishedListener;
import br.com.softctrl.http.rest.listener.ResponseErrorListener;
import br.com.softctrl.http.rest.listener.ResponseListener;

/*
The MIT License (MIT)

Copyright (c) 2015 Carlos Timoshenko Rodrigues Lopes
http://www.0x09.com.br

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 */
public final class StringHTTPRestfulClient extends AbstractHTTPRestfulClient<String, String> {

	/**
	 * @param responseListener
	 * @param responseErrorListener
	 * @param requestFinishedListener
	 */
	public StringHTTPRestfulClient(ResponseListener<String> responseListener,
			ResponseErrorListener responseErrorListener, RequestFinishedListener<String> requestFinishedListener) {
		super(responseListener, responseErrorListener, requestFinishedListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.http.rest.BasicHTTPRestClient#createRequest(br.com.
	 * softctrl. rest.HttpMethod, java.lang.String, java.lang.Object,
	 * br.com.softctrl.http.rest.Parameter[])
	 */
	@Override
	protected Request<String, String> createRequest(HttpMethod httpMethod, String url, String body,
			Parameter... parameters) {
		final Request<String, String> request = new Request<String, String>(httpMethod, url, body) {
			@Override
			public Response<String> parseResponse(int statusCode, InputStream result) {
				return new Response<String>(statusCode, inputStreamToString(result));
			}

			@Override
			public byte[] bodyToByteArray() {
				return (getBody() + "").getBytes();
			}
		};
		if (parameters != null && parameters.length > 0) {
			for (Parameter parameter : parameters) {
				request.addParameter(parameter);
			}
		}
		return request;
	}

}

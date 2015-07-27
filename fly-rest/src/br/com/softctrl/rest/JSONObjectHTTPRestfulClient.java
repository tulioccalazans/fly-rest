/**
 * 
 */
package br.com.softctrl.rest;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.softctrl.rest.listener.RequestFinishedListener;
import br.com.softctrl.rest.listener.ResponseErrorListener;
import br.com.softctrl.rest.listener.ResponseListener;

/**
 * @author timoshenko
 *
 */
public final class JSONObjectHTTPRestfulClient extends AbstractHTTPRestfulClient<JSONObject> {

	/**
	 * @param responseListener
	 * @param responseErrorListener
	 * @param requestFinishedListener
	 */
	public JSONObjectHTTPRestfulClient(ResponseListener<JSONObject> responseListener,
			ResponseErrorListener responseErrorListener, RequestFinishedListener<JSONObject> requestFinishedListener) {
		super(responseListener, responseErrorListener, requestFinishedListener);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.rest.AbstractHTTPRestfulClient#createRequest(br.com.
	 * softctrl.rest.HttpMethod, java.lang.String, java.lang.Object,
	 * br.com.softctrl.rest.Parameter[])
	 */
	@Override
	protected Request<JSONObject> createRequest(HttpMethod httpMethod, String url, JSONObject body,
			Parameter... parameters) {
		final Request<JSONObject> request = new Request<JSONObject>(httpMethod, url, body) {
			@Override
			public Response<JSONObject> parseResponse(int statusCode, String result) {
				return new Response<JSONObject>(statusCode, new JSONObject(result));
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
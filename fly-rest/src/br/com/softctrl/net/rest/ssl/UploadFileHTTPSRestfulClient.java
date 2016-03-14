package br.com.softctrl.net.rest.ssl;

import static br.com.softctrl.net.util.StreamUtils.streamToByteArray;
import static br.com.softctrl.net.util.StreamUtils.streamToString;

import java.io.InputStream;

import br.com.softctrl.net.rest.HttpMethod;
import br.com.softctrl.net.rest.Parameter;
import br.com.softctrl.net.rest.Request;
import br.com.softctrl.net.rest.Response;
import br.com.softctrl.net.rest.listener.RequestFinishedListener;
import br.com.softctrl.net.rest.listener.ResponseErrorListener;
import br.com.softctrl.net.rest.listener.ResponseListener;

/*
The MIT License (MIT)

Copyright (c) 2016 Carlos Timoshenko Rodrigues Lopes
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
public class UploadFileHTTPSRestfulClient extends AbstractHTTPSRestfulClient<InputStream, String> {
    
    /**
     * 
     */
    public UploadFileHTTPSRestfulClient() {
        super();
    }

    /**
     * @param responseListener
     * @param responseErrorListener
     * @param requestFinishedListener
     */
    public UploadFileHTTPSRestfulClient(ResponseListener<String> responseListener,
            ResponseErrorListener responseErrorListener, RequestFinishedListener<String> requestFinishedListener) {
        super(responseListener, responseErrorListener, requestFinishedListener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.com.softctrl.http.rest.AbstractHTTPRestfulClient#createRequest(br.com.
     * softctrl.http.rest.HttpMethod, java.lang.String, java.lang.Object,
     * br.com.softctrl.http.rest.Parameter[])
     */
    @Override
    protected Request<InputStream, String> createRequest(HttpMethod httpMethod, String url, InputStream body,
            Parameter... parameters) {

        final Request<InputStream, String> request = new Request<InputStream, String>(httpMethod, url, body) {
            @Override
            public Response<String> parseResponse(int statusCode, InputStream result) {
                String _result = streamToString(result);
                return new Response<String>(statusCode, _result);
            }

            @Override
            public byte[] bodyToByteArray() {
                return streamToByteArray(getBody());
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
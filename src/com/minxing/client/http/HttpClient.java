package com.minxing.client.http;

import com.minxing.client.model.MxException;
import com.minxing.client.model.MySSLSocketFactory;
import com.minxing.client.model.PostParameter;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HttpClient implements java.io.Serializable {

    private static final long serialVersionUID = -176092625883595547L;
    private static final int OK = 200;// OK: Success!
    private static final int CREATED = 201;
    private static final int NOT_MODIFIED = 304;// Not Modified: There was no
    // new data to return.
    private static final int BAD_REQUEST = 400;// Bad Request: The request was
    // invalid. An accompanying
    // error message will explain
    // why. This is the status code
    // will be returned during rate
    // limiting.
    private static final int NOT_AUTHORIZED = 401;// Not Authorized:
    // Authentication
    // credentials were missing
    // or incorrect.
    private static final int FORBIDDEN = 403;// Forbidden: The request is
    // understood, but it has been
    // refused. An accompanying
    // error message will explain
    // why.
    private static final int NOT_FOUND = 404;// Not Found: The URI requested is
    // invalid or the resource
    // requested, such as a user,
    // does not exists.
    private static final int NOT_ACCEPTABLE = 406;// Not Acceptable: Returned by
    // the Search API when an
    // invalid format is
    // specified in the request.
    private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server
    // Error: Something
    // is broken.
    private static final int BAD_GATEWAY = 502;// Bad Gateway.
    private static final int SERVICE_UNAVAILABLE = 503;// Service Unavailable:
    // The servers are up,
    // but overloaded with
    // requests. Try again
    // later. The search and
    // trend methods use
    // this to indicate when
    // you are being rate
    // limited.

    private String token;
    private String tokenType;

    public String setToken(String token) {
        this.token = token;
        return this.token;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    private final static boolean DEBUG = true;

    // org.apache.commons.httpclient.HttpClient client = null;

    public HttpClient() {
        // this(150, 30000, 30000, 1024 * 1024);
    }

    public HttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs,
                      int maxSize) {
        // httpclient 以后是每次请求都创建一个 为了防止并发问题 效率是低了 但是不会出问题了
        // org.apache.commons.httpclient.HttpClient client =
        // createHttpClient(maxConPerHost, conTimeOutMs, soTimeOutMs,
        // maxSize);

    }

    private org.apache.commons.httpclient.HttpClient createHttpClient(
            int maxConPerHost, int conTimeOutMs, int soTimeOutMs, int maxSize) {
        // MultiThreadedHttpConnectionManager connectionManager = new
        // MultiThreadedHttpConnectionManager();
        // SimpleHttpConnectionManager connectionManager = new
        // SimpleHttpConnectionManager(
        // true);
        // HttpConnectionManagerParams params = connectionManager.getParams();
        // params.setDefaultMaxConnectionsPerHost(maxConPerHost);
        // params.setConnectionTimeout(conTimeOutMs);
        // params.setSoTimeout(soTimeOutMs);

        HttpClientParams clientParams = new HttpClientParams();
        clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient(
                clientParams);
        Protocol myhttps = new Protocol("https", new MySSLSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);
        return client;
    }

    public Response get0(String url, PostParameter[] headers)
            throws MxException {

        GetMethod getmethod = new GetMethod(url);
        return httpRequest(getmethod, headers);

    }

    public InputStream get1(String url, PostParameter[] headerParameters,
                            GetMethod method) throws MxException {
        boolean getIsNUll = false;
        if (method == null) {
            method = new GetMethod(url);
            getIsNUll = true;
        }
        InetAddress ipaddr;
        int responseCode = -1;
        try {

            List<Header> headers = new ArrayList<Header>();

            if (headerParameters != null) {
                for (int i = 0; i < headerParameters.length; i++) {
                    headers.add(new Header(headerParameters[i].getName(),
                            headerParameters[i].getValue()));
                }
            }

            if (tokenType == null || tokenType.trim().equals("")) {
                tokenType = "Bearer";
            }
            if (token == null) {
                throw new IllegalStateException("Oauth2 token is not set!");
            }
            headers.add(new Header("Authorization", tokenType + " " + token));
            try {
                ipaddr = InetAddress.getLocalHost();
                headers.add(new Header("API-RemoteIP", ipaddr.getHostAddress()));
            } catch (Exception e) {
                throw new MxException(
                        "InetAddress.getLocalHost error,check server's hosts and hostname",
                        e);
            }

            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler(0, false));
            for (Header h : headers) {
                method.setRequestHeader(h);
            }
            org.apache.commons.httpclient.HttpClient client = this
                    .createHttpClient(150, 30000, 30000, 1024 * 1024);
            // long t0 = System.currentTimeMillis();
            client.executeMethod(method);
            // long t1 = System.currentTimeMillis();
            responseCode = method.getStatusCode();

            // response.setResponseAsString(this.getResponseBodyAsString(method));
            // response.setStatusCode(responseCode);

            if (responseCode >= 400) {

                if (responseCode == 405) {
                    throw new MxException("HTTP " + method.getStatusCode()
                            + ": Method Not Allowed", method.getStatusCode());
                }

                throw new MxException("HTTP " + method.getStatusCode() + ": "
                        + this.getResponseBodyAsString(method),
                        method.getStatusCode());

            }

            InputStream in = method.getResponseBodyAsStream();
            if (getIsNUll == true) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1204 * 96];
                int read = 0;

                while ((read = in.read(buf)) != -1) {
                    bos.write(buf, 0, read);
                }
                return new ByteArrayInputStream(bos.toByteArray());
            } else {
                return in;
            }

        } catch (Throwable ioe) {
            throw new MxException(ioe.getMessage(), ioe, responseCode);
        } finally {
            if (getIsNUll) {
                method.releaseConnection();
            }
            // client.getHttpConnectionManager().closeIdleConnections(0);
        }

    }

    public boolean get2(String url, PostParameter[] headerParameters, File f)
            throws MxException {
        GetMethod method = new GetMethod(url);
        InputStream in = get1(url, headerParameters, method);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(f);
            byte[] buf = new byte[1024 * 512];
            int read = 0;

            while ((read = in.read(buf)) != -1) {
                out.write(buf, 0, read);
                out.flush();
            }
            return true;
        } catch (FileNotFoundException e) {
            throw new MxException(e.getMessage(), e);
        } catch (IOException e) {
            throw new MxException(e.getMessage(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new MxException(e.getMessage(), e);
                }
            }
            method.releaseConnection();
        }
        // return false;

    }

    public Response post(String url, PostParameter[] params,
                         PostParameter[] headers, Boolean WithTokenHeader)
            throws MxException {

        return post(url, params, WithTokenHeader, headers);

    }

    public Response post(String url, PostParameter[] params,
                         PostParameter[] headers, File file, Boolean WithTokenHeader)
            throws MxException {
        return post(url, params, WithTokenHeader, file, headers);

    }

    public Response post(String url, PostParameter[] params,
                         Boolean WithTokenHeader, PostParameter[] headers)
            throws MxException {

        PostMethod postMethod = new PostMethod(url);
        for (int i = 0; i < params.length; i++) {
            String pValue = params[i].getValue();
            if (pValue != null) {
                postMethod.addParameter(params[i].getName(),
                        params[i].getValue());
            }
        }
        HttpMethodParams param = postMethod.getParams();
        param.setContentCharset("UTF-8");
        if (WithTokenHeader) {

            return httpRequest(postMethod, headers);
        } else {

            return httpRequest(postMethod, WithTokenHeader, headers, null);
        }
    }

    public Response post(String url, PostParameter[] params,
                         Boolean WithTokenHeader, File file, PostParameter[] headers)
            throws MxException {

        PostMethod postMethod = new PostMethod(url);
        if (file != null && file.exists()) {
            Part[] parts = new Part[params.length + 1];
            for (int i = 0; i < params.length; i++) {
                parts[i] = new StringPart(params[i].getName(),
                        params[i].getValue());
            }
            // FilePart：用来上传文件的类
            FilePart fp;
            try {
                fp = new FilePart("[uploading][]data", file);
            } catch (FileNotFoundException e) {
                throw new MxException(e);
            }
            parts[params.length] = fp;

            MultipartRequestEntity mre = new MultipartRequestEntity(parts,
                    postMethod.getParams());
            postMethod.setRequestEntity(mre);
        } else {
            for (int i = 0; i < params.length; i++) {
                postMethod.addParameter(params[i].getName(),
                        params[i].getValue());
            }
        }
        HttpMethodParams param = postMethod.getParams();
        param.setContentCharset("UTF-8");

        return httpRequest(postMethod, WithTokenHeader, headers, file);

    }

    public String post(String url, String params, PostParameter[] headers, boolean withTokenHeader) throws Exception {
        PostMethod postMethod = new PostMethod(url);
//		System.out.println(params);
//		RequestEntity entity = new StringRequestEntity(params);
        RequestEntity entity = new InputStreamRequestEntity(new ByteArrayInputStream(params.getBytes()), "application/json");
        postMethod.setRequestEntity(entity);
        Response response = httpRequest(postMethod, withTokenHeader, headers, null);
        System.out.println("httpcode>>>" + response.getStatusCode() + "; body>>>" + response.getResponseAsString());
        return response.getResponseAsString();
    }

    public Response put(String url, PostParameter[] params,
                        PostParameter[] headers, Boolean WithTokenHeader)
            throws MxException {

        PostParameter[] temp = new PostParameter[params.length + 1];
        System.arraycopy(params, 0, temp, 0, params.length);
        temp[params.length] = new PostParameter("_method", "PUT");
        return post(url, temp, headers, WithTokenHeader);
    }

    public Response delete(String url, PostParameter[] params,
                           PostParameter[] headers) throws MxException {
        if (0 != params.length) {
            String encodedParams = HttpClient.encodeParameters(params);
            if (-1 == url.indexOf("?")) {
                url += "?" + encodedParams;
            } else {
                url += "&" + encodedParams;
            }
        }
        DeleteMethod deleteMethod = new DeleteMethod(url);
        return httpRequest(deleteMethod, headers);
    }

    public Response httpRequest(HttpMethod method,
                                PostParameter[] headerParameters) throws MxException {
        return httpRequest(method, true, headerParameters, null);
    }

    static Logger log = Logger.getLogger(HttpClient.class.getSimpleName());

    public Response httpRequest(HttpMethod method, Boolean WithTokenHeader,
                                PostParameter[] headerParameters, File file) throws MxException {

        InetAddress ipaddr;
        int responseCode = -1;
        try {

            List<Header> headers = new ArrayList<Header>();

            if (headerParameters != null) {
                for (int i = 0; i < headerParameters.length; i++) {
                    headers.add(new Header(headerParameters[i].getName(),
                            headerParameters[i].getValue()));
                }
            }

            if (WithTokenHeader) {
                if (tokenType == null || tokenType.trim().equals("")) {
                    tokenType = "Bearer";
                }

                if (token == null) {
                    throw new IllegalStateException("Oauth2 token is not set!");
                }
                headers.add(new Header("Authorization", tokenType + " " + token));

//				try {
//					ipaddr = InetAddress.getLocalHost();
//					headers.add(new Header("API-RemoteIP", ipaddr
//							.getHostAddress()));
//				} catch (Exception e) {
//					throw new MxException(
//							"InetAddress.getLocalHost error,check server's hosts and hostname",
//							e);
//				}

                // client.getHostConfiguration().getParams()
                // .setParameter("http.default-headers", headers);
            }

            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler(0, false));
            for (Header h : headers) {
                method.setRequestHeader(h);
            }

            org.apache.commons.httpclient.HttpClient client = this
                    .createHttpClient(150, 30000, 30000, 1024 * 1024);
            client.executeMethod(method);


            Header content_type = method.getResponseHeader("Content-Type");
            responseCode = method.getStatusCode();

            Response response = new Response();


            response.setResponseAsString(this.getResponseBodyAsString(method));
            response.setStatusCode(responseCode);


            if (responseCode >= 400) {
                if (responseCode == 405) {
                    throw new MxException("HTTP " + method.getStatusCode()
                            + ": Method Not Allowed", method.getStatusCode());
                }

                if (content_type != null
                        && "application/json".equals(content_type.getValue())) {
                    throw new MxException(getCause(responseCode),
                            response.asJSONObject(), method.getStatusCode());
                } else {
//					System.exit(0);
					/*throw new MxException("HTTP " + method.getStatusCode()
							+ ": " + response.getResponseAsString(),
							method.getStatusCode());*/
                }

            }
            return response;

        } catch (Throwable ioe) {
            throw new MxException(ioe.getMessage(), ioe, responseCode);
        } finally {
            method.releaseConnection();
            // client.getHttpConnectionManager().closeIdleConnections(0);
        }

    }

    private String getResponseBodyAsString(HttpMethod method)
            throws IOException {
        InputStream resStream = method.getResponseBodyAsStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[8 * 1024];

        int read_count = resStream.read(buffer);
        do {

            out.write(buffer, 0, read_count);
            read_count = resStream.read(buffer);
        } while (read_count != -1);

        byte[] read_bytes = out.toByteArray();

        return new String(read_bytes, "UTF-8");

        // BufferedReader br = new BufferedReader(new
        // InputStreamReader(resStream));
        // StringBuffer resBuffer = new StringBuffer();
        // String resTemp = "";
        // while ((resTemp = br.readLine()) != null) {
        // resBuffer.append(resTemp);
        // }
        // String response = resBuffer.toString();
        // return response;
    }

    public static String encodeParameters(PostParameter[] postParams) {
        StringBuffer buf = new StringBuffer();
        for (int j = 0; j < postParams.length; j++) {

            if (postParams[j].getValue() == null) {
                continue;
            }

            if (j != 0) {
                buf.append("&");
            }

            try {
                buf.append(URLEncoder.encode(postParams[j].getName(), "UTF-8"))
                        .append("=")
                        .append(URLEncoder.encode(postParams[j].getValue(),
                                "UTF-8"));
            } catch (java.io.UnsupportedEncodingException neverHappen) {
            }
        }
        return buf.toString();
    }

    private static String getCause(int statusCode) {
        String cause = null;
        switch (statusCode) {
            case NOT_MODIFIED:
                break;
            case BAD_REQUEST:
                cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
                break;
            case NOT_AUTHORIZED:
                cause = "Authentication credentials were missing or incorrect.";
                break;
            case FORBIDDEN:
                cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
                break;
            case NOT_FOUND:
                cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
                break;
            case NOT_ACCEPTABLE:
                cause = "Returned by the Search API when an invalid format is specified in the request.";
                break;
            case INTERNAL_SERVER_ERROR:
                cause = "Something is broken.";
                break;
            case BAD_GATEWAY:
                cause = "app is down or being upgraded.";
                break;
            case SERVICE_UNAVAILABLE:
                cause = "Service Unavailable: The servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
                break;
            default:
                cause = "";
        }
        return statusCode + ":" + cause;
    }

    public static void main(String[] s) {
        HttpClient c = new HttpClient();
        PostParameter[] p = new PostParameter[0];
        InputStream in = c
                .get1("http://192.168.100.230:8030/files/3587/ace6d35d40d5cbd623913c09fdd4fdf2",
                        p, null);
    }


}

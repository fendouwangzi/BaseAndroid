package com.zzg.baseandroid.net;

import android.text.TextUtils;

import com.zzg.baseandroid.util.LogUtils;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by zhongzhigang on 2016/6/3.
 */
public class LoggerInterceptor implements Interceptor
{
    public static final String TAG = "OkHttpUtils";
    private String tag;
    private boolean showResponse;

    public LoggerInterceptor(String tag, boolean showResponse)
    {
        if (TextUtils.isEmpty(tag))
        {
            tag = TAG;
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    public LoggerInterceptor(String tag)
    {
        this(tag, false);
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response);
    }

    private Response logForResponse(Response response)
    {
        try
        {
            //===>response log
             LogUtils.d(tag, "========response'log=======");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
             LogUtils.d(tag, "url : " + clone.request().url());
             LogUtils.d(tag, "code : " + clone.code());
             LogUtils.d(tag, "protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message()))
                 LogUtils.d(tag, "message : " + clone.message());

            if (showResponse)
            {
                ResponseBody body = clone.body();
                if (body != null)
                {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null)
                    {
                         LogUtils.d(tag, "responseBody's contentType : " + mediaType.toString());
                        if (isText(mediaType))
                        {
                            String resp = body.string();
                            LogUtils.d(TAG, "logForResponse: ----------responseBody's content");
                            LogUtils.json(tag,  resp);

                            body = ResponseBody.create(mediaType, resp);
                            return response.newBuilder().body(body).build();
                        } else
                        {
                            LogUtils.e(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                        }
                    }
                }
            }

             LogUtils.d(tag, "========response'log=======end");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return response;
    }

    private void logForRequest(Request request)
    {
        try
        {
            String url = request.url().toString();
            Headers headers = request.headers();

            LogUtils.d(tag, "========request'log=======");
            LogUtils.d(tag, "method : " + request.method());
            LogUtils.d(tag, "url : " + url);
            if (headers != null && headers.size() > 0)
            {
                LogUtils.d(tag, "headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null)
            {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null)
                {
                    LogUtils.d(tag, "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType))
                    {
                        LogUtils.d(TAG, "logForRequest: ---------requestBody's content");
                        LogUtils.json(tag, bodyToString(request));
                    } else
                    {
                         LogUtils.e(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
             LogUtils.d(tag, "========request'log=======end");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType)
    {
        if (mediaType.type() != null && mediaType.type().equals("text"))
        {
            return true;
        }
        if (mediaType.subtype() != null)
        {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request)
    {
        try
        {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e)
        {
            return "something error when show requestBody.";
        }
    }
}

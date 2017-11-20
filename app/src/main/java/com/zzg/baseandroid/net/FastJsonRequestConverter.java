package com.zzg.baseandroid.net;

import com.zzg.baseandroid.util.JsonUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.ByteString;
import retrofit2.Converter;

/**
 * @author zhongzhigang
 * @Title: ${FILE_NAME}
 * @Description:
 * @package_name: sanocare.minute.clinic.net
 * @date 2017/6/23
 */
public class FastJsonRequestConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        String json = JsonUtil.toJsonString(value);
        ByteString byteString = ByteString.encodeUtf8(json);
        return RequestBody.create(MEDIA_TYPE,byteString );
    }
}

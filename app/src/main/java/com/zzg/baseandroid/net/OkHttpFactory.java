package com.zzg.baseandroid.net;


import com.zzg.baseandroid.common.BaseApplication;
import com.zzg.baseandroid.util.CacheUtil;
import com.zzg.baseandroid.util.NetUtils;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpFactory {

    public static final String TAG="OkHttpFactory";

    private static final int DEFAULT_TIMEOUT = 10;
    private static OkHttpFactory mOkHttpFactory;
    private static OkHttpClient mOkHttpClient;

    public static OkHttpFactory getInstance() {
        if (mOkHttpFactory == null) {
            mOkHttpFactory = new OkHttpFactory();
        }
        return mOkHttpFactory;
    }


    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

            okhttpBuilder.addNetworkInterceptor(new LoggerInterceptor("okhttp",true));
            okhttpBuilder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);

//            if(BuildConfig.DEBUG){
//                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                okhttpBuilder.addInterceptor(httpLoggingInterceptor);
//            }

            okhttpBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okhttpBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            okhttpBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            String path = CacheUtil.getCacheDir(BaseApplication.getInstance())+ File.separator+"httpcache";
            File file = new File(path);
            okhttpBuilder.cache(new Cache(file,10485760));
            mOkHttpClient = okhttpBuilder.build();
        }
        return mOkHttpClient;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor(){
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            String scc_token = "null";
//            if(MCApplication.getInstance().userInfo != null){
//                scc_token = MCApplication.getInstance().userInfo.getScc_token();
//            }
//            String userinfo = (String) SharedPreferencesUtils.get(SharedPreferencesUtils.MC_USERINFO, "");
//            userinfo = URLEncoder.encode(userinfo, "UTF-8");
            Request request = original.newBuilder()
                    .method(original.method(), original.body())
                    .addHeader("sccToken",scc_token)//有效性验证字段
//                    .addHeader("sccUser",userinfo)//与前端那边cookie格式相同
                    .addHeader("Platform-Type","app")//平台编号
                    .build();
            String cacheControl = request.cacheControl().toString();
//            long timestamp = System.currentTimeMillis();
//            String nonce = genNonce(timestamp, 16);
//            String str=getSignature(timestamp, nonce);
//            if(!NetUtils.hasNetwork(MCApplication.getInstance())){
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .method(original.method(), original.body())
//                        .addHeader("sccToken",scc_token)
//                        .addHeader("Platform-Type","app")//平台编号
//                        .addHeader("sccUser",userinfo)//与前端那边cookie格式相同
//                        .build();
//            }
            Response originalResponse = chain.proceed(request);
            if(NetUtils.hasNetwork(BaseApplication.getInstance())){
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
//                        .addHeader("SN-Timestamp",timestamp+"")
//                        .addHeader("SN-Randomnumber",nonce)
//                        .addHeader("SN-Visted","2")
//                        .addHeader("SN-Signature",str)
                        .build();
            }else{
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .build();
            }
        }
    };

    //signature_key
    public static String app_key="sino";

    /**
     * 按规则生成请求头的数据签名
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String getSignature(long timestamp, String nonce ){
        String str=null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String mix = timestamp+""+ app_key + nonce+"2";
            str = getString(md.digest(mix.getBytes()));
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return str;
    }

    /**
     * 字节转字符
     * @param encryption
     * @return
     */
    public static String getString(byte[] encryption){
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < encryption.length; i++)
        {
            if (Integer.toHexString(0xff & encryption[i]).length() == 1)
            {
                strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
            }
            else
            {
                strBuf.append(Integer.toHexString(0xff & encryption[i]));
            }
        }
        return strBuf.toString();
    }

    public static String genNonce(long seed, int length) {
        String base = "0123456789";//abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYAC
        Random random = new Random();
        random.setSeed(seed);
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();
    }

}

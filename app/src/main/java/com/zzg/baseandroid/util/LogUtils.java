package com.zzg.baseandroid.util;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zzg.baseandroid.BuildConfig;


/**
 * Log统一管理类
 */
public class LogUtils
{

	private LogUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}



	public static boolean isDebug = BuildConfig.LOG_DEBUG;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static final String TAG = "mclinic";


	public static void init(){
		FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
				.showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
				.methodCount(1)         // (Optional) How many method line to show. Default 2
				.methodOffset(2)        // (Optional) Hides internal method calls up to offset. Default 5
//				.logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
				.tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
				.build();

		Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
	}

//	// 下面四个是默认tag的函数
	public static void i(String msg)
	{
		if (isDebug)
			Logger.i(msg);
	}

	public static void d(String msg)
	{
		if (isDebug)
			Logger.d(msg);
	}

	public static void e(String msg)
	{
		if (isDebug)
			Logger.e(msg);
	}

	public static void v(String msg)
	{
		if (isDebug)
			Logger.v(msg);
	}

	public static void w(String msg)
	{
		if (isDebug)
			Logger.w(msg);
	}

	public static void wtf(String msg)
	{
		if (isDebug)
			Logger.wtf(msg);
	}

	public static void json(String jsonString){
		if(isDebug){
			Logger.json(jsonString);
		}
	}

	public static void xml(String xmlString){
		if(isDebug){
			Logger.xml(xmlString);
		}
	}

	public static void object(Object object){
		if(isDebug){
			Logger.d(object);
		}
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg)
	{
		if (isDebug)
			Logger.t(tag).i(msg);
	}

	public static void d(String tag, String msg)
	{
		if (isDebug)
			Logger.t(tag).d(msg);
	}

	public static void e(String tag, String msg)
	{
		if (isDebug)
			Logger.t(tag).e(msg);
	}

	public static void v(String tag, String msg)
	{
		if (isDebug)
			Logger.t(tag).v(msg);
	}

	public static void w(String tag, String msg)
	{
		if (isDebug)
			Logger.t(tag).w(msg);

	}

	public static void wtf(String tag, String msg)
	{
		if (isDebug)
			Logger.t(tag).wtf(msg);
	}

	public static void json(String tag ,String jsonString){
		if(isDebug){
			Logger.t(tag).json(jsonString);
		}
	}

	public static void xml(String tag ,String xmlString){
		if(isDebug){
			Logger.t(tag).xml(xmlString);
		}
	}

	public static void object(String tag ,Object object){
		if(isDebug){
			Logger.t(tag).d(object);
		}
	}
}
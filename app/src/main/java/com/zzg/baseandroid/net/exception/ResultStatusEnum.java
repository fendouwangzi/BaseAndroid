package com.zzg.baseandroid.net.exception;


import com.zzg.baseandroid.util.JavaEnumUtils;

/**
 * 
* @Title: ResultStatusEnum.java 
* @Package org.sinocare.cloud.common.core.enums 
* @Description: 请求结果状态枚举
* @author tangzhibin@sinocare.com
* @date 2016年5月17日 
* @version V1.0
 */
public enum ResultStatusEnum {
    /**
     * 常量代码5位 
     * 
     * <pre>
     * 	
     * 	110开头 服务器内部错误
     *  20开头 
     * </pre>
     */

    SUCCESS(1, "成功"),

    FAILURE(0, "失败"),

    /**
     *参数不合法
     */
    PARAMETER_INVALID(11010, "参数不合法"),
    /**
     * 参数不完整
     */
    PARAMETER_NULL(11011, "参数不完整"),

    /**
     * 网络连接错误
     */
    NETWORK_CANNOT_LINK(11012, "网络连接错误"),

    /**
     * 值太长
     */
    VALUE_TOOLONG(11013, "值太长"),

    /**
     * 记录值已经存在
     */
    EXIST_RECORD_VALUE(11014, "记录值已经存在"),

    /**
     * 记录值不存在
     */
    NOT_EXIST_RECORD_VALUE(11015, "记录值不存在"),
  
    /**
     * MAC地址对应的设备不存在
     */
    NOT_EXIST_MAC_RECORD_VALUE(11018, "MAC地址对应的设备不存在"),
  
    /**
     * 无效的邮箱地址
     */
    INVALID_EMAIL_ADDR(11019, "无效的邮箱地址"),

    /**
     * Sign验证失败
     */
    SIGN_ERROR(11016, "Sign验证失败"),
    
    /**
     * 未登陆
     */
    NO_LOGIN(11017, "未登录"),
    
    /**
     * 验证码为空
     */
    VCODE_NULL(11018,"验证码不能为空"),
    
    /**
     * 验证码错误
     */
    VCODE_WRONG(11019,"验证码错误"),
    
    /**
     * 验证码已失效
     */
    VCODE_EXPIRE(11020,"验证码不合法或已失效"),
    
    /**
     * 设备sn不合法
     */
    INVALID_DEVICE_SN(11021,"设备sn不合法"),

    /**
     * app 端手机号已经注册
     */
    APP_MOBILE_USED(12000, "该手机号已经注册"),
    /**
     * app 端手机号已经注册
     */
    APP_WRONG_VCODE(12001, "验证码错误"),
    
    /**
     * 手机号不能为空
     */
    APP_INVALID_MOBILE(12002, "手机号无效"),
    
    /**
     * 密码不能为空
     */
    APP_INVALID_PASSWD(12003, "密码不能为空"),
    
    /**
     * 验证码不能为空
     */
    APP_INVALID_VCODE(12004, "验证码不能为空"),
    
    /**
     * app端设备标识不能为空
     */
    APP_INVALID_DTOKEN(12005, "app端设备标识不能为空"),
    
    /**
     * 用户昵称不能为空
     */
    APP_INVALID_NICKNAME(12006, "用户昵称不能为空"),
    /**
     * 确认密码与新密码不一致
     */
    APP_PASSWD_NO_MATCH(12007, "确认密码与新密码不一致"),
    /**
     * 无效的业务类型
     */
    APP_INVALID_VCODE_BUSINESS(12008, "验证短信业务类型无效"),
    /**
     * app 端手机号已经注册
     */
    APP_MOBILE_NOTREGISTER(12009, "该手机号未注册"),
    /**
     * 无效的设备ticket号
     */
    APP_INVALID_DEVICE_TICKET(12010, "无效的设备ticket号"),
    /**
     * 没有绑定设备
     */
    APP_DEVICE_NOT_BIND(12011, "没有绑定设备"),
    
    /**
     * 设备已经绑定
     */
    APP_DEVICE_BINDED(12012, "设备已经绑定"),
    
    /**
     * token失效
     */
    APP_TOKEN_TIME_OUT(12013, "token失效"),
    
    /**
     * 登录失败,手机号或者密码错误
     */
    APP_LOGIN_FAIL(12014, "登录失败,手机号或者密码错误"),
    
    /**
     * 未绑定手机号码
     */
    APP_NO_BIND_MOBILE(12015, "未绑定手机号码"),
    
    /**
     * 手机号与公众号已绑定的手机号码不一致
     */
    APP_BIND_MOBILE_NO_CONSISTENT(12016, "该手机号已经注册"),
    
    /**
     * 
     */
    APP_NO_CHANGE(12017, "无信息变动"),
    
    /**
     * 无效的手机号码
     */
    INVALID_MOBILE_PHONE_NO(12018, "无效的手机号码"),
    
    /**
     * 生日格式不正确
     */
    INVALID_BRITHDAY(12019, "生日格式不正确"),
    
    /**
     * 已绑定此邀请码
     */
    APP_INVITE_CODE_BINDED(12020, "您已绑定此邀请码"),
    
    /**
     * 创建的社群数已达到最大值
     */
    APP_CREATE_MAX_COMMUNITY(12021, "创建的社群数已达到最大值"),
    
    /**
     * 社群名已经存在
     */
    APP_EXIST_COMMUNITY(12022, "社群名已经存在"),
    
    /**
     * 无此权限
     */
    APP_NO_POWER(12023, "无此权限"),
    
    /**
     * 已加入社群
     */
    APP_JOINED(12024, "您已加入该社群"),
    
    /**
     * 审核中
     */
    APP_AUDITING(12025, "正在审核中"),
    
    /**
     * 邀请码不合法
     */
    APP_INVALID_INVITE_CODE(12026, "邀请码不合法"),
    
    /**
     * 社群名已经存在
     */
    APP_HAS_AUDITING_COMMUNITY(12027, "正在审核中，暂时不能创建社群"),
    
    /**
     * 不支持此图片格式
     */
    APP_NOT_SUPPORTED_SUFFIX(12028, "不支持此图片格式"),
    
    /**
     * 群主不能退群
     */
    APP_CREATOR_CANNOT_QUIT(12029, "群主不能退群"),
    
    /**
     *社群不存在
     */
    APP_NOT_EXIST_COMMUNITY(12030, "社群不存在"),
    
    /**
     *官方群，不能进行此操作
     */
    APP_OFFICE_COMMUNITY_NO_THIS_OPERATE(12031, "此群为官方群,不能进行相关操作"),
    
    /**
     *含义违禁词
     */
    APP_CONTENT_HAS_SUBTLE_WORD(12032, "您提交的内容含有违禁词,请修改后重新提交"),
    
    /**
     *帖子不存在
     */
    APP_NOT_EXIST_TOPIC(12033, "帖子不存在"),
    
    /**
     * 置顶帖子数已达极限
     */
    APP_TOP_TOPICS_MAXED(12034, "置顶帖子数已达极限"),
    
    /**
     * 未绑定医生邀请码
     */
    APP_NO_BIND_DOCTOR_INVCODE(12035,"未绑定医生邀请码"),
    
    /**
     * 操作过于频繁
     */
    APP_OPERATION_FREQUENT(12036,"操作过于频繁"),

    /**
     * 用户无默认地址
     */
    APP_NOEXIT_DEFAULT_ADDRESS(12037,"无默认地址"),
    
    /**
     * 运营端登录用户名或密码错误
     */
    PC_INVALID_USERNAME_OR_PASSWORD(13000, "用户名或密码错误"),
    
    /**
     * 手机号已存在
     */
    PC_MOBILE_PHONE_NO_EXIST(13001, "该手机号已经存在"),
    
    /**
     * 服务器无最新版本
     */
    APP_NO_NEW_VERSION_EXIST(13002,"服务器无最新版本"),
    
   
	/**
	 * 运营端登录用户名不能为空
	 */
	PC_INVALID_USERNAME(13003,"用户名不能为空"),
	
	/**
	 * 运营端登录密码不能为空
	 */
	PC_INVALID_PASSWORD(13004,"密码不能为空"),
    
    /**
	 * 运营端登录密码不能为空
	 */
	PC_BANNER_NUMS_MAXED(13005,"最多只能设置3张有效幻灯片"),
	
	/**
     * 云平台登录用户名或密码错误
     */
    CLOUD_INVALID_USERNAME_OR_PASSWORD(14000, "用户名或密码错误"),
	
	/**
	 * 手机号格式不对
	 */
    CLOUD_INVALID_MOBILE(14001,"手机号格式不对"),
    
    /**
	 * 云平台登录用户名或手机号不能为空
	 */
	CLOUD_INVALID_USERIDORMOBILE(14002,"账号或手机号码不能为空"),
	
	/**
	 * 云平台登录密码不能为空
	 */
	CLOUD_INVALID_PASSWORD(14003,"密码不能为空"),
	
	/**
	 * 云平台客户编码不能为空
	 */
	CLOUD_INVALID_CUSTOMERCODE(14004,"客户编码不能为空"),
	
	/**
     * 云平台手机号已存在
     */
    CLOUD_MOBILE_PHONE_EXIST(14005, "该手机号已经存在"),
    
    /**
     * 云平台原始密码不能为空
     */
    CLOUD_INVALID_OLD_PASSWORD(14006,"原始密码不能为空"),
    
    /**
     * 云平台新密码不能为空
     */
    CLOUD_INVALID_NEW_PASSWORD(14007,"新密码不能为空"),
	
    /**
     * 云平台原始密码不能为空
     */
	CLOUD_FALSE_OLD_PASSWORD(14008,"原始密码有误"),
	
	/**
     * token失效
     */
    CLOUD_TOKEN_TIME_OUT(14009, "token超时"),
	
    /**
     * 云平台用户名不能为空
     */
	CLOUD_INVALID_USERNAME(14010,"用户名不能为空"),
	
	/**
	 * 云平台登录账号不存在
	 */
	CLOUD_USER_NO_EXIST(14011,"该账户不存在"),
	
	/**
	 * 云平台登录次数超过限制
	 */
	CLOUD_LOGIN_TIMES_BEYOND(14012,"密码输错超过5次，将锁定一天，请联系管理员"),
	
	/**
	 * 账户已经锁定
	 */
	CLOUD_LOGIN_LOCKED(1401201,"账户已经锁定，请联系管理员"),
	
	/**
	 * 该客户已经被禁用，请联系三诺管理员
	 */
	CLOUD_CUSTOMER_LOCKED(14013,"该客户已经被禁用，请联系管理员"),
	
	/**
	 * 不合法登录账号（每个登录账号必须在customer下面）
	 */
	CLOUD_INVALID_USER(14014,"该账户不合法"),
	
	/**
	 * 云平台手机号码不能为空
	 */
    CLOUD__MOBILE_NOTNULL(14015,"手机号码不能为空"),
	
    /**
	 * 云平台公司名称不能为空
	 */
    CLOUD__CUSTOMER_NAME_NOTNULL(14016,"公司名称不能为空"),
	
    /**
   	 * 云平台scretKey不能为空
   	 */
    CLOUD__SECRETKEY_NOTNULL(14017,"scretKey不能为空"),
	
    /**
     * 该公司名称已经注册
     */
	CLOUD_DUPLICATE_CUSTOMERNAME(14018,"该公司名称已经注册"),
	
	/**
	 * 该手机号已经注册
	 */
	CLOUD_DUPLICATE_PHONE(14019,"该手机号已经注册"),
	
	/**
	 * 该职位已经创建
	 */
	CLOUD_DUPLICATE_MENU(14020,"该职位已经创建"),
	
	/**
	 * 职位权限不能为空
	 */
	CLOUD_AUTHORITY_NOTNULL(14021,"职位权限不能为空"),
	
	/**
	 * 职位不能为空
	 */
    CLOUD__POSITION_NOTNULL(14022,"职位不能为空"),
	
    /**
     * excel上传数据量超限
     */
	CLOUD_EXCEL_UPLOAD_MAX_LIMIT(14023,"上传数据量超限"),
	
	/**
	 * 用户无查询权限
	 */
	CLOUD_USER_NO_AUTHORITY(14024,"此用户无此项操作权限"),
	
	/**
	 * 客户有误（输入客户码与数据库中不匹配）
	 */
	CLOUD_CUSCODE_INVALID(14025,"客户码有误"),
	
	/**
	 * 账号有误（输入账号与数据库中不匹配）
	 */
	CLOUD_USERID_INVALID(14026,"账号有误"),
	
	/**
     * token失效
     */
    CLOUD_TOKEN_OFFLINE(14027, "token下线"),

    /**
     * 电子血糖监测日记:hashCode不存在
     */
    M_HASHCODE_INVALID(15001, "hashCode不存在"),

    /**
     * 电子血糖监测日记:血糖结果值非法
     */
    M_RESULT_INVALID(15002, "血糖结果值非法");



    public int code;

    public String description;

    /**
     * 构造函数
     * 
     * @param code
     *            编码
     * @param description
     *            说明
     */
    private ResultStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
        JavaEnumUtils.put(this.getClass().getName() + code, this);
    }

    /**
     * <pre>
     * 一个便利的方法，方便使用者通过code获得枚举对象，
     * 对于非法状态，以个人处理&lt;/b&gt;
     * </pre>
     * 
     * @param code
     * @return
     */
    public static ResultStatusEnum valueOf(int code) {
        Object obj = JavaEnumUtils.get(ResultStatusEnum.class.getName() + code);
        if (null != obj) {
            return (ResultStatusEnum) obj;
        }
        return FAILURE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

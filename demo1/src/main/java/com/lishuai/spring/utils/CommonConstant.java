package com.lishuai.spring.utils;

public interface CommonConstant {

	/**
	 * 正常状态
	 */
	public static final Integer STATUS_NORMAL = 0;

	/**
	 * 禁用状态
	 */
	public static final Integer STATUS_DISABLE = -1;

	/**
	 * 删除标志
	 */
	public static final Integer DEL_FLAG_1 = 1;

	/**
	 * 未删除
	 */
	public static final Integer DEL_FLAG_0 = 0;

    /**
     * 对象状态：1 数据有效
     */
    public static final Integer OBJECT_STATUS_1 = 1;

    /**
     * 对象状态：0 数据无效
     */
    public static final Integer OBJECT_STATUS_0 = 0;

    /**
     * 字典编码：statStage 普查阶段
     */
    public static final String STAT_STAGE_DICT_CODE = "statStage";

    /**
     * 普查阶段：md 摸底阶段
     */
    public static final String STAT_STAGE_MD = "md";

    /**
     * 普查阶段：dj 登记阶段
     */
    public static final String STAT_STAGE_DJ = "dj";

    /**
     * 普查阶段：check 事后质量抽查
     */
    public static final String STAT_STAGE_CHECK = "check";

    /**
     * 字典编码：mapQueryBy 数据地图查询方式
     */
    public static final String MAP_QUERYBY_DICT_CODE = "mapQueryBy";

    /**
     * 数据地图查询方式：tdsql
     */
    public static final String MAP_QUERYBY_TDSQL = "tdsql";

    /**
     * 数据地图查询方式：tbase
     */
    public static final String MAP_QUERYBY_TBASE = "tbase";

    /**
     * 字典编码：multiQueryBy 综合查询方式
     */
    public static final String MULTI_QUERYBY_DICT_CODE = "multiQueryBy";

    /**
     * 综合查询方式：tdsql
     */
    public static final String MULTI_QUERYBY_TDSQL = "tdsql";

    /**
     * 综合查询方式：tbase
     */
    public static final String MULTI_QUERYBY_TBASE = "tbase";

    /**
     * 字典编码：multiQueryShard 综合查询是否使用分布式tbase
     */
    public static final String MULTI_QUERYBY_SHARD = "multiQueryShard";

    /**
     * 综合查询是否使用分布式tbase：是
     */
    public static final String MULTI_QUERYBY_SHARD_TRUE = "true";

    /**
     * 综合查询是否使用分布式tbase：否
     */
    public static final String MULTI_QUERYBY_SHARD_FALSE = "false";

    /**
     * 字典编码：processCron 进度缓存cron表达式
     */
    public static final String PROCESS_CRON_DICT_CODE = "processCron";

    /**
     * 是否有子节点：1 是
     */
    public static final String HAS_CHILD_1 = "1";

    /**
     * 是否有子节点：0 否
     */
    public static final String HAS_CHILD_0 = "0";

	/**
	 * 系统日志类型： 登录
	 */
	public static final int LOG_TYPE_1 = 1;
	
	/**
	 * 系统日志类型： 操作
	 */
	public static final int LOG_TYPE_2 = 2;

    /**
     * 系统日志类型： 统一身份认证用户同步
     */
    public static final int LOG_TYPE_3 = 3;

	/**
	 * 操作日志类型： 查询
	 */
	public static final int OPERATE_TYPE_1 = 1;
	
	/**
	 * 操作日志类型： 添加
	 */
	public static final int OPERATE_TYPE_2 = 2;
	
	/**
	 * 操作日志类型： 更新
	 */
	public static final int OPERATE_TYPE_3 = 3;
	
	/**
	 * 操作日志类型： 删除
	 */
	public static final int OPERATE_TYPE_4 = 4;
	
	/**
	 * 操作日志类型： 倒入
	 */
	public static final int OPERATE_TYPE_5 = 5;
	
	/**
	 * 操作日志类型： 导出
	 */
	public static final int OPERATE_TYPE_6 = 6;
	
	
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;
    
    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;

    /** 登录用户Shiro权限缓存KEY前缀 */
    public static String PREFIX_USER_SHIRO_CACHE  = "shiro:cache:org.jeecg.modules.shiro.authc.ShiroRealm.authorizationCache:";
    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";
    /** Token缓存时间：3600秒即一小时 */
    public static final int  TOKEN_EXPIRE_TIME  = 3600;
    

    /**
     *  0：一级菜单
     */
    public static final Integer MENU_TYPE_0  = 0;
   /**
    *  1：子菜单 
    */
    public static final Integer MENU_TYPE_1  = 1;
    /**
     *  2：按钮权限
     */
    public static final Integer MENU_TYPE_2  = 2;
    
    /**通告对象类型（USER:指定用户，ALL:全体用户）*/
    public static final String MSG_TYPE_UESR  = "USER";
    public static final String MSG_TYPE_ALL  = "ALL";
    
    /**发布状态（0未发布，1已发布，2已撤销）*/
    public static final String NO_SEND  = "0";
    public static final String HAS_SEND  = "1";
    public static final String HAS_CANCLE  = "2";
    
    /**阅读状态（0未读，1已读）*/
    public static final String HAS_READ_FLAG  = "1";
    public static final String NO_READ_FLAG  = "0";
    
    /**优先级（L低，M中，H高）*/
    public static final String PRIORITY_L  = "L";
    public static final String PRIORITY_M  = "M";
    public static final String PRIORITY_H  = "H";
    
    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    public static final String SMS_TPL_TYPE_0  = "0";
    public static final String SMS_TPL_TYPE_1  = "1";
    public static final String SMS_TPL_TYPE_2  = "2";
    
    /**
     * 状态(0无效1有效)
     */
    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";
    
    /**
     * 同步工作流引擎1同步0不同步
     */
    public static final String ACT_SYNC_0 = "0";
    public static final String ACT_SYNC_1 = "1";
    
    /**
     * 消息类型1:通知公告2:系统消息
     */
    public static final String MSG_CATEGORY_1 = "1";
    public static final String MSG_CATEGORY_2 = "2";
    
    /**
     * 是否配置菜单的数据权限 1是0否
     */
    public static final Integer RULE_FLAG_0 = 0;
    public static final Integer RULE_FLAG_1 = 1;

    /**
     * 是否用户已被冻结 1(解冻)正常 2冻结
     */
    public static final Integer USER_UNFREEZE = 1;
    public static final Integer USER_FREEZE = 2;
    
    /**字典翻译文本后缀*/
    public static final String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * 表单设计器主表类型
     */
    public static final Integer DESIGN_FORM_TYPE_MAIN = 1;

    /**
     * 表单设计器子表表类型
     */
    public static final Integer DESIGN_FORM_TYPE_SUB = 2;

    /**
     * 表单设计器URL授权通过
     */
    public static final Integer DESIGN_FORM_URL_STATUS_PASSED = 1;

    /**
     * 表单设计器URL授权未通过
     */
    public static final Integer DESIGN_FORM_URL_STATUS_NOT_PASSED = 2;

    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_ADD = "add";
    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_EDIT = "edit";
    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_DETAIL = "detail";
    /**
     * 表单设计器URL授权未通过
     */
    public static final String DESIGN_FORM_URL_TYPE_VIEW = "view";

    /**
     * 综合查询内置模板标识
     */
    public static final String MULTI_QUERY_SYS_FLAG = "sys_";

}

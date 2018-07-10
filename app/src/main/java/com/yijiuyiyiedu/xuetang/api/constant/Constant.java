package com.yijiuyiyiedu.xuetang.api.constant;


import com.yijiuyiyiedu.xuetang.module.base.BaseApplication;

import java.io.File;

/**
 * Created by 公用参数 on 2016/7/1.
 */
public class Constant {

    public static String ERROR_TITLE = "网络连接异常";
    public static String ERROR_CONTEXT = "请检查网络后重试";
    public static String ERROR_BUTTON = "重试";

    public static String EMPTY_TITLE = "没有找到数据";
    public static String EMPTY_CONTEXT = "换个条件试试吧";
    public static String WX_APP_SCERET = "f402e1a5d70090e3207ffa0b505d168d";
    public static String WX_APP_ID = "wx1e93e301d703d535";
    public static String BUGLY_APP_ID = "81fd4d7958";
    public static String QQ_APP_ID = "101472918";
    public static String PARTNER_ID = "1501374291";
    public static String BUYLY_TAG = "CrashReportInfo";
    public static String TENCENT_BASE = "http://1256959017.vod2.myqcloud.com";

    // 开启Log模式
    public static final boolean DEBUG = Boolean.parseBoolean("true");

    // 路径
    public static final String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + File.separator + "NetCache";


    // 服务器基础路径
    public static final String NEW_BASE_URL = "http://api.1911edu.com/Api/v1";
    //服务器
    public static final String BASE_URL = "http://api.1911edu.com";
    //用户登入
    public static final String USER_LOGIN = NEW_BASE_URL + "/Login/loginCheck";
    //第三方登录
    public static final String REGISTER_THIRD_PARTY = NEW_BASE_URL + "/Login/registerThirdParty";
    //判断第三方登录是否绑定手机号
    public static final String THIRD_PART_USER = NEW_BASE_URL + "/Login/getThirdPartUser";
    //注册发送验证码
    public static final String SEND_SMS = BASE_URL + "/Publics/PhoneSms/appSendSms";
    //用户注册
    public static final String USER_REGISTER = NEW_BASE_URL + "/Login/registerAjax";
    //企业上传图片
    public static final String BUSINESS_UPLOAD = BASE_URL + "/Publics/Upload/leafletsUpload";
    //首页
    public static final String HOME = NEW_BASE_URL + "/Index/Index";
    //用户学习课程列表
    public static final String USER_STUDY_LIST = NEW_BASE_URL + "/UserCenter/userCurriculum";
    //获取用户信息
    public static final String GET_USER_INFO = NEW_BASE_URL + "/Login/getPreInfo";
    //课程分类列表
    public static final String CURRICULUM_LIST = NEW_BASE_URL + "/Curriculum/curriculumList";
    //课程详情
    public static final String CURRICULUM_DETSILS = NEW_BASE_URL + "/Curriculum/curriculumDetail";
    //添加课程收藏
    public static final String COLLECT_COURSE = NEW_BASE_URL + "/Curriculum/addCollection";
    //消息管理
    public static final String MSG_MANAGER = NEW_BASE_URL + "/UserCenter/userMessage";
    //订单管理 （购买记录）
    public static final String ORDERLIST = NEW_BASE_URL + "/Order/orderList";
    //关键词搜索列表
    public static final String SEARCH_CURRICULUM = NEW_BASE_URL + "/Curriculum/searchCurriculumList";
    //搜索列表 含历史记录 热门搜索
    public static final String SEARCH_RECORDLIST = NEW_BASE_URL + "/SearchRecord/searchRecordList";
    //清除搜索历史记录
    public static final String CLEAR_HISTORY = NEW_BASE_URL + "/SearchRecord/clearUserSearchRecord";
    //删除收藏
    public static final String DEL_COLLACT = NEW_BASE_URL + "/Collection/deleteCollection";
    //课程收藏列表
    public static final String COLLECT_LIST = NEW_BASE_URL + "/Collection/collectionList";
    //保存编辑的个人信息
    public static final String SAVE_USER_INFO = NEW_BASE_URL + "/MyInfo/perInformationAjax";
    //修改密码
    public static final String UPDATE_PASSWORD = NEW_BASE_URL + "/MyInfo/editPassWordAjax";
    //忘记密码
    public static final String FORGET_PASSWORD = NEW_BASE_URL + "/Login/forgetPasswordAjax";
    //购买信息确认页
    public static final String BUY_COURSE = NEW_BASE_URL + "/MyInfo/buyCourse";
    //显示预支付信息并生成订单
    public static final String PRODUCE_ORDER = NEW_BASE_URL + "/Order/produceOrder";
    //获取评论分页列表
    public static final String GET_EVALAUTE = NEW_BASE_URL + "/Curriculum/getEvalua";
    //获取视频播放信息
    public static final String PLAYER_INFO = NEW_BASE_URL + "/CurriculumCatalog/curriculumCatalogPlayerInfo";
    //分类（一二级分类）列表
    public static final String CATEGROY_LIST = NEW_BASE_URL + "/Category/categoryList";
    //获取下级分类列表
    public static final String CHILD_CATEGORY_LIST = NEW_BASE_URL + "/Category/childCategoryList";
    //添加评价接口
    public static final String ADD_EVALUATE = NEW_BASE_URL + "/Curriculum/addEvaluate";
    //获取订单信息
    public static final String PAY_MENT = NEW_BASE_URL + "/Order/payMent";
    //支付宝订单
    public static final String ALI_PAY_MENT = BASE_URL + "/Pay/Wechat/appAliPrePayment";
    //微信订单
    public static final String WECHAT_PAY_MENT = BASE_URL + "/Pay/Wechat/appPrePayment";
    //获取新闻列表
    public static final String NEWS_LIST = NEW_BASE_URL + "/News/newsList";
    //查看兑换码列表
    public static final String CONVERT_LIST = NEW_BASE_URL + "/Order/orderRandomList";
    //获取投屏url
    public static final String GET_ALNA_URL = NEW_BASE_URL + "/CurriculumCatalog/curriculumCatalogVideoInfo";
//    数字课程列表接口
    public static final String VIDEO_CATALOG_LIST = NEW_BASE_URL + "/CurriculumCatalog/videoCatalogList";
    //添加联系商务信息
    public static final String ADD_CONTACT_BUSINESS = NEW_BASE_URL + "/Index/addContactBusiness";
    //企业定制课程H5
    public static final String ENTERPRISE_CUSTOM = NEW_BASE_URL + "/AppH5/projectCustomized";
    //线下活动H5
    public static final String UNDER_LINE_ACTIVITY = NEW_BASE_URL + "/AppH5/projectActivity";
    //学位项目H5
    public static final String ACADEMIC_DEGREE = NEW_BASE_URL + "/AppH5/projectDegree";
    //hr课程负责人入口
    public static final String HR_ENTRANCE= NEW_BASE_URL + "/AppH5/hrEntry";
    //搜索列表空展示的猜你喜欢
    public static final String SEARCH_NONE= NEW_BASE_URL + "/Curriculum/searchNoneRecommend";
    //获取省市县
    public static final String CITY_INFO= NEW_BASE_URL + "/MyInfo/regionList";
    //获取职业列表
    public static final String POSITION_LIST= NEW_BASE_URL + "/MyInfo/positionList";
    //获取评论列表
    public static final String GET_EVALUATE= NEW_BASE_URL + "/Curriculum/getEvalua";
    //获取课程目录列表
    public static final String CURRICULUM_CATALOG_LIST= NEW_BASE_URL + "/Curriculum/curriculumCatalogList";
    //新上好课列表
    public static final String NEW_CURRICULUM_LIST= NEW_BASE_URL + "/Curriculum/newsCurriculumList";
    //经典好课列表
    public static final String RECOMMEND_CURRICULUM_LIST= NEW_BASE_URL + "/Curriculum/recommendCurriculumList";
    //名师大咖秀列表
    public static final String TEACHER_LIST= NEW_BASE_URL + "/Teacher/teacherList";
    //分类列表 一二级列表
    public static final String CATEGORY_LIST= NEW_BASE_URL + "/Category/childCategoryList";
    //已绑定课程码
    public static final String CONVER_COURSE= NEW_BASE_URL + "/MyInfo/usedInvitationCodeList";
    //我的课程列表
    public static final String MY_CURRICULUM= NEW_BASE_URL + "/MyInfo/studyCurriculumList";
    //合作伙伴列表
    public static final String FRIEND_LIST= NEW_BASE_URL + "/CollaborationEnterprise/collaborationEnterpriseList";
    //验证手机号是否注册过
    public static final String CHECK_PHONE= NEW_BASE_URL + "/Login/checkPhoneReg";
    //关于我们 h5
    public static final String ABOUT_US= NEW_BASE_URL + "/AppH5/aboutUs";
    //名师详情
    public static final String TEACHER_DETAILS= NEW_BASE_URL + "/Teacher/teacherDetail";
    //保存播放进度
    public static final String SAVE_SCHEDULE= NEW_BASE_URL + "/VideoSeerecord/addVideoSeerecord";
//     用户购买项目详情


    //项目列表
//    public static final String PROJECT_CATEGORY = BASE_URL + "/Api/Project/projectCategoryList";
//项目分类列表
//    public static final String USER_PROJECT = BASE_URL + "/Api/UserCenter/userProject";
//    用户项目列表
//    public static final String USER_PROJECT_DETAILS = BASE_URL + "/Api/UserCenter/userProjectDetail";
////联系商务H5
//    public static final String CONTACT_BUSINESS = NEW_BASE_URL + "/Index/contactBusiness";
//    public static final String PROJECT_LIST = BASE_URL + "/Api/Project/projectList";
    //项目详情
//    public static final String PROJECT_DETAILS = BASE_URL + "/Api/Project/projectDetail";
    //提交定制项目
//    public static final String ADD_PROJECT_CUSTOM = BASE_URL + "/Api/Project/addProjectCustomized";
    //添加企业子账号
//    public static final String ADD_CHILD_USER = BASE_URL + "/Api/MyInfo/addChildUserAjax";
//    //删除企业子账号
//    public static final String DEL_CHILD_USER = BASE_URL + "/Api/MyInfo/delUser";
    //管理企业子账号
//    public static final String MANAGER_ACCOUNT = BASE_URL + "/Api/MyInfo/childAccount";
    //添加企业认证信息
//    public static final String COMPANY_CERT = BASE_URL + "/Api/Login/addEnterpriseInfoAjax";
//获取会员列表
//     public static final String VIP_LIST = BASE_URL + "/Api/Member/memberRecharge";
//兑换码兑换课程
//public static final String CONVERT_COURSE = BASE_URL + "/Api/Order/changeOrderRandomAjax";

}

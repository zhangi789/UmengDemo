package cn.hxek.com.bean;

/**
 * @author 张海洋
 * @Date on 2019/07/08.
 * @org 上海..科技有限公司
 * @describe
 */
public class PushCustomeBean {

    /**
     * 1、摇钱桩收益明细页面
     * 2、资金明细页面
     * 3、我的消息 系统消息页面
     * 4、新闻详情页面
     * 5、提现记录
     */
    private int type;
    private String openUrl;

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package cn.handyplus.rice.motd.constants;

import cn.handyplus.rice.motd.param.MotdParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量
 *
 * @author handy
 */
public abstract class MotdConstants {

    /**
     * MOTD 列表
     */
    public final static List<MotdParam> MOTD_LIST = new ArrayList<>();

    /**
     * 检查更新网址
     */
    public final static String PLUGIN_VERSION_URL = "https://api.github.com/repos/handy-git/RiceMotd/releases/latest";

}
package cn.handyplus.rice.motd.util;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.HandyConfigUtil;
import cn.handyplus.rice.motd.constants.MotdConstants;
import cn.handyplus.rice.motd.param.MotdParam;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.Map;

/**
 * 配置
 *
 * @author handy
 */
public class ConfigUtil {

    public static FileConfiguration CONFIG;

    /**
     * 加载全部配置
     */
    public static void init() {
        CONFIG = HandyConfigUtil.loadConfig();
        // 加载示例
        File langFile = new File(InitApi.PLUGIN.getDataFolder(), "logo/logo.png");
        if (!(langFile.exists())) {
            InitApi.PLUGIN.saveResource("logo/logo.png", false);
        }
        // 缓存数据
        Map<String, String> motdMap = HandyConfigUtil.getStringMapChild(CONFIG, "motd");
        if (motdMap.isEmpty()) {
            return;
        }
        MotdConstants.MOTD_LIST.clear();
        for (String key : motdMap.keySet()) {
            MotdParam param = new MotdParam();
            param.setMotd1(CONFIG.getString("motd." + key + ".motd1"));
            param.setMotd2(CONFIG.getString("motd." + key + ".motd2"));
            MotdConstants.MOTD_LIST.add(param);
        }
    }

}
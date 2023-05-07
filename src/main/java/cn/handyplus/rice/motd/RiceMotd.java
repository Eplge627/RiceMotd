package cn.handyplus.rice.motd;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.api.MessageApi;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.rice.motd.constants.MotdConstants;
import cn.handyplus.rice.motd.util.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public class RiceMotd extends JavaPlugin {
    private static RiceMotd INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        InitApi initApi = InitApi.getInstance(this);
        ConfigUtil.init();

        initApi.initCommand("cn.handyplus.rice.motd.command")
                .initListener("cn.handyplus.rice.motd.listener")
                .addMetrics(18406)
                .checkVersion(ConfigUtil.CONFIG.getBoolean(BaseConstants.IS_CHECK_UPDATE), MotdConstants.PLUGIN_VERSION_URL);

        MessageApi.sendConsoleMessage(ChatColor.GREEN + "已成功载入服务器！");
        MessageApi.sendConsoleMessage(ChatColor.GREEN + "Author:handy QQ群:1064982471");
    }

    @Override
    public void onDisable() {
        MessageApi.sendConsoleMessage(ChatColor.GREEN + "已成功卸载！");
        MessageApi.sendConsoleMessage(ChatColor.GREEN + "Author:handy QQ群:1064982471");
    }

    public static RiceMotd getInstance() {
        return INSTANCE;
    }

}
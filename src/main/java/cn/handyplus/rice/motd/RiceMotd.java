package cn.handyplus.rice.motd;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.rice.motd.constants.MotdConstants;
import cn.handyplus.rice.motd.util.ConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public class RiceMotd extends JavaPlugin {
    public static boolean USE_PAPI;

    @Override
    public void onEnable() {
        InitApi initApi = InitApi.getInstance(this);
        ConfigUtil.init();

        // 加载PlaceholderApi
        USE_PAPI = BaseUtil.hook(BaseConstants.PLACEHOLDER_API);

        initApi.initCommand("cn.handyplus.rice.motd.command")
                .initListener("cn.handyplus.rice.motd.listener")
                .checkVersion(ConfigUtil.CONFIG.getBoolean(BaseConstants.IS_CHECK_UPDATE), MotdConstants.PLUGIN_VERSION_URL)
                .addMetrics(18406);

        MessageUtil.sendConsoleMessage("&a已成功载入服务器！");
        MessageUtil.sendConsoleMessage("&aAuthor:handy MCBBS: https://www.mcbbs.net/thread-1442905-1-1.html");
    }

}
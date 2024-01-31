package cn.handyplus.rice.motd.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.HandyHttpUtil;
import cn.handyplus.rice.motd.constants.MotdConstants;
import cn.handyplus.rice.motd.util.ConfigUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * 玩家进入服务器事件.
 *
 * @author handy
 */
@HandyListener
public class PlayerEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onOpPlayerJoin(PlayerJoinEvent event) {
        // op登录发送更新提醒
        if (!ConfigUtil.CONFIG.getBoolean(BaseConstants.IS_CHECK_UPDATE_TO_OP_MSG, true)) {
            return;
        }
        HandyHttpUtil.checkVersion(event.getPlayer(), MotdConstants.PLUGIN_VERSION_URL);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLogin(PlayerLoginEvent event) {
        if (!MotdConstants.MAINTENANCE_MODE) {
            return;
        }
        Player player = event.getPlayer();
        if (player.isOp()) {
            return;
        }
        String maintenanceModeMsg = ConfigUtil.CONFIG.getString("maintenanceModeMsg", "&e服务器正在维护中...");
        event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, BaseUtil.replaceChatColor(maintenanceModeMsg));
    }

}
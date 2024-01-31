package cn.handyplus.rice.motd.command.admin;

import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.rice.motd.constants.MotdConstants;
import cn.handyplus.rice.motd.util.ConfigUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 维护模式
 *
 * @author handy
 */
public class MaintenanceCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "maintenance";
    }

    @Override
    public String permission() {
        return "riceMotd.maintenance";
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        MotdConstants.MAINTENANCE_MODE = !MotdConstants.MAINTENANCE_MODE;
        String maintenanceModeMsg = ConfigUtil.CONFIG.getString("maintenanceModeMsg", "&e服务器正在维护中...");
        if (MotdConstants.MAINTENANCE_MODE) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.kickPlayer(BaseUtil.replaceChatColor(maintenanceModeMsg));
            }
        }
        MessageUtil.sendMessage(sender, MotdConstants.MAINTENANCE_MODE ? "&a维护模式已开启" : "&a维护模式已关闭");
    }

}
package cn.handyplus.rice.motd.listener;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.core.CollUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.rice.motd.constants.MotdConstants;
import cn.handyplus.rice.motd.param.MotdParam;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 当收到MOTD请求时被调用
 *
 * @author handy
 */
@HandyListener
public class ServerListPingEventListener implements Listener {

    /**
     * 替换自定义数据.
     *
     * @param event 事件
     */
    @EventHandler
    public void onEvent(ServerListPingEvent event) throws Exception {
        // 替换MOTD
        if (CollUtil.isNotEmpty(MotdConstants.MOTD_LIST)) {
            MotdParam param = MotdConstants.MOTD_LIST.get(new Random().nextInt(MotdConstants.MOTD_LIST.size()));
            event.setMotd(BaseUtil.replaceChatColor(param.getMotd1() + "\n" + param.getMotd2()));
        }
        // 替换ICON
        File file = this.randomFile();
        if (file != null) {
            event.setServerIcon(Bukkit.loadServerIcon(file));
        }
    }

    /**
     * 获取随机icon
     *
     * @return icon file
     */
    private File randomFile() {
        File directory = new File(InitApi.PLUGIN.getDataFolder(), "logo/");
        if (!(directory.exists())) {
            InitApi.PLUGIN.saveResource("logo/", false);
        }
        // 获取全部配置
        File[] spawnFileList = directory.listFiles();
        if (spawnFileList == null || spawnFileList.length == 0) {
            return null;
        }
        // 循环加载文件
        List<File> fileList = Arrays.asList(spawnFileList);
        return fileList.get(new Random().nextInt(fileList.size()));
    }

}
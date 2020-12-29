package cn.edu.uestc.cac.api.utils;

import java.io.IOException;

import cn.edu.uestc.cac.dao.entity.SystemIndicators;
import sun.misc.BASE64Decoder;

/**
 * @author wang
 */
public class DataDecodeUtil {

    private static BASE64Decoder base64Decoder = new BASE64Decoder();

    @Deprecated
    public static SystemIndicators decode(String data) {
        String[] datas = data.split(" ");
        if (datas.length != 21) return null;
        for (int i = 0; i < datas.length; i++) {
            datas[i] = base64Decode(datas[i]);
        }
        SystemIndicators indicators = new SystemIndicators();
        indicators.setVersion(stringToInt(datas[0], 1));
        indicators.setUptime(stringToInt(datas[1]));
        indicators.setSessions(stringToInt(datas[2]));
        indicators.setProcesses(stringToInt(datas[3]));
        indicators.setFileHandles(stringToInt(datas[4]));
        indicators.setFileHandlesLimit(stringToInt(datas[5]));
        indicators.setRamTotal(stringToLong(datas[6]));
        indicators.setRamUsage(stringToLong(datas[7]));
        indicators.setSwapTotal(stringToLong(datas[8]));
        indicators.setSwapUsage(stringToLong(datas[9]));
        indicators.setDiskTotal(stringToLong(datas[10]));
        indicators.setDiskUsage(stringToLong(datas[11]));
        indicators.setDiskRwTime(stringToInt(datas[12]));
        indicators.setConnections(stringToInt(datas[13]));
        indicators.setIpv4(datas[14]);
        indicators.setRx(stringToLong(datas[15]));
        indicators.setTx(stringToLong(datas[16]));
        String[] load = datas[17].split(" ");
        if (load.length == 3) {
            indicators.setLoadAvg1(stringToFloat(load[0].trim()));
            indicators.setLoadAvg5(stringToFloat(load[1].trim()));
            indicators.setLoadAvg15(stringToFloat(load[2].trim()));
        }
        indicators.setLoadCpu(stringToFloat(datas[18]));
        indicators.setLoadIo(stringToFloat(datas[19]));
        indicators.setLabel(stringToInt(datas[20]));
        return indicators;
    }

    public static String base64Decode(String s) {
        try {
            return new String(base64Decoder.decodeBuffer(s));
        } catch (IOException e) {
            return "N/A";
        }
    }

    public static int stringToInt(String s) {
        return stringToInt(s, 0);
    }

    public static long stringToLong(String s) {
        return stringToLong(s, 0L);
    }

    public static float stringToFloat(String s) {
        return stringToFloat(s, 0.0F);
    }

    public static int stringToInt(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long stringToLong(String s, long defaultValue) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static float stringToFloat(String s, float defaultValue) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

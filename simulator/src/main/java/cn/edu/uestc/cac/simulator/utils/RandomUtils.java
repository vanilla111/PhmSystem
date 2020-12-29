package cn.edu.uestc.cac.simulator.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 随机生成数字的工具类
 *
 * @author maomao
 * @date 2020-12-28
 */
public class RandomUtils {

    private static final Random random = new Random();

    /**
     * 随机生成 [a, b] 之间的整数，包含边界
     *
     * @param min 生成区间的最小值
     * @param max 生成区间的最大值
     * @return 随机生成的数字
     */
    public static int randomGenerateInteger(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 随机生成 [a, b] 之间的不同的 num 个整数，包含边界
     *
     * @param min 生成区间的最小值
     * @param max 生成区间的最大值
     * @param num 需要生成的不同整数的个数
     * @return 随机生成的数字
     */
    public static int[] randomGenerateDiffInteger(int min, int max, int num) {
        if (num <= 0 || num > max - min + 1) {
            return new int[0];
        }

        int idx = 0;
        int[] res = new int[num];
        Set<Integer> set = new HashSet<>();

        while (set.size() < num) {
            int val = randomGenerateInteger(min, max);
            if (!set.contains(val)) {
                res[idx++] = val;
                set.add(val);
            }
        }
        return res;
    }
}

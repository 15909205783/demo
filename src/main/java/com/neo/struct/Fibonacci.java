package com.neo.struct;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-04
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(calculate(10));
    }

    public static int calculate(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return calculate(num - 1) + calculate(num - 2);
    }
}

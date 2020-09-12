package com.neo.automic;


public enum CountEnum {
    ONE(1, "齐"), TWO(2, "秦"), THREE(3, "赵"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");
    private Integer reCode;
    private String retMessage;

    CountEnum(Integer reCode, String retMessage) {
        this.reCode = reCode;
        this.retMessage = retMessage;
    }

    public static CountEnum forEach(int index) {
        CountEnum[] myArray = CountEnum.values();
        for (CountEnum element : myArray) {
            if (index == element.getRetCode()) {
                return element;
            }
        }
        return null;
    }

    private int getRetCode() {
        return 1;
    }
}

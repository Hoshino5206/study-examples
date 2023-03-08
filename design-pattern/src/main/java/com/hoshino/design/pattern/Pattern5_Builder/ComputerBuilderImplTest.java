package com.hoshino.design.pattern.Pattern5_Builder;

/**
 * @author huangyuehao
 * @date 2023-03-06
 */
public class ComputerBuilderImplTest {

    public static void main(String[] args) {
        // 链式写法建造者模式
        Computer computer = new ComputerBuilderImpl().builder()
                .motherBoard("ROG 玩家国度 Z790-A GAMING D5")
                .cpu("Inter酷睿i9 13900k")
                .memory("芝奇皇家戟 32G*2")
                .disk("三星980Pro 2T")
                .gpu("华硕4090Ti 水猛禽")
                .power("龙神二代一体式水冷")
                .chassis("雷神二代1200W")
                .heatSink("太阳神机箱")
                .build();
        System.out.println("computer = " + computer);

        // 基于lombok注解建造者模式
        Computer computer1 = Computer.builder()
                .motherboard("ROG 玩家国度 Z790-A GAMING D5")
                .cpu("Inter酷睿i9 13900k")
                .memory("芝奇皇家戟 32G*2")
                .disk("三星980Pro 2T")
                .gpu("华硕4090Ti 水猛禽")
                .power("龙神二代一体式水冷")
                .chassis("雷神二代1200W")
                .heatSink("太阳神机箱")
                .build();
        System.out.println("computer1 = " + computer1);
    }

}

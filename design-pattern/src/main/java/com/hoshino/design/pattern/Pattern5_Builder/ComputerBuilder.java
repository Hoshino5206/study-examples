package com.hoshino.design.pattern.Pattern5_Builder;

/**
 * @author huangyuehao
 * @date 2023-03-06
 */
public interface ComputerBuilder {

    Computer computer = new Computer();

    ComputerBuilder builder();

    ComputerBuilder motherBoard(String str);

    ComputerBuilder cpu(String str);

    ComputerBuilder memory(String str);

    ComputerBuilder disk(String str);

    ComputerBuilder gpu(String str);

    ComputerBuilder heatSink(String str);

    ComputerBuilder power(String str);

    ComputerBuilder chassis(String str);

    Computer build();

}

package com.hoshino.design.pattern.Pattern5_Builder;

/**
 * @author huangyuehao
 * @date 2023-03-06
 */
public class ComputerBuilderImpl implements ComputerBuilder {

    @Override
    public ComputerBuilder builder() {
        return new ComputerBuilderImpl();
    }

    @Override
    public ComputerBuilder motherBoard(String str) {
        computer.setMotherboard(str);
        return this;
    }

    @Override
    public ComputerBuilder cpu(String str) {
        computer.setCpu(str);
        return this;
    }

    @Override
    public ComputerBuilder memory(String str) {
        computer.setMemory(str);
        return this;
    }

    @Override
    public ComputerBuilder disk(String str) {
        computer.setDisk(str);
        return this;
    }

    @Override
    public ComputerBuilder gpu(String str) {
        computer.setGpu(str);
        return this;
    }

    @Override
    public ComputerBuilder heatSink(String str) {
        computer.setHeatSink(str);
        return this;
    }

    @Override
    public ComputerBuilder power(String str) {
        computer.setPower(str);
        return this;
    }

    @Override
    public ComputerBuilder chassis(String str) {
        computer.setChassis(str);
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }

}

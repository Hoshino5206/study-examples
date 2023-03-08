package com.hoshino.design.pattern.Pattern5_Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangyuehao
 * @date 2023-03-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class Computer {

    private String motherboard;

    private String cpu;

    private String memory;

    private String disk;

    private String gpu;

    private String power;

    private String heatSink;

    private String chassis;

    public static Computer.ComputerBuilder builder() {
        return new Computer.ComputerBuilder();
    }

    public static class ComputerBuilder {
        private String motherboard;

        private String cpu;

        private String memory;

        private String disk;

        private String gpu;

        private String power;

        private String heatSink;

        private String chassis;

        ComputerBuilder() {
        }

        public Computer.ComputerBuilder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Computer.ComputerBuilder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Computer.ComputerBuilder memory(String memory) {
            this.memory = memory;
            return this;
        }

        public Computer.ComputerBuilder disk(String disk) {
            this.disk = disk;
            return this;
        }

        public Computer.ComputerBuilder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Computer.ComputerBuilder power(String power) {
            this.power = power;
            return this;
        }

        public Computer.ComputerBuilder heatSink(String heatSink) {
            this.heatSink = heatSink;
            return this;
        }

        public Computer.ComputerBuilder chassis(String chassis) {
            this.chassis = chassis;
            return this;
        }

        public Computer build() {
            return new Computer(this.motherboard, this.cpu, this.memory, this.disk, this.gpu, this.power, this.heatSink, this.chassis);
        }

        @Override
        public String toString() {
            return "Computer.ComputerBuilder(motherboard=" + this.motherboard + ", cpu=" + this.cpu + ", memory=" + this.memory + ", disk=" + this.disk + ", gpu=" + this.gpu + ", power=" + this.power + ", heatSink=" + this.heatSink + ", chassis=" + this.chassis + ")";
        }

    }
}

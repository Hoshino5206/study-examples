package com.hoshino.design.pattern.Pattern4_Prototype;

import lombok.*;

/**
 * @author huangyuehao
 * @date 2023-03-07
 * 浅克隆：创建一个新对象，新对象的属性和原来对象的属性完全相同，对于非基本类型属性(引用类型)，仍指向原有属性所指向的对象的内存地址。
 * 深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
 * Cloneable接口实现的克隆是浅拷贝。通过"复制"一个已经存在的实例来返回新的实例，而不是新建实例。被复制的实例就是我们所称的"原型"，这个原型是可定制的。
 * 性能优良，Java自带的原型模式是基于内存二进制流的拷贝，比直接new一个对象性能上提升了很多，我们平常使用的BeanUtils就是原型模式
 * 可以使用深克隆方式保存对象的状态，使用原型模式将对象复制一份将其状态保存起来，简化了创建对象的过程,以便在需要的时候使用
 */
@Setter
@Getter
public class PrototypeShallowClone implements Cloneable {

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student {

        private String name;

        private Integer number;

    }

    private Student student;

    private Integer age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        PrototypeShallowClone prototype = new PrototypeShallowClone();
        Student student = new Student("huang", 20);
        prototype.setStudent(student);

        PrototypeShallowClone prototypeCopy = (PrototypeShallowClone)prototype.clone();

        System.out.println(prototype.getStudent());
        System.out.println(prototypeCopy.getStudent());
    }

}


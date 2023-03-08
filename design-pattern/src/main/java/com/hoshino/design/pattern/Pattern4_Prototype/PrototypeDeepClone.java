package com.hoshino.design.pattern.Pattern4_Prototype;

import lombok.*;

import java.io.*;

/**
 * @author huangyuehao
 * @date 2023-03-07
 */
@Setter
@Getter
public class PrototypeDeepClone implements Cloneable, Serializable {

    private static final long serialVersionUID = -15007097570028131L;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Student implements Serializable{

        private static final long serialVersionUID = -365694101405640039L;

        private String name;

        private Integer number;

    }

    private Student student;

    private Integer age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert bos != null && oos != null && bis != null && ois != null;
            bos.close();
            oos.close();
            bis.close();
            ois.close();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        PrototypeDeepClone prototype = new PrototypeDeepClone();
        Student student = new Student("huang", 20);
        prototype.setStudent(student);

        PrototypeDeepClone prototypeCopy = (PrototypeDeepClone) prototype.deepClone();

        System.out.println(prototype.getStudent());
        System.out.println(prototypeCopy.getStudent());
    }

}
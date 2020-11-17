package com.mine.utils.javaSerializeUtil;

import java.io.*;
import java.time.Clock;

import com.mine.common.entity.User;

public class JavaSerializeUtil {

	/**
	 * 序列化
	 * @param object
	 * @param <T>
	 * @return
	 * @throws Exception
     */
	public static <T extends Serializable> byte[] serialize(T object) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		return baos.toByteArray();
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @param <T>
	 * @return
	 * @throws Exception
     */
	public static <T extends Serializable> T unserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		return (T)ois.readObject();
	}

	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setName("xxx");
		user.setAge(33);
		user.setTime(Clock.systemDefaultZone().millis());
		byte[] arr =serialize(user);
		User user1 = unserialize(arr);
        System.out.println(user1);
	}
}

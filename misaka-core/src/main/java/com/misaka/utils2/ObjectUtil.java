package com.misaka.utils2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author xiamo
 * @Description:
 * @ClassName: ObjectUtil
 * @date 2021/11/26 13:13
 */
public class ObjectUtil {

    public byte[] getByte() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)) {
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.yijiuyiyiedu.xuetang.utils.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * ClassName: AESUtils <br/>
 * date: 2013-11-15 下午3:13:42 <br/>
 *
 * @author hushuan
 */
public class AESUtils {
    private static final String ALGORITHM = "AES";
    private static final String Transformation = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE = 128;
    private static final int CACHE_SIZE = 1024;

    /**
     * 生成随机密钥
     *
     * @return
     * @throws Exception
     */
    public static String getSecretKey() throws Exception {
        return getSecretKey(null);
    }

    /**
     * <p>
     * 生成密钥
     * <p>
     *
     * @param seed 密钥种子
     * @return
     * @throws Exception
     */
    public static String getSecretKey(String seed) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom;
        if (seed != null && !"".equals(seed)) {
            secureRandom = new SecureRandom(seed.getBytes());
        } else {
            secureRandom = new SecureRandom();
        }
        keyGenerator.init(KEY_SIZE, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64Utils.encode(secretKey.getEncoded());
    }

    /**
     * 转换密钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        return secretKey;
    }

    /**
     * <p>
     * 加密(输入：byte[]，输出：byte[])
     * </p>
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        if (data != null && key != null) {
            // Key k = toKey(Base64Utils.decode(key));
            // byte[] raw = k.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),
                    ALGORITHM);
            Cipher cipher = Cipher.getInstance(Transformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(data);
        }

        return null;
    }

    /**
     * <p>
     * 加密(输入：String，输出：String)
     * <p>
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        if (data != null && key != null && !"".equals(key)) {
            try {
                byte[] result = encrypt(data.getBytes(), key);
                return Base64Utils.encode(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * <p>
     * 文件加密
     * </p>
     *
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void encryptFile(String key, String sourceFilePath,
                                   String destFilePath) throws Exception {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        if (sourceFile.exists() && sourceFile.isFile()) {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
            InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destFile);
            Key k = toKey(Base64Utils.decode(key));
            byte[] raw = k.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
            Cipher cipher = Cipher.getInstance(Transformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            CipherInputStream cin = new CipherInputStream(in, cipher);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = cin.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            cin.close();
            in.close();
        }
    }

    /**
     * <p>
     * 解密(输入：byte[]，输出：byte[])
     * </p>
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        // Key k = toKey(Base64Utils.decode(key));
        // byte[] raw = k.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),
                ALGORITHM);
        Cipher cipher = Cipher.getInstance(Transformation);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    /**
     * <p>
     * 解密(输入：String，输出：String)
     * </p>
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptToString(String data, String key) {
        if (data != null && key != null && !"".equals(key)) {
            try {
                byte[] result = decrypt(Base64Utils.decode(data), key);
                return new String(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * <p>
     * 文件解密
     * </p>
     *
     * @param key
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void decryptFile(String key, String sourceFilePath,
                                   String destFilePath) throws Exception {
        File sourceFile = new File(sourceFilePath);
        File destFile = new File(destFilePath);
        if (sourceFile.exists() && sourceFile.isFile()) {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile.createNewFile();
            FileInputStream in = new FileInputStream(sourceFile);
            FileOutputStream out = new FileOutputStream(destFile);
            Key k = toKey(Base64Utils.decode(key));
            byte[] raw = k.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
            Cipher cipher = Cipher.getInstance(Transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            CipherOutputStream cout = new CipherOutputStream(out, cipher);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                cout.write(cache, 0, nRead);
                cout.flush();
            }
            cout.close();
            out.close();
            in.close();
        }
    }

}

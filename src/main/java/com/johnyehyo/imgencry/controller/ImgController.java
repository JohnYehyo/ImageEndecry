package com.johnyehyo.imgencry.controller;

import com.johnyehyo.imgencry.ao.DecryAo;
import com.johnyehyo.imgencry.enums.ResponseEnum;
import com.johnyehyo.imgencry.util.ArrayUtil;
import com.johnyehyo.imgencry.util.MyAlgorithms;
import com.johnyehyo.imgencry.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;

/**
 * @description:
 * @author: JohnYehyo
 * @create: 2022-06-30 22:32:06
 */
@Controller
@RequestMapping
public class ImgController {

    @Value("${file.path}")
    private String path;

    @Value("${file.image}")
    private String imagePath;

    @Value("${file.suffix}")
    private String suffix;


    @RequestMapping
    public String index(){
        return "index";
    }

    @PostMapping(value = "encry")
    @ResponseBody
    public String encry() {
        return "";
    }

    @PostMapping(value = "decry")
    @ResponseBody
    public ResponseVo decry(@RequestBody DecryAo decryAo) {
        if (null == decryAo.getImgUrl() || decryAo.getImgUrl().equals("")) {
            return ResponseVo.error();
        }

        double key = decryAo.getKey();
        String dir = path + imagePath;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String imageName = UUID.randomUUID().toString().replace("-", "") + suffix;
        File out = new File(path + imagePath + "/" + imageName);
        Boolean encrypt = false;
        String type = decryAo.getType();


        byte[] b = new byte[0];
        String imgUrl = decryAo.getImgUrl();
        if (null != imgUrl && !imgUrl.equals("")) {
            HttpURLConnection connection = null;
            // 创建远程url连接对象
            URL connecUrl = null;
            try {
                connecUrl = new URL(imgUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            try {
                connection = (HttpURLConnection) connecUrl.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 设置连接方式：get
            try {
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 通过connection连接，获取输入流
            InputStream is = null;
            try {
                if (connection.getResponseCode() == 200) {
                    try {
                        is = connection.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != is) {
                byte[] buffer = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while (true) {
                    try {
                        if (!((len = is.read(buffer)) != -1)) {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bos.write(buffer, 0, len);
                }
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                b = bos.toByteArray();
            }
        }

        ByteArrayInputStream in = new ByteArrayInputStream(b);
        try {
            BufferedImage image = ImageIO.read(in);
            int h = image.getHeight();
            int w = image.getWidth();
            int[] buffer = new int[w * h];

            image.getRGB(0, 0, w, h, buffer, 0, w);

            // 一维转二维
            int[][] buffer_2d = new int[h][w];
            ArrayUtil.change(buffer, buffer_2d, h, w);

            // 解密
            MyAlgorithms ma = new MyAlgorithms();
            if (encrypt) {
                switch (type) {
                    case "rc":
                        ma.encrypt(buffer_2d, key, h, w);
                        break;
                    case "r":
                        ma.allRowEncrypt(buffer_2d, key, h, w);
                        break;
                    default:
                        System.out.println("未知的模式，可能的模式：r/rc");
                }
            } else {
                switch (type) {
                    case "rc":
                        ma.decrypt(buffer_2d, key, h, w);
                        break;
                    case "r":
                        ma.allRowDecrypt(buffer_2d, key, h, w);
                        break;
                    default:
                        System.out.println("未知的模式，可能的模式：r/rc");
                }
            }

            // 二维转一维
            ArrayUtil.recovery(buffer_2d, buffer, h, w);

            image.setRGB(0, 0, w, h, buffer, 0, w);

            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseVo.response(ResponseEnum.SUCCESS, imagePath + "/" + imageName);
    }


}

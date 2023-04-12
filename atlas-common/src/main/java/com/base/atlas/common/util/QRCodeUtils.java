package com.base.atlas.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

/**
 * @author wujie
 * @date 2023/3/10 10:59
 */
public class QRCodeUtils {

    private static final int BLACK = 0xFF000000;//用于设置图案的颜色
    private static final int WHITE = 0xFFFFFFFF; //用于背景色

    private QRCodeUtils() {
    }

    public static BitMatrix generateBitMatrix(String contents) throws WriterException {
        // contents 二维码内容
        int width = 430; // 二维码图片宽度 300
        int height = 430; // 二维码图片高度300

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//		hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
//	    hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数

        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                width, //条形码的宽度
                height, //条形码的高度
                hints);//生成条形码时的一些配置,此项可选

        // 生成二维码
//        File outputFile = new File("E:\\images" + File.separator + IdUtils.uuid()+".jpg");//指定输出路径
//
//        ZxingImageUtils.writeToFile(bitMatrix, format, outputFile,avatar);

        return bitMatrix;
    }

//    public static void main(String[] args) throws Exception {
//        try {
//            generateImage("我是图片20981209384","https://img-blog.csdnimg.cn/ac222b87a8714bf2ae64a3386b235393.png");
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//    }


    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? WHITE : BLACK));
            }
        }
        return image;
    }

    /**
     * 传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的
     * @return
     * @throws IOException
     */
    public static BufferedImage convertCircular(BufferedImage bi1)  {
        // 透明底的图片
        BufferedImage bi2 = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_RGB);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
        Graphics2D g2 = bi2.createGraphics();
        g2.fillRect(0,0,bi1.getWidth(),bi1.getHeight());
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2.drawImage(bi1, 0, 0, null);
        g2.dispose();
        return bi2;
    }


    public static BufferedImage writeToFile(BitMatrix matrix,String avatar) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        // 这里需要圆形就用 convertCircular 正方形矩阵的就用toBufferedImage
        BufferedImage bufferedImage = convertCircular(image);
        //设置logo图标
        bufferedImage = LogoMatrix(bufferedImage,avatar);

//        if (!ImageIO.write(bufferedImage, format, file)) {
//            throw new IOException("Could not write an image of format " + format + " to " + file);
//        }else{
//            System.out.println("图片生成成功！");
//        }
        return bufferedImage;
    }

    public static BufferedImage writeToStream(BitMatrix matrix, String format, OutputStream stream,String avatar) throws IOException {
        BufferedImage bufferedImage = toBufferedImage(matrix);
        //设置logo图标
        bufferedImage = LogoMatrix(bufferedImage,avatar);

        if (!ImageIO.write(bufferedImage, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
        return bufferedImage;
    }


    /**
     * 设置 logo
     * @param matrixImage 源二维码图片
     * @return 返回带有logo的二维码图片
     * @throws IOException
     * @author Administrator sangwenhao
     */
    public static BufferedImage LogoMatrix(BufferedImage matrixImage,String avatar) throws IOException {
        /**
         * 读取二维码图片，并构建绘图对象
         */
        Graphics2D g2 = matrixImage.createGraphics();
        int matrixWidth = matrixImage.getWidth();
        int matrixHeight = matrixImage.getHeight();
        /**
         * 读取Logo图片
         */
        URL url = new URL(avatar);
        BufferedImage logo = ImageIO.read(url);

        //开始绘制图片
        g2.drawImage(logo,matrixWidth/5*2,matrixHeight/5*2, matrixWidth/5, matrixHeight/5, null);//绘制
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);// 设置笔画对象
        //指定弧度的圆角矩形
        Ellipse2D.Float round = new Ellipse2D.Float(matrixWidth/5*2, matrixHeight/5*2, matrixWidth/5, matrixHeight/5);
        g2.setColor(Color.white);
        g2.draw(round);// 绘制圆弧矩形
        g2.dispose();
        matrixImage.flush() ;
        return matrixImage ;
    }


}

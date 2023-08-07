import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageDownloader {
    public static void main(String[] args) {
        String baseUrl = "https://img93.com/";
        String saveDirectory = "D:/sex/"; // 指定保存目录
        //13600
        int start = 1; // 起始数字
        int end = 9000; // 结束数字

        for (int i = start; i <= end; i++) {
            String imageUrl = baseUrl + i + ".jpg";
            String savePath = saveDirectory + i + ".jpg";

            try {
                downloadImage(imageUrl, savePath);
                System.out.println("图片 " + i + " 下载成功！");
            } catch (IOException e) {
                System.err.println("图片 " + i + " 下载失败：" + e.getMessage());
            }
        }
    }

    private static void downloadImage(String imageUrl, String savePath) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream inputStream = new BufferedInputStream(url.openStream());
             FileOutputStream outputStream = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}

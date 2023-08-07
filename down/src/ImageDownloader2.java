import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class ImageDownloader2 {

    public static void main(String[] args) throws Exception {
        int imgNumber = 1;  //用于给下载的图片进行命名
        for(int i = 590; i <= 1000; i++){
            for(int j = 1; j <= 4; j++){
                String imgUrl = "https://img.yalayi.net/img/gallery/" + i + "/z" + j + ".jpg!pcimg";
                saveImage(imgUrl, "D:/sexmax/sex" + imgNumber + ".jpg");
                imgNumber++;  //每下载一张图片，图片的名字序列就增加1
            }
        }
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }
        } catch(IOException e){
            System.err.println("Error while downloading images: " + e.getMessage());
        }
    }
}

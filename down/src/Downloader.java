import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Downloader {

    private static final String DOWNLOAD_PATH = "D:\\sexvideo\\"; // 这是保存文件的文件夹路径3068
    private static final String URL_PREFIX = "https://cdn82.com:10082/3151/index"; // 这是要下载的.ts文件的URL前缀
    private static final String URL_SUFFIX = ".ts"; // 这是要下载的.ts文件的URL后缀
    private static final int START_INDEX = 300; // 这是开始下载的索引
    private static final int END_INDEX = 500; // 这是结束下载的索引

    public static void main(String[] args) {
        for (int i = START_INDEX; i <= END_INDEX; i++) {
            downloadTsFile(i);
        }
    }

    private static void downloadTsFile(int index) {
        String fileUrl = URL_PREFIX + index + URL_SUFFIX;
        String saveFilePath = DOWNLOAD_PATH + "index" + index + URL_SUFFIX;
        try (InputStream in = new URL(fileUrl).openStream()) {
            Files.copy(in, Paths.get(saveFilePath), StandardCopyOption.REPLACE_EXISTING);
//            System.out.println("Downloaded: " + fileUrl);
        } catch (Exception ex) {
//            System.out.println("An error occurred while downloading: " + fileUrl);
            ex.printStackTrace();
        }
    }
}

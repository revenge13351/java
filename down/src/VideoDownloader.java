import java.io.*;
import java.net.*;

class VideoDownloader {
    public static void main(String[] args) {
        String[] links = {
                "https://cdn82.com:10082/13615/hls/QF5wETqf.ts",
                "https://cdn82.com:10082/13615/hls/OyzCv6eI.ts",
                "https://cdn82.com:10082/13615/hls/yCF2Jxvr.ts"
        };

        for (String link : links) {
            try {
                downloadVideo(link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadVideo(String link) throws IOException {
        URL url = new URL(link);

        InputStream in = new BufferedInputStream(url.openStream());
        File outDir = new File("D:\\sexvideo");
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        String fileName = link.substring(link.lastIndexOf('/'));
        FileOutputStream out = new FileOutputStream("D:\\sexvideo\\" + fileName);

        byte data[] = new byte[1024];
        int count;
        while((count = in.read(data, 0, 1024)) != -1) {
            out.write(data, 0, count);
        }

        out.close();
        in.close();
    }
}

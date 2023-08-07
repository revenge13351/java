import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MergeAndConvert {

    private static final String DIRECTORY = "D:\\sexvideo";
    private static final String OUTPUT_FILE = "output8.mp4";

    public static void main(String[] args) throws IOException {
        MergeAndConvert mergeAndConvert = new MergeAndConvert();
        File dir = new File(DIRECTORY);

        // 创建一个临时文件包含所有ts文件
        File tempFile = mergeAndConvert.createListFile(dir);

        if (tempFile != null) {
            mergeAndConvert.merge(tempFile);
        }

        // 删除 ts 文件
        mergeAndConvert.deleteTSFiles(dir);
    }

    private void deleteTSFiles(File dir) {
        File[] filesInDir = dir.listFiles();
        for (File file : filesInDir) {
            if (file.getName().endsWith(".ts")) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.out.println("Error while deleting file: " + file.getName());
                }
            }
        }
    }

    private File createListFile(File directory) throws IOException {
        File[] filesInDirectory = directory.listFiles();
        File listFile = new File(directory + "\\filesList.txt");
        try (FileWriter writer = new FileWriter(listFile)) {
            if (filesInDirectory != null) {
                for (File file : filesInDirectory) {
                    if (file.getName().endsWith(".ts")) {
                        writer.write("file '" + file.getName() + "'" + System.lineSeparator());
                    }
                }
            }
        }
        return listFile;
    }

    private void merge(File listFile) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "ffmpeg", "-f", "concat", "-safe", "0", "-i", listFile.getAbsolutePath(),
                "-c", "copy", DIRECTORY + "\\" + OUTPUT_FILE);

        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try {
            int exitCode = process.waitFor();
            System.out.println("Exit code: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

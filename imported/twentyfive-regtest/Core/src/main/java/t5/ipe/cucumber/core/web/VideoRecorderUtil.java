package t5.ipe.cucumber.core.web;
import org.monte.media.Format;
import org.monte.media.math.Rational;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorderUtil {
    // Provide your full ffmpeg.exe path here
    private static final String ffmpegPath =
            "C:\\Tools\\ffmpeg-2025-09-18-git-c373636f55-full_build\\bin\\ffmpeg.exe";
    private static MyScreenRecorder screenRecorder;

    public static void convertAviToMp4(String aviPath, String mp4Path) throws IOException, InterruptedException {
        // Ensure output directory exists
        File folder = new File("videos/");
        Process process = getProcess(aviPath, mp4Path, folder);
        // Wait until ffmpeg finishes and capture exit code
        System.out.println("Converting video from AVI to MP4...");
        int exitCode = process.waitFor();
        System.out.println("FFmpeg process exited with code: " + exitCode);
        if (exitCode != 0) {
            throw new RuntimeException("FFmpeg conversion failed with exit code " + exitCode);
        }
    }

    private static Process getProcess(String aviPath, String mp4Path, File folder) throws IOException {
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Build ffmpeg process
        ProcessBuilder pb = new ProcessBuilder(
                ffmpegPath,        // full path to ffmpeg.exe
                "-y",              // overwrite if file exists
                "-i", aviPath,     // input file
                "-vcodec", "libx264",
                "-crf", "23",
                mp4Path            // output file
        );

        pb.inheritIO();
        Process process = pb.start();
        return process;
    }

    public static void startRecording(String methodName) throws Exception {
        File folder = new File("videos/");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle captureSize = new Rectangle(screenSize);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new MyScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, folder, methodName);

        screenRecorder.start();
    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
    }
}

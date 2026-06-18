package t5.ipe.cucumber.core.web;

import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;

public class MyScreenRecorder extends ScreenRecorder {
    private String fileName;

    public MyScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea,
                            Format fileFormat, Format screenFormat, Format mouseFormat,
                            Format audioFormat, File movieFolder, String fileName) throws Exception {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.fileName = fileName;
    }

    @Override
    protected File createMovieFile(Format fileFormat) {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        }
        return new File(movieFolder, fileName + ".avi");
    }
}

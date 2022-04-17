package FileDemo;

import java.io.File;
import java.io.FileFilter;

public class FileFilterDemoImpl implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory())
            return true;
        return pathname.getName().toLowerCase().endsWith(".java");
    }
}

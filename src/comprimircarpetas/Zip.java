package comprimircarpetas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author josue
 */
public class Zip {
    private ZipOutputStream output;
    public void comprimir(String path_file, String path_zip){
        init(new File(path_zip));
        zip(new File(path_file));
    }
    private void init(File zip){
        try {
            output = new ZipOutputStream(new FileOutputStream(zip));
        }
        catch (FileNotFoundException ex) {
            System.out.println(getClass().getSimpleName()+": "+ex.getMessage());
        }
    }
    private boolean zipFile(File file){
        try {
            byte[] buf = new byte[1024];
            output.putNextEntry(new ZipEntry(file.getPath()));
            FileInputStream fis = new FileInputStream(file);
            int len;
            while((len = fis.read(buf)) > 0){
                output.write(buf, 0, len);
            }
            fis.close();
            output.closeEntry();
            return true;
        }
        catch (IOException ex) {
            System.out.println(getClass().getSimpleName()+": "+ex.getMessage());
        }
        return false;
    }
    private boolean zipDir(File file){
        try {
            output.putNextEntry(new ZipEntry(file.getPath()+File.pathSeparator));
            output.closeEntry();
            return true;
        }
        catch (IOException ex) {
            System.out.println(getClass().getSimpleName()+": "+ex.getMessage());
        }
        return false;
    }
    private boolean add(File... files){
        for(File file : files){
            if(file.isDirectory()){
                zipDir(file);
                add(file.listFiles());
            }
            else
                zipFile(file);
        }
        return true;
    }
    private void zip(File... files){
        try {
            add(files);
            output.finish();
            output.close();
        } catch (IOException ex) {
            System.out.println(getClass().getSimpleName()+": "+ex.getMessage());
        }
    }
}

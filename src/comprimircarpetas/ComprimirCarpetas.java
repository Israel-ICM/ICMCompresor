package comprimircarpetas;

import java.io.File;

/**
 *
 * @author josue
 */
public class ComprimirCarpetas {
    public static void main(String[] args) {
        //new Zip().comprimir("fotos", "miZip.zip");
        eliminarCarpetaContenido("fotos");
    }
    public static void eliminarCarpetaContenido(String path_directorio){
        File direct = new File(path_directorio);
        if(!direct.exists())
            return;
        if(direct.isDirectory()){
            for(File f : direct.listFiles()){
                eliminarCarpetaContenido(f.getPath());
            }
        }
        direct.delete();
    }
}

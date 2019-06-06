package ru.avalon.java.actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Действие, которое копирует файлы в пределах дискового
 * пространства.
 */
public class FileCopyAction implements Action {
    
    private final String sourceDirName;
    private final String targetDirName;
    
    
    public FileCopyAction(String source, String target) {
        this.sourceDirName = source;
        this.targetDirName = target;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №2 Реализуйте метод run класса FileCopyAction
         */
            File folder = new File(sourceDirName);
            File[] listOfFiles = folder.listFiles();
            Path targetDir = Paths.get(targetDirName);
            if(listOfFiles != null) {
                for (File file : listOfFiles)
                    try {
                        Files.copy(file.toPath(),
                                targetDir.resolve(file.getName()),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        Logger.getLogger(FileCopyAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } else System.out.println("the directory is empty!");
            
        }
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
                // нет необходимости, нечего освобождать
        /*
         * TODO №3 Реализуйте метод close класса FileCopyAction
         */
        
    }
}

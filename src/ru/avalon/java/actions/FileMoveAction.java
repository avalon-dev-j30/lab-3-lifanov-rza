package ru.avalon.java.actions;

import java.io.File;
/**
 * Действие, которое перемещает файлы в пределах дискового
 * пространства.
 */
public class FileMoveAction implements Action {
    private final String sourceDirName;
    private final String targetDirName;
    
    public FileMoveAction(String source, String target) {
        this.sourceDirName = source;
        this.targetDirName = target;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        /*
         * TODO №4 Реализуйте метод run класса FileMoveAction
         */
            File folder = new File(sourceDirName);
            File[] listOfFiles = folder.listFiles();
            File targetDir = new File(targetDirName);
            for(File file : listOfFiles) {
                file.renameTo(new File(targetDir, file.getName()));
            }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
                // нет необходимости
        /*
         * TODO №5 Реализуйте метод close класса FileMoveAction
         */
        throw new UnsupportedOperationException("Not implemented yet!");
    }

}

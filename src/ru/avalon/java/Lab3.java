package ru.avalon.java;

import ru.avalon.java.console.ConsoleUI;
import ru.avalon.java.actions.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка
 * многоуровневых приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность" 
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {
    
    public static final Semaphore SEMAPHORE = new Semaphore(1, true);
    public static final Semaphore SEMAPHORE2 = new Semaphore(1, true);
    /**
     * Точка входа в приложение.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        new Lab3().run();
    }
    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием
     * перечисления {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        String sourceDirName;
        String targetDirName;
        String folderName;
        Scanner in = new Scanner(System.in);
        synchronized (SEMAPHORE) {
            try {
                SEMAPHORE.acquire();
                switch (command) {
                    case copy:
                        System.out.println("Operation copy");
                        System.out.print("Insert folder name of source "
                                + "directory please > ");
                        sourceDirName = in.nextLine();
                        System.out.print("Insert folder name of source "
                                + "directory please > ");
                        targetDirName = in.nextLine();
                        new FileCopyAction(sourceDirName, targetDirName).start();
                        /*
                        * TODO №6 Обработайте команду copy
                        */
                        break;
                    case move:
                        System.out.println("Operation move");
                        System.out.print("Insert folder name of source "
                                + "directory please > ");
                        sourceDirName = in.nextLine();
                        System.out.print("Insert folder name of source "
                                + "directory please > ");
                        targetDirName = in.nextLine();
                        new FileMoveAction(sourceDirName, targetDirName).start();
                        /*
                        * TODO №7 Обработайте команду move
                        */
                        break;
                    case exit:
                        System.out.println("Operation exit");
                        close();
                        Action.executor.shutdown();
                        break;
                        /*
                        * TODO №9 Обработайте необработанные команды
                        */
                        
                    case create:
                        System.out.println("Operation create");
                        System.out.print("Insert folder name of source "
                                + "directory please > ");
                        sourceDirName = in.nextLine();
                        System.out.print("Insert new folder name > ");
                        folderName = in.nextLine();
                        new FolderCreateAction(sourceDirName, folderName).start();
                        break;
                        
                    case delete:
                        System.out.println("Operation delete");
                        System.out.print("Insert folder name of source "
                                + "directory please > ");
                        sourceDirName = in.nextLine();
                        new FileDeleteAction(sourceDirName).start();
                        break;
                }
                
                SEMAPHORE.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Lab3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

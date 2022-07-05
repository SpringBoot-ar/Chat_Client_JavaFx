package springboot_ar.chat.client.core;

public class Core {
    public static CoreController controller;
    public static CoreGUI gui;

    public static void start(){
        if(!isInit){
            init();
        }
    }

    private static boolean isInit = false;
    private static void init(){
        gui = new CoreGUI();
        controller = new CoreController();
        gui.start();
        isInit = true;

    }
}

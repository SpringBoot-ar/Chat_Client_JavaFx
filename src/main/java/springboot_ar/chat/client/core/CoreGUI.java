package springboot_ar.chat.client.core;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import springboot_ar.chat.client.gui.controller.Chat;
import springboot_ar.chat.client.gui.controller.Login;
import springboot_ar.chat.client.gui.controller.Root;
import springboot_ar.chat.client.gui.view.Index;

import java.io.IOException;

public class CoreGUI {

	public Stage stage;
	public Scene scene;

	public BorderPane gui_Root;
	public AnchorPane gui_Login,gui_Chat;

	public VBox gui_HotBar;
	public HBox gui_Header;

	public void start() {
		generateGUI();
	}

	public FXMLLoader getLoader(String path) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Index.class.getResource(path + ".fxml"));
		return loader;
	}

	public Pane getPain(FXMLLoader loader) {
		try {
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void generateGUI() {

		FXMLLoader loaderRoot = getLoader("Root");
		gui_Root = (BorderPane) getPain(loaderRoot);
		Core.controller.root = loaderRoot.<Root>getController();

		FXMLLoader loaderLogin = getLoader("Login");
		gui_Login = (AnchorPane) getPain(loaderLogin);
		Core.controller.login = loaderLogin.<Login>getController();

		FXMLLoader loaderChat = getLoader("Chat");
		gui_Chat = (AnchorPane) getPain(loaderChat);
		Core.controller.chat = loaderChat.<Chat>getController();


		stage = new Stage();
		scene = new Scene(gui_Root);
		stage.setScene(scene);

		scene.getStylesheets().add(Index.class.getResource("css2.css").toExternalForm());

		showLogin();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});

		Platform.setImplicitExit(true);
	}

	public StringProperty rootCss = new SimpleStringProperty();

	public void showLogin() {
		gui_Root.setCenter(gui_Login);
		gui_Root.setRight(null);
		gui_Root.setTop(null);
		stage.setMaximized(true);
		if (!stage.isShowing()) {
			stage.show();
		}
	}

    public static class GUIShow {

		public static void chat(){Core.gui.gui_Root.setCenter(Core.gui.gui_Chat);}

		public static void exit() {
			System.exit(0);
		}

	}
}

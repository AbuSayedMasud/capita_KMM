import SwiftUI
import shared

@main
struct iOSApp: App {

    
    init() {
        
        UINavigationBar.appearance().backgroundColor = UIColor(red: 0.592156862745098, green: 0.5490196078431373, blue: 0.12941176470588237, alpha: 1.0)
        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor.white]
        UIBarButtonItem.appearance().tintColor = .white
        
        // Set the preferred color scheme to light mode
              if #available(iOS 15.0, *) {
                  UIWindow.appearance().overrideUserInterfaceStyle = .light
              }
    }

    
	var body: some Scene {
		WindowGroup {
            LoginView()
                .preferredColorScheme(.light)
		}
	}
}

//import SwiftUI
//import shared
//
//@main
//struct iOSApp: App {
//
//    
//    init() {
//        
//        UINavigationBar.appearance().backgroundColor = UIColor(red: 0.592156862745098, green: 0.5490196078431373, blue: 0.12941176470588237, alpha: 1.0)
//        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor.white]
//        UIBarButtonItem.appearance().tintColor = .white
//        
//        // Customize the appearance of the tab bar
//               let tabBarAppearance = UITabBarAppearance()
//               tabBarAppearance.configureWithOpaqueBackground()
//               tabBarAppearance.backgroundColor = UIColor.black // Set the background color for light mode
//               if #available(iOS 13.0, *) {
//                   UITabBar.appearance().standardAppearance = tabBarAppearance
//                   UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
//               } else {
//                   UITabBar.appearance().standardAppearance = tabBarAppearance
//               }
//    }
//
//    
//	var body: some Scene {
//		WindowGroup {
//            LoginView()
//		}
//	}
//}
import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        configureAppearance()
    }
    
    private func configureAppearance() {
        UINavigationBar.appearance().backgroundColor = UIColor(red: 0.592, green: 0.549, blue: 0.129, alpha: 1.0)
        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor.white]
        UIBarButtonItem.appearance().tintColor = .white
        
        // Set up default tab bar appearance
        let tabBarAppearance = UITabBarAppearance()
        tabBarAppearance.configureWithOpaqueBackground()
        UITabBar.appearance().standardAppearance = tabBarAppearance
        if #available(iOS 13.0, *) {
            UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
        }
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onAppear {
                    updateAppearance()
                }
                .environment(\.colorScheme, .light) // Override to always use light mode for preview
        }
    }
    
    private func updateAppearance() {
        if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene {
            windowScene.windows.first?.overrideUserInterfaceStyle = .unspecified
        }
        
        if UITraitCollection.current.userInterfaceStyle == .dark {
            setTabBarAppearance(for: .dark)
        } else {
            setTabBarAppearance(for: .light)
        }
    }
    
    private func setTabBarAppearance(for style: UIUserInterfaceStyle) {
        let tabBarAppearance = UITabBarAppearance()
        tabBarAppearance.configureWithOpaqueBackground()
        
        if style == .dark {
            tabBarAppearance.backgroundColor = UIColor.black
        } else {
            tabBarAppearance.backgroundColor = UIColor.white
        }
        
        UITabBar.appearance().standardAppearance = tabBarAppearance
        if #available(iOS 13.0, *) {
            UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
        }
    }
}

struct ContentView: View {
    var body: some View {
        LoginView()
    }
}

//
//  HomeView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 5/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeView: View {
    var body: some View {
        
        TabView {
            shantaHomeView()
                .tabItem {
                    Image(uiImage: UIImage(named: "home")!)
                }
            
            marketView()
                .tabItem {
                    Image(uiImage: UIImage(named: "market")!)
                }
            
            NavigationView {
                Text("Trade is not implemented")
                    .navigationBarTitle("Trade", displayMode: .inline)
                    .background(Color.yellow) // Set the navigation bar color
            }
            .tabItem {
                Image(uiImage: UIImage(named: "trade")!)
            }
            
            NavigationView {
                ServicesView()
            }
            .tabItem {
                Image(uiImage: UIImage(named: "Services")!)
            }
            
            ProfileView()
                .tabItem {
                    Image(uiImage: UIImage(named: "profile")!)
                }
            
        }
        //tabbar image color
        .accentColor(appColor)
        .topSafeAreaColor()
    }
    
    
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}

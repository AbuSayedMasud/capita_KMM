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
                    //Image(systemName: "house.fill")
                    Image(uiImage: UIImage(named: "home")!)
                }
            
            marketView()
                .tabItem {
                    //Image(systemName: "2.circle")
                    Image(uiImage: UIImage(named: "market")!)
                }
            
            NavigationView {
                Text("Trade is not implemented")
                    .navigationBarTitle("Trade", displayMode: .inline)
                    .background(Color.yellow) // Set the navigation bar color
            }
            .tabItem {
                //Image(systemName: "3.circle")
                Image(uiImage: UIImage(named: "trade")!)
            }
            
            NavigationView {
                ServicesView()
            }
            .tabItem {
                //Image(systemName: "4.circle")
                Image(uiImage: UIImage(named: "Services")!)
            }
//            let profileService = CustomerProfileServiceImpl(databaseDriverFactory: DatabaseDriverFactory())
//            let viewModel = ProfileViewModel(profileService: profileService)
//            ProfileView(viewModel: viewModel)
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

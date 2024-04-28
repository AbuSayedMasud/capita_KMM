//
//  marketView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 10/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct marketView: View {
    @State private var selectedTabIndex = 0
    let names = ["Overview", "Indices", "Stocks", "Watchlist", "News"]
    
    var body: some View {
        NavigationView {
            VStack {
                // Content of the market view
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack {
                        ForEach(0..<names.count) { index in
                            Button(action: {
                                self.selectedTabIndex = index
                            }) {
                                VStack {
                                    Text(self.names[index])
                                        .padding()
                                        .foregroundColor(self.selectedTabIndex == index ? .white : Color.white.opacity(0.5))
                                    if self.selectedTabIndex == index {
                                        Rectangle()
                                            .frame(width: 70, height: 3)
                                            .foregroundColor(.white) // Color of the bar
                                    }
                                }
                            }
                        }
                    }
                    .background(appColor)
                }
                
                // Placeholder for content
                switch selectedTabIndex {
                case 0:
                    OverviewView()
                case 1:
                    IndicesView()
                case 2:
                    StocksView()
                case 3:
                    WatchlistView()
                case 4:
                    NewsView()
                default:
                    Text("No Content")
                }
                
                Spacer() // Add space to push buttons to the bottom
            }
            .navigationBarTitle("Market", displayMode: .inline)
            .navigationBarItems(
                leading: Button(action: {
                    // Action for the first button
                }) {
                    Image("loading_nav_button")
                },
                trailing: HStack {
                    Button(action: {
                        // Action for the second button
                        // Show your alert or perform an action
                    }) {
                        Image("search_nav_button")
                    }
                    Button(action: {
                        // Action for another button
                    }) {
                        Image("alarm_nav_button")
                    }
                }
            )
        }
    }
}

struct MarketView_Previews: PreviewProvider {
    static var previews: some View {
        marketView()
    }
}

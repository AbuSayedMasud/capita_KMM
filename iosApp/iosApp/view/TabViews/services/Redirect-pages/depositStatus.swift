//
//  deposit.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 27/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct deposit: View {
    var body: some View {
        
        VStack {
            //Spacer()
            NavigationLink(destination: depositeRequest()) {
                Text("+ New Request")
                    .padding()
                    .frame(maxWidth: .infinity, alignment: .center)
                                        
                    .background(Color(UIColor(named: "sectionTheme")!))
                    .foregroundColor(.primary)
                    .cornerRadius(25)
                    .shadow(color: .gray, radius: 5, x: 0, y: 5)
                    .overlay(
                        RoundedRectangle(cornerRadius: 25)
                            .stroke(appColor, lineWidth: 1)
                    )
        
            }
            .padding(.horizontal, 40)
        }
        .padding(.top, 20)
        .navigationBarTitle("Deposit", displayMode: .inline)
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

#Preview {
    deposit()
}

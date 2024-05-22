//
//  services.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 24/2/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ServicesView: View {
    var body: some View {
        VStack(spacing: 20) {
            NavigationLink(destination: marketView()) {
                RowView(title: "Deposite", icon: "Deposite")
            }
            
            NavigationLink(destination: marketView()) {
                RowView(title: "Withdraw", icon: "Withdraw")
            }
            
            NavigationLink(destination: marketView()) {
                RowView(title: "IPO", icon: "IPO")
            }
            
            NavigationLink(destination: marketView()) {
                RowView(title: "Tax Certification", icon: "Tax Certification")
            }
            
            NavigationLink(destination: marketView()) {
                RowView(title: "Product Switch", icon: "Product Switch")
            }
            
            Spacer()
        }
        .padding()
        .navigationBarTitle("Services", displayMode: .inline)
        .navigationBarItems(
            leading:
                Button(action: {
                    // Handle action for the first button
                }) {
                    Image(uiImage: UIImage(named: "loading_nav_button")!)
                },
            trailing:
                Button(action: {
                    // Handle action for the second button
                }) {
                    Image(uiImage: UIImage(named: "alarm_nav_button")!)
                }
        )
    }
}


struct RowView: View {
    var title: String
    var icon: String
    
    var body: some View {
        HStack {
            Image(icon)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 30, height: 30)
            Text(title)
                .font(.headline)
                .foregroundStyle(.primary)
            Spacer()
        }
        .padding()
        //.background(Color.white)
        .background(Color(UIColor(named: "sectionTheme")!))
        .cornerRadius(10)
        .shadow(radius: 5)
    }
}

struct ServicesView_Previews: PreviewProvider {
    static var previews: some View {
        ServicesView()
    }
}

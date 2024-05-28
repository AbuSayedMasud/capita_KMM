//
//  deposit.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 27/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct deposit: View {
    @State private var searchText = ""
    @State private var isActionMenuOpen = false
    @State private var selectedAction = "Action"
    @State private var aprovalStatus = "Declined"
    
    @State private var items = ["Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5"]
    
    var body: some View {
        ScrollView {
            VStack {
                NavigationLink(destination: depositeRequest()) {
                    Text("+ New Request")
                        .padding()
                        .frame(maxWidth: .infinity, alignment: .center)
                        .background(Color(UIColor(named: "sectionTheme")!))
                    //.foregroundColor(.primary)
                        .foregroundColor(.secondary)
                        .cornerRadius(25)
                        .shadow(color: .gray, radius: 5, x: 0, y: 5)
                        .overlay(
                            RoundedRectangle(cornerRadius: 25)
                                .stroke(appColor, lineWidth: 1)
                        )
                }
                .padding(.horizontal, 25)
                .padding(.top, 20)
                
                // Horizontal stack for additional buttons 🔍
                HStack() {
                    HStack {
                        TextField("🔍 Search Name..", text: $searchText)
                            .padding()
                            .frame(width: 174)
                            .background(Color(UIColor(named: "sectionTheme")!))
                            .cornerRadius(10)
                            .shadow(color: .gray, radius: 5, x: 0, y: 5)
                    }
                    .padding(.horizontal, 10)
                    
                    // Dropdown Button
                    Menu {
                        Button(action: {
                            selectedAction = "Add"
                        }) {
                            Text("Add")
                        }
                        Button(action: {
                            selectedAction = "Edit"
                        }) {
                            Text("Edit")
                        }
                        Button(action: {
                            selectedAction = "Delete"
                        }) {
                            Text("Delete")
                        }
                    } label: {
                        HStack {
                            Text("\(selectedAction)")
                            Image(uiImage: UIImage(named: "deposit-Dropdown")!)
                        }
                        .padding()
                        .frame(maxWidth: .infinity, alignment: .center)
                        .background(Color(UIColor(named: "sectionTheme")!))
                        .foregroundColor(.primary)
                        .cornerRadius(10)
                        .shadow(color: .gray, radius: 5, x: 0, y: 5)
                    }
                }
                .padding(.horizontal, 25)
                .padding(.top, 40)
                
                // TableView
                VStack(alignment: .leading, spacing: 8) {
                    ForEach(items.indices, id: \.self) { index in
                        HStack{
                            VStack(alignment: .leading, spacing: 4) {
                                Text("Tran ID #20506008731")
                                    .bold()
                                Spacer()
                                HStack{
                                    Text("22 Feb 2024")
                                        .foregroundColor(.secondary)
                                    Text("CASH")
                                        .foregroundColor(.secondary)
                                    Spacer()
                                }
                                
                            }
                            Spacer()
                            VStack{
                                //Text("\(index + 1). \(items[index])")
                                if aprovalStatus == "Declined"{
                                    Text(aprovalStatus)
                                    //.padding(.horizontal, 20)
                                        .padding(.init(top: 6, leading: 8, bottom: 6, trailing: 8))
                                        .foregroundColor(Color(uiColor: UIColor(red: 0.99, green: 0.35, blue: 0.35, alpha: 1.00)))
                                        .background(Color(uiColor: UIColor(red: 0.99, green: 0.35, blue: 0.35, alpha: 0.2)))
                                }
                                else{
                                    Text(aprovalStatus)
                                        .padding(.init(top: 6, leading: 8, bottom: 6, trailing: 8))
                                        .foregroundColor(Color(uiColor: UIColor(red: 0.24, green: 0.84, blue: 0.60, alpha: 1.00)))
                                        .background(Color(uiColor:  UIColor(red: 0.24, green: 0.84, blue: 0.60, alpha: 0.2)))
                                    
                                }
                                
                                Spacer()
                                Text("+ 50,000.00")
                            }
                        }
                        
                    }
                    //.frame(height: 89)
                    .padding()
                    .background(Color(UIColor(named: "sectionTheme")!))
                    .cornerRadius(10)
                    .shadow(radius: 5)
                    
                }
                .padding(.top , 40)
                .padding(.horizontal , 20)
            }
        }
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

struct deposit_Previews: PreviewProvider {
    static var previews: some View {
        deposit()
    }
}

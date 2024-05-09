//
//  shantaHomeView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 10/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct shantaHomeView: View {
    /*
     init() {
     // Customize navigation bar appearance
     let appearance = UINavigationBarAppearance()
     appearance.configureWithOpaqueBackground()
     let color = UIColor(red: 0.592156862745098, green: 0.5490196078431373, blue: 0.12941176470588237, alpha: 1.0)
     appearance.backgroundColor = color
     
     
     UINavigationBar.appearance().standardAppearance = appearance
     UINavigationBar.appearance().scrollEdgeAppearance = appearance
     }
     */
    //@ObservedObject private var viewModel: ContentViewModel//AccountBalanceViewModel
    //
    //      init(viewModel: ContentViewModel) {
    //          self.viewModel = viewModel
    //      }
    
    @ObservedObject var viewModel: AccountBalanceViewModel
    
    init() {
        
        self.viewModel = AccountBalanceViewModel()
    }
    // Define arrays for news titles, details, and dates
    let newsTitles = ["ABBANK: Weekly NAV", "ACI: Dividend Disbursement"]
    let newsDetails = [
        "SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead",
        "SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead"
    ]
    let newsDates = ["15-jun-2023", "15-jun-2023"]
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 20) {
                    VStack {
                        NavigationLink(destination: password_recovery()) {
                            HStack{
                                Text("Balance")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        HStack {
                            Text("Available Balance")
                            //.fontWeight(.medium)
                            
                            Spacer()

                            Text(String(viewModel.cashBalance))
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("Current Balance")
                            Spacer()
                            Text(String(viewModel.currentBalance))
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        HStack {
                            Text("Equtity")
                            Spacer()
                            Text(String(viewModel.equity))
                        }
                        
                        ColoredDivider(color: dividerColor, height: 1)
                        HStack {
                            Text("Purchase Power")
                            Spacer()
                            Text(String(viewModel.buyingPower))
                        }
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5) // Add shadow here
                    
                    VStack {
                        NavigationLink(destination: password_recovery()) {
                            HStack{
                                Text("Position")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        HStack {
                            VStack{
                                Text("ACI")
                                Text("Market Price")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                                Text("Average Price")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                                
                            }
                            
                            Spacer()
                            VStack{
                                Text("500.00")
                                Text("800.00")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                                Text("800.00")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                            }
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        HStack {
                            VStack{
                                Text("ACME")
                                Text("Market Price")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                                Text("Average Price")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                            }
                            
                            Spacer()
                            VStack{
                                Text("500.00")
                                Text("800.00")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                                Text("800.00")
                                    .font(.caption)
                                    .foregroundColor(.gray)
                            }
                        }
                        
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5) // Add shadow here
                    
                    VStack {//3rd vstack
                        NavigationLink(destination: password_recovery()) {
                            HStack{
                                Text("Receivable")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        HStack {
                            Text("Right Share Receivable")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("AL-HAJTEX")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("100.00 @ 0.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        HStack {
                            Text("AL-HAJTEX")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("50.00 @ 0.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("Cash Dividend Receivable")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("ACI")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("0.00 @ 22,000.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        HStack {
                            Text("ACI")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("0.00 @ 22,000.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                    
                    VStack {
                        NavigationLink(destination: password_recovery()) {
                            HStack{
                                Text("Transaction")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        HStack {
                            Text("Cash Deposit")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                            
                            Text("-20,000.00")
                                .foregroundColor(Color.red)
                                .bold()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("15-jun-2023")
                                .foregroundColor(.gray)
                            Spacer()
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("cdbl charge Ac opening (own)")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                            
                            Text("+500.00")
                                .foregroundColor(Color.green)
                                .bold()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("15-jun-2023")
                                .foregroundColor(.gray)
                            Spacer()
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("BUY - ABBANK")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                            
                            Text("+7,575.00")
                                .foregroundColor(Color.green)
                                .bold()
                        }
                        HStack {
                            Text("")
                        }
                        
                        HStack {
                            Text("SubAPSCL NonConvertIABLE and Fully Redeemable Couphead")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("500 @ 15")
                                .foregroundColor(.gray)
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("15-jun-2023")
                                .foregroundColor(.gray)
                            Spacer()
                        }
                        
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                    
                    // News Section
                    VStack {
                        NavigationLink(destination: password_recovery()) {
                            HStack{
                                Text("News")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        // Iterate over the news arrays to populate the news section dynamically
                        ForEach(0..<newsTitles.count) { index in
                            VStack {
                                HStack {
                                    Text(self.newsTitles[index])
                                        .foregroundColor(Color.black)
                                        .bold()
                                    Spacer()
                                }
                                HStack {
                                    Text("")
                                }
                                HStack {
                                    Text(self.newsDetails[index])
                                        .foregroundColor(.gray)
                                }
                                HStack {
                                    Text("")
                                }
                                HStack {
                                    Text(self.newsDates[index])
                                        .foregroundColor(.gray)
                                    Spacer()
                                }
                            }
                            
                            // Add divider only if it's not the last news item
                            if index != self.newsTitles.count - 1 {
                                ColoredDivider(color: dividerColor, height: 1)
                            }
                        }
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                }//full-vstack
                .padding(.top, 20)
                .padding(.horizontal, 20)
            }
            
            .navigationBarTitle("SHANTA", displayMode: .inline)
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
            
            
        }//navigationview
        
    }
}

#Preview {
    shantaHomeView()
}
